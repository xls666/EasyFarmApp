package com.example.app_easyfarma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class ModColaborador_crud extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_colaborador_crud);

        drawerLayout = findViewById(R.id.drawer_layout);

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
        recreate();
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