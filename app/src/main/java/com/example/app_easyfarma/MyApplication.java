package com.example.app_easyfarma;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {

    private ArrayList<Producto> productos;

    public ArrayList<Producto> getProducts() {
        return productos;
    }

    public void setProducts(ArrayList<Producto> productos) {
        this.productos = productos;
    }
}

