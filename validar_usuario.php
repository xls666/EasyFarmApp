<?php
include 'conexion.php';
$correo=$_POST['correo'];
$contraseña=$_POST['contraseña'];

//$usu_usuario="aroncal@gmail.com";
//$usu_password="12345678";

$sentencia=$conexion->prepare("SELECT * FROM cliente WHERE correo=? AND contraseña=?");
$sentencia->bind_param('ss',$correo,$contraseña);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
         echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
}
$sentencia->close();
$conexion->close();
?>