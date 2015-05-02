package com.example.yanir.tooca;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Jesus on 4/4/2015.
 */
public class Notas_principal extends ActionBarActivity {

    ListView list;
    String [] titulos;
    SimpleDateFormat formato;

    TextView fecha;
    ImageButton adelante;
    ImageButton atras;
    int dayShift;
    Calendar c;
    String fecha_seleccionada;

    int[] images = {R.drawable.ic_apunte,R.drawable.ic_smiley};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_principal);
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

        Locale locale = new Locale("es", "ES");
        final SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM , yyyy ", locale);
        formato = new SimpleDateFormat("dd-MM-yyyy");

        c = Calendar.getInstance();

        fecha= (TextView) findViewById(R.id.fechaNotas);
        fecha.setText(formatter.format(c.getTime()));


        adelante = (ImageButton) findViewById(R.id.diaAdelante);
        adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayShift = 1;
                c.add(Calendar.DAY_OF_YEAR,dayShift);
                fecha.setText(formatter.format(c.getTime()));
                fecha_seleccionada = formatter.format(c.getTime());
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

            }
        });


       Resources res =  getResources();
       titulos = res.getStringArray(R.array.notes_titles);
       list = (ListView) findViewById(R.id.listViewNotas);
       NotasAdapter adapter = new NotasAdapter(this,titulos,images);
       list.setAdapter(adapter);





        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                if(arg2==0) {
                    abrirApuntes(arg1);
                    Toast.makeText(getBaseContext(), titulos[arg2], Toast.LENGTH_LONG).show();
                }

                if(arg2==1) {
                    abrirAnimo(arg1);
                    Toast.makeText(getBaseContext(), titulos[arg2], Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void abrirApuntes(View view) {
        Intent intent = new Intent(this, Notas_apuntitos.class);
        intent.putExtra("fecha_actual",formato.format(c.getTime()));
        startActivity(intent);
    }

    public void abrirAnimo(View view) {
        Intent intent = new Intent(this, Animo.class);
        intent.putExtra("fecha_actual",formato.format(c.getTime()));
        startActivity(intent);
    }




}


class NotasAdapter extends ArrayAdapter<String>{
    Context c;
    int[] images;
    String[] titleArray;
    NotasAdapter(Context context, String[] titles, int images[]){
        super(context,R.layout.single_row_notes,R.id.textViewNotas,titles);
        this.c = context;
        this.images = images;
        this.titleArray = titles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_notes,parent,false);

        ImageView myImage = (ImageView)row.findViewById(R.id.imageView);
        TextView myTitle = (TextView) row.findViewById(R.id.textViewNotas);

        myImage.setImageResource(images[position]);
        myTitle.setText(titleArray[position]);

        return row;
    }


}

