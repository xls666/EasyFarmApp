<?php

    require "conexion1.php";
    
    // PRUEBAS
 
    
    $sql = "SELECT * FROM productos";
    $query = $mysqli->query($sql);
    
    $datos = array();
    
    while($resultado = $query->fetch_assoc()) {
        $datos[] = $resultado;
    }
    
    //echo json_encode($datos);
    echo json_encode(array("Productos" => $datos));
?>
