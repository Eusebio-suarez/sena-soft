// Tipos e interfaces relacionados con vuelos

export type FlightType = "ONLY" | "DOUBLE"; // Puedes ampliar según tu enum real

export interface Flight {
    id: number;
    origin: string;
    destination: string;
    type: FlightType;
    departureDate: string; // ISO string desde el backend
    arrivalDate: string;
    price: number;
}

export interface ApiResponse<T> {
    success: boolean;
    message: string;
    data: T;
}
