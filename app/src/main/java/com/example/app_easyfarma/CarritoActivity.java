package com.example.app_easyfarma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity {

    RecyclerView recycler_confirmar_pedido;
    TextView tv_suma_total;
    CarritoAdapter carritoAdapter;

    private ArrayList<Producto> productos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        init();
        setupAdapter();
    }

    private void init() {

        recycler_confirmar_pedido = findViewById(R.id.recycler_confirmar_pedido);
        tv_suma_total = findViewById(R.id.tv_suma_total);
        productos = ((MyApplication) this.getApplication()).getProducts();
    }

    private void setupAdapter() {

        carritoAdapter = new CarritoAdapter(productos);
        recycler_confirmar_pedido.setAdapter(carritoAdapter);
        recycler_confirmar_pedido.setLayoutManager(new LinearLayoutManager(this));

        carritoAdapter.onItemClickListener(new CarritoAdapter.OnItemClick() {
            @Override
            public void itemClick(Producto producto) {

                double totalPedido = 0.0;
                for (int i=0;i<productos.size();i++){

                    if (productos.get(i).id != producto.id){
                        totalPedido = totalPedido + Double.parseDouble(productos.get(i).precio) * productos.get(i).cantidad;
                    }

                }

                tv_suma_total.setText(""+totalPedido);
            }
        });

        double totalPedido = 0.0;
        for (int i=0;i<productos.size();i++){

            totalPedido = totalPedido + Double.parseDouble(productos.get(i).precio) * productos.get(i).cantidad;
        }

        tv_suma_total.setText(""+totalPedido);

    }
}