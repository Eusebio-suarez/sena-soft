import { BrowserRouter, Routes, Route } from "react-router-dom";
import FlightList from "./components/layout/FlightList";
import PassengerForm from "./components/pages/PassengerForm";
import PaymentForm from "./components/pages/PaymentForm";

function App() {
  return (
    <BrowserRouter> 
      <Routes>
        <Route path="/" element={<FlightList />} />
        <Route path="/passengers" element={<PassengerForm />} />
        <Route path="payment" element={<PaymentForm></PaymentForm>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
