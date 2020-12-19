package com.example.app_easyfarma;

import java.io.Serializable;

public class Producto implements Serializable {

    String id;
    String producto;
    String precio;
    String ubicacion;
    int cantidad;
    double total;


    public Producto(String id, String producto, String precio, String ubicacion) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.ubicacion = ubicacion;

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
