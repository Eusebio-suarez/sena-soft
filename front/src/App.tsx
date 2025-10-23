// src/App.tsx
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import NavBar from "./components/ui/NavBar";
import FlightList from "./components//layout/FlightList";

const Home: React.FC = () => (
  <div style={{ padding: 16 }}>
    <h1>✈️ AirBook</h1>
    <p>Bienvenido — usa la navegación para ver vuelos.</p>
  </div>
);

const NotFound: React.FC = () => (
  <div style={{ padding: 16 }}>
    <h2>404 • Página no encontrada</h2>
  </div>
);

const App: React.FC = () => {
  return (
    <BrowserRouter>
      <NavBar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/flights" element={<FlightList />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
