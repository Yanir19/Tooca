package com.example.yanir.tooca;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Datos_Usuario_Activity extends ActionBarActivity {


    Button Aceptar;
    EditText txtNombre ;
    EditText txtApellido ;
    EditText txtFecha;
    EditText txtDireccion1;
    EditText txtDireccion2;
    EditText Fechatxt;
    Manejador_BD BD;
    DatePicker DP;
    Button Fecha ;
    DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    Calendar calendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos__usuario_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


        Aceptar = (Button)findViewById(R.id.AceptarBtn);
        txtNombre = (EditText)findViewById(R.id.NombreTxt);
        txtApellido = (EditText)findViewById(R.id.ApellidoTxt);
        txtFecha = (EditText)findViewById(R.id.FechaTxt);
        txtDireccion1 = (EditText)findViewById(R.id.Direccion_1Txt);
        txtDireccion2 = (EditText)findViewById(R.id.Direccion_2txt);
        BD = new Manejador_BD(this);
        Fecha = (Button)findViewById(R.id.Fechabtn);
        Fechatxt = (EditText)findViewById(R.id.FechaTxt);
        Cursor c = BD.Get_BD("select * from usuarios;");
        //Nos aseguramos de que existe al menos un registro
                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        txtNombre.setText(c.getString(0));
                        txtApellido.setText(c.getString(1));
                    } while(c.moveToNext());
                }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_datos__usuario_, menu);
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



    public void clickAceptar (View v){

        System.out.println("Yo entre a  hacer la base de datos ");
        Manejador_BD BD = new Manejador_BD(this);
        String sentencia = " INSERT INTO usuarios (nombre,apellido,fecha,Direccion1,Direccion2) VALUES ('"+txtNombre.getText()+"', " +
                " '"+txtApellido.getText()+"' , '"+txtFecha.getText()+"' , '"+txtDireccion1.getText()+"' , " +
                " '"+txtDireccion2.getText()+"' ); ";
        BD.Push_BD(sentencia);
        Intent intent = new Intent(Datos_Usuario_Activity.this, MainActivity.class);

    }

    public void MostrarFecha (View v){
        setDate();
    }

    public void update (){
        Fechatxt.setText(formato.format(calendar.getTime()));
    }

    public void setDate(){
        new DatePickerDialog(Datos_Usuario_Activity.this,d,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    DatePickerDialog.OnDateSetListener d= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            update();
        }
    };

}
