package com.example.app_easyfarma;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetallesUsuario extends AppCompatActivity {

    TextView tvDetalles;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_usuario);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        tvDetalles = findViewById(R.id.tvDetalles);

        tvDetalles.setText("ID: "+usuario.getId()+"\n\nNombre: "+usuario.getProducto());

    }
}