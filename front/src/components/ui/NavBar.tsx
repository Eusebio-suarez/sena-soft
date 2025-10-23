// src/components/NavBar.tsx
import React from "react";
import { Link } from "react-router-dom";

const NavBar: React.FC = () => {
  return (
    <nav style={{ padding: "1rem", borderBottom: "1px solid #ddd" }}>
      <Link to="/" style={{ marginRight: 16 }}>Inicio</Link>
      <Link to="/flights">Vuelos</Link>
    </nav>
  );
};

export default NavBar;
