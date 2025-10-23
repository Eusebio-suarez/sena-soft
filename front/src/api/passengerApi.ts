import axios from "axios";
import type { PassengerRequestDTO } from "../models/passenger";

const API_URL = "http://localhost:8080/api/v1/passengers";

export const registerPassenger = async (data: PassengerRequestDTO) => {
  try {
    const response = await axios.post(`${API_URL}/register`, data);
    return response.data;
  } catch (error: unknown) {
    if (axios.isAxiosError(error) && error.response) {
      const message =
        error.response.data?.message || "Error al registrar pasajero";
      throw new Error(message);
    }
    throw new Error("Error de conexión con el servidor");
  }
};
