<?php
$hostname='localhost';
$database='id14884871_db_farmacia';
$username='id14884871_farmacia';
$password='Giancarlo1#3';

$conexion=new mysqli($hostname,$username,$password,$database);
if($conexion->connect_errno){
    echo "El sitio web está experimentado problemas";
}
?>