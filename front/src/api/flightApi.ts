import axios from "axios";
import type { ApiResponse, Flight } from "../models/flights";

const API_URL = "http://localhost:8080/api/v1/flights";

export const getFlights = async (): Promise<ApiResponse<Flight[]>> => {
    const response = await axios.get<ApiResponse<Flight[]>>(API_URL);
    return response.data;
};
