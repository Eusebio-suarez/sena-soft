// src/components/PassengerForm.tsx
import React from "react";
import { usePassengerForm } from "../../hooks/userPassengerForm";
import type { DocEnum, GenderEnum } from "../../models/passenger";
import PayBuutton from "../ui/PayBuutton";

const PassengerForm: React.FC = () => {
  const { formData, handleChange, handleSubmit, passengers } = usePassengerForm();

  const docTypes: DocEnum[] = ["CC", "TI", "PE", "RC"];
  const genders: GenderEnum[] = ["MEN", "WOMEN", "OTHER"];

  return (
    <div className="min-h-screen bg-gray-50 flex flex-col items-center p-6">
      <h1 className="text-3xl font-bold mb-6 text-blue-700">Registro de Pasajeros</h1>

      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleSubmit();
        }}
        className="bg-white shadow-md rounded-2xl p-6 w-full max-w-lg"
      >
        <input
          name="name"
          value={formData.name}
          onChange={handleChange}
          placeholder="Nombre completo"
          className="border rounded-lg p-2 w-full mb-3"
          required
        />

        <select
          name="docType"
          value={formData.docType}
          onChange={handleChange}
          className="border rounded-lg p-2 w-full mb-3"
        >
          {docTypes.map((doc) => (
            <option key={doc} value={doc}>
              {doc}
            </option>
          ))}
        </select>

        <input
          name="numberDoc"
          value={formData.numberDoc}
          onChange={handleChange}
          placeholder="Número de documento"
          className="border rounded-lg p-2 w-full mb-3"
          required
        />

        <input
          type="date"
          name="birth"
          value={formData.birth}
          onChange={handleChange}
          className="border rounded-lg p-2 w-full mb-3"
          required
        />

        <select
          name="gender"
          value={formData.gender}
          onChange={handleChange}
          className="border rounded-lg p-2 w-full mb-3"
        >
          {genders.map((gender) => (
            <option key={gender} value={gender}>
              {gender}
            </option>
          ))}
        </select>

        <label className="flex items-center gap-2 mb-3">
          <input
            type="checkbox"
            name="infant"
            checked={formData.infant}
            onChange={handleChange}
          />
          <span>¿Es infante?</span>
        </label>

        <input
          name="phone"
          value={formData.phone}
          onChange={handleChange}
          placeholder="Teléfono"
          className="border rounded-lg p-2 w-full mb-3"
          required
        />

        <input
          type="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          placeholder="Correo electrónico"
          className="border rounded-lg p-2 w-full mb-3"
          required
        />

        <input
          type="number"
          name="seatNumber"
          value={formData.seatNumber}
          onChange={handleChange}
          placeholder="Número de asiento"
          min="1"
          className="border rounded-lg p-2 w-full mb-3"
          required
        />

        <button
          type="submit"
          className="bg-blue-600 text-white py-2 px-4 rounded-xl w-full hover:bg-blue-700 transition"
        >
          Registrar pasajero
        </button>
      </form>

      {passengers.length > 0 && (
        <div className="mt-6 w-full max-w-lg">
          <h2 className="text-lg font-semibold mb-2">Pasajeros registrados:</h2>
          <ul className="bg-white shadow rounded-lg p-3">
            {passengers.map((p, index) => (
              <li key={index} className="border-b py-2 last:border-none">
                {p.name} - Asiento {p.seatNumber}
              </li>
            ))}
          </ul>
        </div>
      )}
    <PayBuutton></PayBuutton>
    </div>
  );
};

export default PassengerForm;
