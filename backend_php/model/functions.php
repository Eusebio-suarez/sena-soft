<?php
session_start();

function BuscarVuelos($conn, $origen, $destino, $tipo_ruta, $fecha)
{
    $sql = "SELECT * FROM vuelos 
            WHERE origen LIKE ? 
            AND destino LIKE ? 
            AND tipo = ? 
            AND fecha_vuelo = ?";

    $stmt = mysqli_prepare($conn, $sql);

    // Para permitir búsquedas parciales
    $origen = "%$origen%";
    $destino = "%$destino%";

    mysqli_stmt_bind_param($stmt, "ssis", $origen, $destino, $tipo_ruta, $fecha);
    mysqli_stmt_execute($stmt);
    $res = mysqli_stmt_get_result($stmt);

    if (mysqli_num_rows($res) > 0) {
        // Guardar todos los vuelos encontrados en una sesión
        $_SESSION['vuelos'] = mysqli_fetch_all($res, MYSQLI_ASSOC);

        echo '
        <script>
        alert("Se han encontrado vuelos");
        window.location.href = "../vistas/vuelos.php";
        </script>
        ';
    } else {
        echo '
        <script>
        alert("No se han encontrado vuelos con esos criterios");
        window.location.href = "../vistas/buscar_vuelos.html";
        </script>
        ';
    }
}

function RegistrarInfo($conn,$idvuelo,$numasiento,$nombre,$email,$phone,$radio,$genero,$numdoc,$tipodoc,$nacimiento)
{

    $validate = "SELECT * FROM asientos_pasajero WHERE id_asiento = ?";
    $val = mysqli_prepare($conn,$validate);
    mysqli_stmt_bind_param($val,"i",$numasiento);
    mysqli_stmt_execute($val);

    $res = mysqli_stmt_get_result($val);
    if (mysqli_num_rows($res) > 0) {
        echo '
        <script>
        alert("Asiento ya ocupado lo sentimos");
        window.location.href = "../index.html";
        </script>
        ';
    }

    $sql = "INSERT INTO compradores(nacimiento,num_doc,tipo_doc,email,nombre,telefono) VALUES (?,?,?,?,?,?)";
    $stmt = mysqli_prepare($conn,$sql);
    mysqli_stmt_bind_param($stmt,"ssisss",$nacimiento,$numdoc,$tipodoc,$email,$nombre,$phone);

    if (mysqli_stmt_execute($stmt)) {

    $idcomprador = mysqli_insert_id($conn);

    $sql1 = "INSERT INTO reservas(id_vuelo,id_comprador) VALUES (?,?)";
    $stmt1 = mysqli_prepare($conn,$sql1);
    mysqli_stmt_bind_param($stmt1,"ii",$idvuelo,$idcomprador);

        if (mysqli_stmt_execute($stmt1)) {
            $idreserva = mysqli_insert_id($conn);
            $sql2 = "INSERT INTO pasajeros(nacimiento,email,genero,infant,telefono,num_asiento,nombre,num_doc,tipo_doc) VALUES (?,?,?,?,?,?,?,?,?)";
            $stmt2 = mysqli_prepare($conn,$sql2);
            mysqli_stmt_bind_param($stmt2,"ssssssssi",$nacimiento,$email,$genero,$radio,$phone,$numasiento,$nombre,$numdoc,$tipodoc);
            if (mysqli_stmt_execute($stmt2)) {
                echo '
                <script>
                alert("Se ha registrado una reserva, revisa tu correo y continua al pago");
                window.location.href = "vistas/pago.html";
                </script>
                ';
            }
        }
    }
}
?>
