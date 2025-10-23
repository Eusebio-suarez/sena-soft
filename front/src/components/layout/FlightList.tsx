import React, { useEffect, useState } from "react";
import type { Flight } from "../../models/flights";
import { getFlights } from "../../api/flightApi";

const FlightList: React.FC = () => {
  const [flights, setFlights] = useState<Flight[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

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
                <button className="mt-4 w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition">
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
