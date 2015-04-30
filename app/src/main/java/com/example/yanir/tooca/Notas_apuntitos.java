package com.example.yanir.tooca;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Jesus on 4/17/2015.
 */
public class Notas_apuntitos extends FragmentActivity {

    private CaldroidFragment caldroidFragment;

    Manejador_BD BD;
    EditText apuntes ;
    TextView fecha;
    ImageButton adelante;
    ImageButton atras;
    int dayShift;
    Calendar c;
    String fecha_seleccionada;
    Button enviarApunte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notas_apuntitos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        BD = new Manejador_BD(this);
        //final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Locale locale = new Locale("es", "ES");
        final SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM , yyyy ", locale);
        final SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        c = Calendar.getInstance();

        apuntes = (EditText) findViewById(R.id.apuntes);

        fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText(formatter.format(c.getTime()));

        enviarApunte = (Button) findViewById(R.id.enviarApunte);
        enviarApunte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BD.agregarNota(formato.format(c.getTime()),apuntes,"triste");
            }
        });

        adelante = (ImageButton) findViewById(R.id.diaAdelante);
        adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayShift = 1;
                c.add(Calendar.DAY_OF_YEAR,dayShift);
                fecha.setText(formatter.format(c.getTime()));
                fecha_seleccionada = formatter.format(c.getTime());
                apuntes.setText(BD.getApuntesbyFecha(formato.format(c.getTime())), TextView.BufferType.EDITABLE);
                Toast.makeText(getApplicationContext(),BD.getNotasbyFecha(formato.format(c.getTime())), Toast.LENGTH_SHORT).show();
            }
        });

        atras = (ImageButton) findViewById(R.id.diaAtras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayShift = -1;
                c.add(Calendar.DAY_OF_YEAR,dayShift);
                fecha.setText(formatter.format(c.getTime()));
                fecha_seleccionada = formato.format(c.getTime());
                apuntes.setText(BD.getApuntesbyFecha(formato.format(c.getTime())), TextView.BufferType.EDITABLE);
            }
        });



    }
}
