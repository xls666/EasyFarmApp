package com.example.app_easyfarma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ModColaborador_crud extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    Button btnDelete, btnEdit;
    EditText etProducto, etPrecio, etUbicacion, etId;
    String id;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_colaborador_crud);

        drawerLayout = findViewById(R.id.drawer_layout);

        requestQueue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            id =extras.getString("id");
        }

        initUI();

        readUser();

        btnDelete.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }

    private void initUI() {
        etProducto = findViewById(R.id.etProducto);
        etPrecio= findViewById(R.id.etPrecio);
        etUbicacion= findViewById(R.id.etUbicacion);
        etId= findViewById(R.id.etId);

        btnDelete =findViewById(R.id.btnDelete);
        btnEdit=findViewById(R.id.btnEdit);
    }

    private void readUser() {
        String URL1 = "https://giancaproject1.000webhostapp.com/fetch.php?id=" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String producto, precio, ubicacion;
                        try {
                            producto = response.getString("producto");
                            precio = response.getString("precio");
                            ubicacion = response.getString("ubicacion");


                            etProducto.setText(producto);
                            etPrecio.setText(precio);
                            etUbicacion.setText(ubicacion);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onClick(View v) {
        int id =v.getId();

        if (id == R.id.btnEdit){

            String producto = etProducto.getText().toString().trim();
            String precio = etPrecio.getText().toString().trim();
            String ubicacion =etUbicacion.getText().toString().trim();


            updateUser(producto, precio, ubicacion);

        }else if (id == R.id.btnDelete){

            String idUser = etId.getText().toString().trim();
            removeUser(idUser);
        }
    }


    private void updateUser(final String producto, final String precio, final String ubicacion) {
        String URL1 = "https://giancaproject1.000webhostapp.com/edit.php";
        StringRequest stringRequest = new StringRequest(

                Request.Method.POST,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ModColaborador_crud.this, "Actualización satisfactoria", Toast.LENGTH_SHORT).show();
                    }
                },

                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("producto",producto);
                params.put("precio",precio);
                params.put("ubicacion",ubicacion);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void removeUser(final String idUser) {
        String URL1 = "https://giancaproject1.000webhostapp.com/delete.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", idUser);
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


    public void ClickLogout (View view){
        MainActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }



}