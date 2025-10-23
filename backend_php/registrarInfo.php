<?php

require_once('model/functions.php');
require_once('conn/conn.php');

$idvuelo = $_POST['id_vuelo']??'';
$numasiento = $_POST['num_asiento']??'';
$email = $_POST['email']??'';
$nombre = $_POST['nombre']??'';
$phone = $_POST['phone']??'';
$radio = $_POST['radio']??'';
$genero = $_POST['genero']??'';
$numdoc = $_POST['num_doc']??'';
$tipodoc = $_POST['tipodoc']??'';
$nacimiento = $_POST['nacimiento']??'';

RegistrarInfo($conn,$idvuelo,$numasiento,$nombre,$email,$phone,$radio,$genero,$numdoc,$tipodoc,$nacimiento);


?>