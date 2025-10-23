import { useState } from "react";
import { registerPayment } from "../api/paymentApi";

export const usePaymentForm = () => {
  const [formData, setFormData] = useState({
    name: "",
    docType: "CC",
    numberDoc: "",
    email: "",
    phone: "",
  });

  const [loading, setLoading] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    const bookingId = localStorage.getItem("bookingId");

    if (!bookingId) {
      alert("⚠️ No se encontró una reserva activa.");
      setLoading(false);
      return;
    }

    const paymentData = {
      ...formData,
      bookingId: Number(bookingId),
    };

    try {
      const response = await registerPayment(paymentData);
      const data = response.data || response;

      if (Array.isArray(data) && data.length > 0) {
        const resumen = data
          .map(
            // eslint-disable-next-line @typescript-eslint/no-explicit-any
            (tiket: any, i: number) =>
              `🎟️ Tiquete ${i + 1}\n` +
              `🆔 COD: ${tiket.id}\n` +
              `👤 Pasajero: ${tiket.name}\n` +
              `💺 Asiento: ${tiket.seatNumber}\n` +
              `🕒 Salida: ${tiket.departureDate}\n` +
              `🕓 Llegada: ${tiket.arrivalDate}\n` +
              `💰 Precio total: $${tiket.totalPrice}\n`
          )
          .join("\n--------------------\n");

        alert(`✅ Pago realizado con éxito.\n\n${resumen}`);
      } else {
        alert("✅ Pago realizado, pero no se recibieron tiquetes.");
      }

      window.location.href = "/";
    } catch (error) {
      alert(error || "❌ Hubo un problema al procesar el pago.");
    } finally {
      setLoading(false);
    }
  };

  return { formData, loading, handleChange, handleSubmit };
};
