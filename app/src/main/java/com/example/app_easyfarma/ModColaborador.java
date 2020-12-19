package com.example.app_easyfarma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ModColaborador extends AppCompatActivity implements View.OnClickListener{

    EditText etProducto, etPrecio, etUbicacion, etId;
    Button btnCreate, btnFetch;
    RequestQueue requestQueue;

    private static final String URL ="https://giancaproject1.000webhostapp.com/save.php";

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_colaborador);
        requestQueue = Volley.newRequestQueue(this);

        drawerLayout = findViewById(R.id.drawer_layout);



        initUI();
        btnCreate.setOnClickListener(this);
        btnFetch.setOnClickListener(this);
    }

    @SuppressLint("WrongViewCast")
    private void initUI() {
        //EditText
        etProducto = findViewById(R.id.etProducto);
        etPrecio=findViewById(R.id.etPrecio);
        etUbicacion=findViewById(R.id.etUbicacion);

        etId=findViewById(R.id.etId);

        //Buttons
        btnCreate=findViewById(R.id.btnCreate);
        btnFetch=findViewById(R.id.btnFetch);
    }

    public void onClick(View v) {
        int id =v.getId();

        if (id == R.id.btnCreate) {
            String producto = etProducto.getText().toString().trim();
            String precio = etPrecio.getText().toString().trim();
            String ubicacion = etUbicacion.getText().toString().trim();

            createUser(producto, precio, ubicacion);
        }else if (id == R.id.btnFetch){

            Intent intent =new Intent(this, ModColaborador_crud.class);
            intent.putExtra("id", etId.getText().toString().trim());
            startActivity(intent);
        }

    }

    private void createUser(final String producto, final String precio, final String ubicacion) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ModColaborador.this, "Se registro el producto", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("producto", producto);
                params.put("precio", precio);
                params.put("ubicacion", ubicacion);


                return params;
            }

        };
        requestQueue.add(stringRequest);

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
        MainActivity.redirectActivity(this,ModCliente.class);
    }

    public void ClickModColaborador(View view){
        MainActivity.redirectActivity(this,ModColaborador.class);

    }

    public void ClickModColaborador_crud(View view){
        MainActivity.redirectActivity(this,ModColaborador_crud.class);

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