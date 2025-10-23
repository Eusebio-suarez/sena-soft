import React, { useEffect, useState } from "react";
import type { Flight } from "../../models/flights";
import { getFlights } from "../../api/flightApi";
import { registerBooking } from "../../api/bookingsApi";
import { useNavigate } from "react-router-dom";

const FlightList: React.FC = () => {
  const [flights, setFlights] = useState<Flight[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [bookingSuccess, setBookingSuccess] = useState<string | null>(null);

  const navigate = useNavigate();

  // ✅ Reservar vuelo
  const handleBooking = async (flightId: number) => {
    try {
      const response = await registerBooking(flightId);
      if (response.success) {
        const bookingId = response.data; // número devuelto por el backend
        localStorage.setItem("bookingId", bookingId.toString());
        setBookingSuccess(`Reserva creada exitosamente ✅ (ID: ${bookingId})`);

        // redirigir a la página de registro de pasajeros
        setTimeout(() => navigate("/passengers"), 1200);
      } else {
        setError(response.message || "No se pudo registrar la reserva");
      }
    } catch {
      setError("Error al conectar con el servidor");
    }
  };

  // ✅ Cargar vuelos
  useEffect(() => {
    const fetchFlights = async () => {
      try {
        const response = await getFlights();
        if (response.success) {
          setFlights(response.data);
        } else {
          setError(response.message || "Error al obtener los vuelos");
        }
      } catch {
        setError("No se pudo conectar con el servidor");
      } finally {
        setLoading(false);
      }
    };
    fetchFlights();
  }, []);

  // ✅ Render
  if (loading) {
    return (
      <div className="flex justify-center items-center h-64">
        <p className="text-gray-500 text-lg animate-pulse">
          Cargando vuelos disponibles...
        </p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex justify-center items-center h-64">
        <p className="text-red-600 font-semibold">{error}</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-linear-to-b from-blue-50 to-blue-100 p-6">
      <h2 className="text-3xl font-bold text-center text-blue-700 mb-8">
        ✈️ Vuelos disponibles
      </h2>

      {bookingSuccess && (
        <div className="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-6 text-center">
          {bookingSuccess}
        </div>
      )}

      {flights.length === 0 ? (
        <p className="text-center text-gray-600 text-lg">
          No hay vuelos disponibles por el momento
        </p>
      ) : (
        <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-3">
          {flights.map((flight) => (
            <div
              key={flight.id}
              className="bg-white shadow-md rounded-2xl overflow-hidden hover:shadow-xl transition duration-300"
            >
              <img
                src="data:image/jpeg;base64,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"
                alt="Vuelo"
                className="w-full h-40 object-cover"
              />

              <div className="p-4">
                <h3 className="text-lg font-semibold text-gray-800 mb-2">
                  {flight.origin} → {flight.destination}
                </h3>
                <p className="text-sm text-gray-600">
                  <span className="font-medium">Tipo:</span> {flight.type}
                </p>
                <p className="text-sm text-gray-600">
                  <span className="font-medium">Salida:</span>{" "}
                  {new Date(flight.departureDate).toLocaleString()}
                </p>
                <p className="text-sm text-gray-600">
                  <span className="font-medium">Llegada:</span>{" "}
                  {new Date(flight.arrivalDate).toLocaleString()}
                </p>
                <p className="mt-3 text-xl font-bold text-blue-600">
                  {flight.price.toLocaleString("es-CO", {
                    style: "currency",
                    currency: "COP",
                  })}
                </p>
                <button
                  onClick={() => handleBooking(flight.id)}
                  className="mt-4 w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition"
                >
                  Reservar vuelo
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default FlightList;
