package com.example.yanir.tooca;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Button UsuarioBtn;
    private Button botoncalendario;
    public Layout mControls;
    private Manejador_BD BD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        BD = new Manejador_BD(this);
//        BD.Push_BD("insert into usuarios values (1,'Yanir','Castillo','11-04-2015','Poz','Poz'); " );
//        BD.Push_BD("insert into centros_asistenciales values (1,1,'Chilemex','8.304223','-62.724277','cancer');");

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstFun", true);
        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
        //    ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        //    layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ;
        }
        /*Aqui revisamos si es la primera vez que entra el usuario
        * Si es verdad se ejecutara la actividad de registro y
        * Si es falso entrara normalmente al menu
        */

       // Se verifica si es la primera vez del usuario

        if (isFirstRun) {
            //Iniciamos el registro
            Toast.makeText(MainActivity.this, "Bienvenida", Toast.LENGTH_LONG).show();
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

    public void abrirAjustes(View view) {
        Intent intent = new Intent(this, Ajustes.class);
        startActivity(intent);
    }

    public void AbrirMapas(View view) {
        Intent intent = new Intent(MainActivity.this, Centros_Asistenciales.class);
        startActivity(intent);
    }

    public void abrirNotas(View view) {
        Intent intent = new Intent(this, Notas_principal.class);
        startActivity(intent);
    }

    public void abrirAutoexamen(View view) {
        Intent intent = new Intent(MainActivity.this, Autoexamen.class);
        startActivity(intent);
    }

}
