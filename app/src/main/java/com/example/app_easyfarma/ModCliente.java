package com.example.app_easyfarma;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ModCliente extends AppCompatActivity {
    DrawerLayout drawerLayout;

    //Variables
    EditText etBuscador;
    RecyclerView rvLista;
    AdaptadorProductos adaptador;
    FloatingActionButton fabCarrito;
    List<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modcliente);

        fabCarrito = findViewById(R.id.fabCarrito);
        etBuscador = findViewById(R.id.etBuscador);
        etBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                filtrar(s.toString());
            }
        });

        fabCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),CarritoActivity.class);
                startActivity(intent);
            }
        });

        rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new GridLayoutManager(this, 1));

        listaProductos = new ArrayList<>();

        obtenerUsuarios();

        adaptador = new AdaptadorProductos(ModCliente.this, listaProductos);
        rvLista.setAdapter(adaptador);

        drawerLayout = findViewById(R.id.drawer_layout);

    }

    public void obtenerUsuarios() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_USUARIOS),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Productos");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                listaProductos.add(
                                        new Producto(
                                                jsonObject1.getString("id"),
                                                jsonObject1.getString("producto"),
                                                jsonObject1.getString("precio"),
                                                jsonObject1.getString("ubicacion")
                                        )
                                );
                            }

                            adaptador = new AdaptadorProductos(ModCliente.this, listaProductos);
                            rvLista.setAdapter(adaptador);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );

        requestQueue.add(stringRequest);
    }

    public void filtrar(String texto) {
        ArrayList<Producto> filtrarLista = new ArrayList<>();

        for(Producto producto : listaProductos) {
            if(producto.getProducto().toLowerCase().contains(texto.toLowerCase())) {
                filtrarLista.add(producto);
            }
        }

        adaptador.filtrar(filtrarLista);
    }


    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void ClickModCliente(View view){
        recreate();
    }

    public void ClickModColaborador(View view){
        MainActivity.redirectActivity(this,ModColaborador.class);
    }

    public void ClickLogout (View view){
        MainActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }
}