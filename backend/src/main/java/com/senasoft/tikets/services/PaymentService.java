package com.senasoft.tikets.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.senasoft.tikets.dto.request.PaymentRequestDTO;
import com.senasoft.tikets.dto.response.TiketResponseDTO;
import com.senasoft.tikets.entity.BookingEntity;
import com.senasoft.tikets.entity.PassengerEntity;
import com.senasoft.tikets.entity.PaymentEntity;
import com.senasoft.tikets.entity.TiketEntity;
import com.senasoft.tikets.enums.SeatStatusEnum;
import com.senasoft.tikets.enums.StatusEnum;
import com.senasoft.tikets.exceptions.ExceptionImpl;
import com.senasoft.tikets.repository.BookingRepository;
import com.senasoft.tikets.repository.PassengerRepository;
import com.senasoft.tikets.repository.PaymentRepository;
import com.senasoft.tikets.repository.TiketReposistory;

@Service
public class PaymentService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TiketReposistory tiketReposistory;

    @Autowired
    private PassengerRepository passengerRepository;
    
    public List<TiketResponseDTO> processPayment(PaymentRequestDTO paymentRequestDTO) {

        // Buscar la reserva
        Optional<BookingEntity> bookingOptional = bookingRepository.findById(paymentRequestDTO.getBookingId());
        if (bookingOptional.isEmpty()) {
            throw new ExceptionImpl("No se encontró la reserva", HttpStatus.NOT_FOUND);
        }

        BookingEntity booking = bookingOptional.get();

        if(booking.getStatus()==StatusEnum.COMPLETE){
            throw new ExceptionImpl("la reserva ya fue paga", HttpStatus.BAD_REQUEST);
        }

        // Crear el pago
        PaymentEntity payment = PaymentEntity.builder()
            .name(paymentRequestDTO.getName())
            .docType(paymentRequestDTO.getDocType())
            .numberDoc(paymentRequestDTO.getNumberDoc())
            .monto(booking.getTotalPrice())
            .email(paymentRequestDTO.getEmail())
            .phone(paymentRequestDTO.getPhone())
            .date(LocalDate.now())
            .build();

        paymentRepository.save(payment);

        // Asociar el pago a la reserva
        booking.setPayment(payment);
        booking.setStatus(StatusEnum.COMPLETE);
        bookingRepository.save(booking);

        // Obtener los pasajeros de la reserva
        List<PassengerEntity> passengerList = booking.getPassengers();

        // Crear los tickets y actualizar asientos
        for (PassengerEntity passenger : passengerList) {
            TiketEntity tiket = TiketEntity.builder()
                .booking(booking)
                .passenger(passenger)
                .build();

            tiketReposistory.save(tiket);

            // Asociar el ticket al pasajero
            passenger.setTiket(tiket);

            // Cambiar el estado del asiento
            passenger.setSeatStatus(SeatStatusEnum.BUSY);
            passengerRepository.save(passenger);
        }

        // Retornar los tickets generados
        return passengerRepository.findByBooking(booking).stream()
            .map(p -> TiketResponseDTO.builder()
                .id(p.getTiket().getId())
                .name(p.getName())
                .seatNumber(p.getSeatNumber())
                .departureDate(p.getBooking().getFlight().getDepartureDate())
                .arrivalDate(p.getBooking().getFlight().getArrivalDate())
                .totalPrice(p.getBooking().getTotalPrice())
                .build()
            )
            .toList();
            
    }
}
