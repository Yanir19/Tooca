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

    private Button UsuarioBtn;
    private Button botoncalendario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Manejador_BD BD = new Manejador_BD(this);
        /*Aqui revisamos la version de SDK que esta usando el usuario y se le asignan las
        * restricciones de estilo correspondiente a cada una
        */
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstFun", true);
        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ;
        }
        /*Aqui revisamos si es la primera vez que entra el usuario
        * Si es verdad se ejecutara la actividad de registro y
        * Si es falso entrara normalmente al menu
        */
        if (isFirstRun) {
            // setContentView(R.layout.registro);
            Toast.makeText(MainActivity.this, "First run", Toast.LENGTH_LONG).show();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstFun", false).commit();
            Intent intent = new Intent(MainActivity.this, Datos_Usuario_Activity.class);
            System.out.println("Voy a la nueva vista");

            //Iniciamos la nueva actividad

            startActivity(intent);
        } else {
            setContentView(R.layout.menu);
            Toast.makeText(MainActivity.this, "No First run", Toast.LENGTH_LONG).show();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


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


}
