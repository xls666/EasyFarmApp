package com.example.app_easyfarma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Inicializar variables

    DrawerLayout drawerLayout;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById((R.id.botonInicio));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirIniciarSesion();
            }
        });

        //Asignar variables
        drawerLayout =findViewById(R.id.drawer_layout);

    }

    public void  AbrirIniciarSesion(){
        Intent intent = new Intent(this,Login.class);
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

    public void ClickModColaborador_crud(View view){
        //Redireccionar actividad a aboutus
        redirectActivity(this,ModColaborador_crud.class);
    }

    public void ClickLogout(View view){
        //Cerrar app
        logout(this);

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


    public static void redirectActivity(Activity activity, Class aClass) {
        //inicializar intent
        Intent intent = new Intent(activity, aClass);
        //Configurar aviso
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Iniciar actividad
            activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Cerrar drawer
        closeDrawer(drawerLayout);
    }
}