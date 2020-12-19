package com.example.app_easyfarma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    DrawerLayout drawerLayout;
    EditText edtUsuario, edtPassword;
    Button btnLogin;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        edtUsuario.setText("xxxxx@gmail.com");
        edtPassword.setText("123");

        button2 = (Button) findViewById(R.id.btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroCuenta();
            }
        });

        //Asignar variables
        drawerLayout =findViewById(R.id.drawer_layout);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUsuario(  "https://giancaproject1.000webhostapp.com/validar_usuario.php");
            }
        });
    }

    public void RegistroCuenta(){
        Intent intent = new Intent(this,crearcuenta.class);
        startActivity(intent);
    }

    public void ClickMenu(View view){
        //Abrir drawer
        openDrawer(drawerLayout);

    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Abrir drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //Cerrar drawer
        closeDrawer(drawerLayout);

    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Cerrar drawer Layout
        //Verificar condicion
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //CUAndo drawer esta abierto
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        //Recrear actividad
        recreate();
    }

    public void ClickModCliente(View view){
        redirectActivity(this,ModCliente.class);
    }



    public void ClickModColaborador(View view){
        //Redireccionar actividad a aboutus
        redirectActivity(this,ModColaborador.class);
    }

    public void ClickLogout(View view){
        //Cerrar app
        logout(this);

    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //inicializar intent
        Intent intent = new Intent(activity, aClass);
        //Configurar aviso
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Iniciar actividad
        activity.startActivity(intent);
    }

    public static void logout(final Activity activity) {
        //Inicializar mensaje de alerta
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Configurar titulo
        builder.setTitle("Salir");
        //Configurar mensaje
        builder.setMessage("Estas seguro de salir de la aplicaciòn?");
        //Boton respuesta afirmativa
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //Finalizar actividad
                activity.finishAffinity();
                //Salir de la aplicaciòn
                System.exit( 0);
            }
        });
        //Boton respuesta negativa
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(), ModCliente.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Email or Password Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("correo", edtUsuario.getText().toString());
                parametros.put("contraseña", edtPassword.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}


