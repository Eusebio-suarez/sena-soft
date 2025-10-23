// src/hooks/usePassengerForm.ts
import { useState } from "react";
import { registerPassenger } from "../api/passengerApi";
import type { PassengerRequestDTO } from "../models/passenger";

export const usePassengerForm = () => {
  const [passengers, setPassengers] = useState<PassengerRequestDTO[]>([]);
  const [formData, setFormData] = useState<PassengerRequestDTO>({
    bookingId: Number(localStorage.getItem("bookingId")) || 0,
    name: "",
    docType: "CC",
    numberDoc: "",
    birth: "",
    gender: "MEN",
    infant: false,
    phone: "",
    email: "",
    seatNumber: 1,
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value, type } = e.target;
    const isCheckbox = type === "checkbox";
    const inputElement = e.target as HTMLInputElement;

    setFormData((prev) => ({
      ...prev,
      [name]: isCheckbox ? inputElement.checked : value,
    }));
  };

  const handleSubmit = async () => {
    if (passengers.length >= 5) {
      alert("Solo se pueden registrar hasta 5 pasajeros.");
      return;
    }

    try {
      const response = await registerPassenger(formData);
      setPassengers([...passengers, formData]);
      alert(`Pasajero registrado con éxito. ID: ${response}`);
      setFormData({
        ...formData,
        name: "",
        numberDoc: "",
        birth: "",
        phone: "",
        email: "",
        seatNumber: formData.seatNumber + 1,
      });
    } catch (error) {
      console.error(error);
        if (error instanceof Error) {
        alert(error.message || "error al registrar pasagero");
      } else {
        alert("error al registrar pasagero");
      }
    }
  };

  return { formData, handleChange, handleSubmit, passengers };
};
