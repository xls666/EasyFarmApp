package com.example.app_easyfarma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class buscar_prod_cliente extends AppCompatActivity {

    private Button button,button2;
    private TextView producto;
    private EditText nom_producto;
    /*private ImageButton ed_mic;*/
    RequestQueue requestQueue;
    private static final int REQ_CODE_SPEECH_INPUT=100;
    private static final String TAG = MainActivity.class.getSimpleName();

    String str_producto;

    String URL="https://giancaproject1.000webhostapp.com/listproducts.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_prod_cliente);


        producto = findViewById(R.id.resultTextView);
        nom_producto = findViewById(R.id.producto);
        button = findViewById(R.id.agregar_carrito_compra);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetallesCompra();
            }



        });

        button2 =findViewById(R.id.btn_buscarprod);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();

            }
        });

        /*ed_mic=findViewById(R.id.mic);
        ed_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarEntradaVoz();
            }
        });*/


    }
    private void postData()
    {
        str_producto=nom_producto.getText().toString().trim();


        StringRequest request = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        producto.setText(response);

                        Toast.makeText(buscar_prod_cliente.this,response,Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(buscar_prod_cliente.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }

        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();


                params.put("producto",str_producto);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue( buscar_prod_cliente.this);
        requestQueue.add(request);

    }
    public void getData(){

        try {

            JSONObject object = new JSONObject();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    producto.setText("Resposne : " + response.toString());
                    Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void iniciarEntradaVoz() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola dime lo que sea");


        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        }
        catch (ActivityNotFoundException e){


        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case REQ_CODE_SPEECH_INPUT:{
                if (resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    nom_producto.setText(result.get(0));

                }
                break;

            }
        }

    }

    private void DetallesCompra(){
        Intent intent = new Intent(this,mod_prod_cliente.class);
        startActivity(intent);
    }
}
