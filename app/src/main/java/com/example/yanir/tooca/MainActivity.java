package com.example.yanir.tooca;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Manejador_BD BD = new Manejador_BD(this);
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstFun", true);

       // Se verifica si es la primera vez del usuario

        if (isFirstRun) {
            //Iniciamos el registro
            Toast.makeText(MainActivity.this, "Bienvenida", Toast.LENGTH_LONG).show();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstFun", false).commit();
            Intent intent = new Intent(MainActivity.this, Datos_Usuario_Activity.class);
            startActivity(intent);
        } else {
            //Iniciamos el menu
            setContentView(R.layout.menu);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            //Se verifica la version de la API que dispone el usuario
            if (Build.VERSION.SDK_INT < 19) {
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
                layoutParams.height = 0;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
            Toast.makeText(MainActivity.this, "No First run", Toast.LENGTH_LONG).show();


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*Accion que se ejecuta al presionar el boton calendario*/
    public void abrirCalendario(View view) {
        Intent int1 = new Intent(this, Calendario.class);
        startActivity(int1);
    }

    public void abrirAjustes(View view) {
        Intent intent = new Intent(this, Ajustes.class);
        startActivity(intent);
    }

}
