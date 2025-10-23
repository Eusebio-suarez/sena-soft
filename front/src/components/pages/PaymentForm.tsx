import { usePaymentForm } from "../../hooks/usePaymentForm";

const PaymentForm = () => {
  const { formData, loading, handleChange, handleSubmit } = usePaymentForm();

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <form
        onSubmit={handleSubmit}
        className="bg-white p-8 rounded-2xl shadow-lg w-[400px] space-y-4"
      >
        <h2 className="text-2xl font-bold text-center text-gray-700 mb-6">
          Formulario de Pago
        </h2>

        <input
          type="text"
          name="name"
          placeholder="Nombre completo"
          value={formData.name}
          onChange={handleChange}
          required
          className="border p-2 w-full rounded"
        />

        <select
          name="docType"
          value={formData.docType}
          onChange={handleChange}
          className="border p-2 w-full rounded"
        >
          <option value="CC">Cédula de ciudadanía</option>
          <option value="TI">Tarjeta de identidad</option>
          <option value="PE">Pasaporte</option>
          <option value="RC">Registro civil</option>
        </select>

        <input
          type="text"
          name="numberDoc"
          placeholder="Número de documento"
          value={formData.numberDoc}
          onChange={handleChange}
          required
          className="border p-2 w-full rounded"
        />

        <input
          type="email"
          name="email"
          placeholder="Correo electrónico"
          value={formData.email}
          onChange={handleChange}
          required
          className="border p-2 w-full rounded"
        />

        <input
          type="text"
          name="phone"
          placeholder="Teléfono"
          value={formData.phone}
          onChange={handleChange}
          required
          className="border p-2 w-full rounded"
        />

        <button
          type="submit"
          disabled={loading}
          className={`w-full py-2 rounded text-white transition ${
            loading
              ? "bg-gray-400 cursor-not-allowed"
              : "bg-blue-600 hover:bg-blue-700"
          }`}
        >
          {loading ? "Procesando..." : "Realizar Pago"}
        </button>
      </form>
    </div>
  );
};

export default PaymentForm;
