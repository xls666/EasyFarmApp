package com.example.app_easyfarma;

import java.io.Serializable;

public class Usuario implements Serializable {

    String id;
    String producto;
    String precio;
    String ubicacion;


    public Usuario(String id, String producto, String precio, String ubicacion) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.ubicacion = ubicacion;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


}
