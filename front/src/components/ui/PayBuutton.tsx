import { useNavigate } from "react-router-dom";

export default function PayBuutton() {
// Dentro de tu componente:
const navigate = useNavigate();

return (
  <div>
    {/* Aquí va tu formulario de pasajero */}
    <button
      type="button"
      onClick={() => navigate("/payment")}
      className="bg-green-600 text-white px-4 py-2 rounded mt-4 hover:bg-green-700 transition"
    >
      Ir a Pagar
    </button>
  </div>
);

}
