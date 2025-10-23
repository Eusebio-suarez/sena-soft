// src/api/paymentApi.ts
import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/payments";

// eslint-disable-next-line @typescript-eslint/no-explicit-any
export const registerPayment = async (data: any) => {
  try {
    const response = await axios.post(API_URL, data);
    return response.data;
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  } catch (error: any) {
    if (error.response && error.response.data) {
      throw new Error(error.response.data.message || "Error al registrar el pago.");
    }
    throw new Error("Error de conexión con el servidor.");
  }
};
