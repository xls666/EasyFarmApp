package com.example.app_easyfarma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DetallesProducto extends AppCompatActivity implements View.OnClickListener {

    TextView tvDetalles;
    TextView ProductoCantidad;
    TextView ProductoTotal;
    Producto producto;

    //Producto _producto;

    //Botones
    Button menos;
    Button mas;
    Button agregar;

    ArrayList<Producto> productos ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        producto = (Producto) getIntent().getSerializableExtra("producto");

        tvDetalles = findViewById(R.id.tvDetalles);
        agregar = findViewById(R.id.btn_agregar);
        menos = findViewById(R.id.btnmenos);
        mas = findViewById(R.id.btnmas);
        ProductoCantidad = findViewById(R.id.txtcant);
        ProductoTotal = findViewById(R.id.txttotal);

        tvDetalles.setText("ID: " + producto.getId() + "\n\nNombre: " + producto.getProducto() + "\n\nPrecio: " + producto.getPrecio());

        //Funciones
        mas.setOnClickListener(this);
        menos.setOnClickListener(this);
        agregar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnmas) {
            String _cantidad = ProductoCantidad.getText().toString();
            //_cantidad=_cantidad.replace(",","").replace("#","").replace(".","");
            int Incremento = Integer.parseInt(_cantidad) + 1;
            ProductoCantidad.setText(String.valueOf(Incremento));
            CalculaTotal(Incremento);
        } else if (id == R.id.btnmenos) {
            String _cantidad = ProductoCantidad.getText().toString();
            //_cantidad=_cantidad.replace(",","").replace("#","").replace(".","");
            int Incremento = Integer.parseInt(_cantidad) - 1;
            if (Incremento < 0)
                Incremento = 0;
            ProductoCantidad.setText(String.valueOf(Incremento));
            CalculaTotal(Incremento);
        } else if (id == R.id.btn_agregar) {

            productos = ((MyApplication) this.getApplication()).getProducts();

            if (productos == null) {

               productos = new ArrayList<>();
            }

            int cantidad = Integer.parseInt(ProductoCantidad.getText().toString());
            double total = cantidad * Float.parseFloat(producto.precio);

            producto = (Producto) getIntent().getSerializableExtra("producto");

            producto.setCantidad(cantidad);
            producto.setTotal(total);

            productos.add(producto);

            ((MyApplication) this.getApplication()).setProducts(productos);

            Snackbar.make(v, producto.getProducto() + " " + "añadido al carrito", Snackbar.LENGTH_LONG).show();

        }
    }

    ;
 /*   public void afterTextChanged(View editable) {
        int id =editable.getId();
        if (id == R.id.txtcant) {
            String _cantidad = ProductoCantidad.getText().toString();
            _cantidad = _cantidad.replace(",", "").replace("#", "").replace(".", "");
            int Incremento = Integer.parseInt(_cantidad);
            //CalculaTotal(Incremento);
        }
    }*/


    void CalculaTotal(int cantidad) {
        double resultado = cantidad * Double.parseDouble(producto.getPrecio());
        ProductoTotal.setText(Double.toString(resultado));

    }


}