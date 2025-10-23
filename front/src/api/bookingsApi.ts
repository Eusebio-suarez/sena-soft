import axios from "axios";
import type { ApiResponse } from "../models/flights";

const API_URL = "http://localhost:8080/api/v1/bookings"; // ajusta si tu back usa otro puerto

export const registerBooking = async (flightId: number): Promise<ApiResponse<number>> => {
    const response = await axios.post<ApiResponse<number>>(`${API_URL}/register`, {
        flightId,
    });
    return response.data;
};
