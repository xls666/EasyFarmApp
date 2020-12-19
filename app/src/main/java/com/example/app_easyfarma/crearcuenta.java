package com.example.app_easyfarma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
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

public class crearcuenta extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private Button button;
    Button button2;
    private EditText nombres,apellidos,correo,DNI,contrasena;
    String URL_REGIST="https://giancaproject1.000webhostapp.com/registro_user.php";
    String str_nombres,str_apellidos,str_correo,str_DNI,str_contrasena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearcuenta);
        nombres = findViewById(R.id.nombres);
        apellidos = findViewById(R.id.apellidos);
        DNI = findViewById(R.id.DNI);
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);

        //Asignar variables
        drawerLayout =findViewById(R.id.drawer_layout);

        button = (Button) findViewById(R.id.CrearCuenta);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearCuenta();
                //CuentaCreada();
            }
        });
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



    public void CrearCuenta(){

        if(nombres.getText().toString().equals("")){
            Toast.makeText(this,"Ingresa el nombre",Toast.LENGTH_LONG).show();
        }
        else if(apellidos.getText().toString().equals("")) {
            Toast.makeText(this, "Ingresa el apellido", Toast.LENGTH_LONG).show();
        }
        else if(DNI.getText().toString().equals("")) {
            Toast.makeText(this, "Ingresa el DNI", Toast.LENGTH_LONG).show();
        }
        else if(correo.getText().toString().equals("")) {
            Toast.makeText(this, "Ingresa el correo", Toast.LENGTH_LONG).show();
        }
        else if(contrasena.getText().toString().equals("")) {
            Toast.makeText(this, "Ingresa la contraseña", Toast.LENGTH_LONG).show();
        }
        else{
            str_nombres = nombres.getText().toString().trim();
            str_apellidos = apellidos.getText().toString().trim();
            str_DNI = DNI.getText().toString().trim();
            str_correo = correo.getText().toString().trim();
            str_contrasena = contrasena.getText().toString().trim();
            StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(crearcuenta.this,response,Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(crearcuenta.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("apellidos",str_apellidos);
                    params.put("nombres",str_nombres);
                    params.put("DNI",str_DNI);
                    params.put("correo",str_correo);
                    params.put("contraseña",str_contrasena);
                    return params;
                }
            };
            RequestQueue requestQueue =Volley.newRequestQueue( crearcuenta.this);
            requestQueue.add(request);
        }

    }

    //public void CuentaCreada(){

    //VOLVER AL MENU DE LOGIN
    //Intent intent = new Intent(this,Login.class);
    //startActivity(intent);

    //}

}
