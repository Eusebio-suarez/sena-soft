<?php
require_once('backend_php/model/functions.php');
require_once('backend_php/conn/conn.php');

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


use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;


require 'vendor/autoload.php'; // Adjust path if not using Composer



    $mail = new PHPMailer(true); // Enable exceptions
try {
// Server settings
$mail->isSMTP(); // Use SMTP
$mail->Host = 'smtp.gmail.com'; // SMTP server
$mail->SMTPAuth = true; // Enable authentication
$mail->Username = 'santivilla19maya@gmail.com'; // SMTP username
$mail->Password = 'etrn oymd svlj thjb'; // SMTP password
$mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS; // Encryption (TLS/SSL)
$mail->Port = 587; // TCP port (587 for TLS)

// Recipients
$mail->setFrom('santivilla19maya@gmail.com');
$mail->addAddress($email); // Add recipient

// Content
$mail->isHTML(true); // Email format: HTML
$mail->Subject = 'Muchas gracias por confiar en nosotros';
$mail->Body = '<b>Has realizado tu reserva exitosamente en el asiento '.$numasiento.'</b>';
$mail->AltBody = '';

$mail->send();
RegistrarInfo($conn,$idvuelo,$numasiento,$nombre,$email,$phone,$radio,$genero,$numdoc,$tipodoc,$nacimiento);
} catch (Exception $e) {
echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}";
}

?>


