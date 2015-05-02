package com.example.yanir.tooca;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Jesus on 4/17/2015.
 */
public class Notas_apuntitos extends ActionBarActivity {

    Manejador_BD BD;
    EditText apuntes ;
    TextView fecha;
    Button enviarApunte;
    Bundle bundle;
    Calendar c;
    SimpleDateFormat formato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notas_apuntitos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BD = new Manejador_BD(this);
        bundle = getIntent().getExtras();
        formato = new SimpleDateFormat("dd-MM-yyyy");
        Locale locale = new Locale("es", "ES");
        final SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM , yyyy ", locale);
        try {
            c = Calendar.getInstance();
            Date date = formato.parse(bundle.getString("fecha_actual"));
            c.setTime(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Toast.makeText(getBaseContext(), formatter.format(c.getTime()), Toast.LENGTH_LONG).show();




        apuntes = (EditText) findViewById(R.id.apuntes);
        apuntes.setText(BD.getApuntesbyFecha(bundle.getString("fecha_actual")), TextView.BufferType.EDITABLE);


        fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText(formatter.format(c.getTime()));

        enviarApunte = (Button) findViewById(R.id.enviarApunte);
        enviarApunte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BD.agregarNota(bundle.getString("fecha_actual"),apuntes,"triste");
            }
        });

    }
}
