<?php

require_once('model/functions.php');
require_once('conn/conn.php');

if ($_SERVER['REQUEST_METHOD'] === "POST") {
    $origen = $_POST['origen']??'';
    $destino = $_POST['destino']??'';
    $tipo_ruta = $_POST['tipo_ruta']??'';
    $fecha = $_POST['fecha']??'';
}

BuscarVuelos($conn,$origen,$destino,$tipo_ruta,$fecha);
?>