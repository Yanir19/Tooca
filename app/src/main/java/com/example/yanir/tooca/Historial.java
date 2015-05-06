package com.example.yanir.tooca;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Miguel on 05/05/2015.
 */
public class Historial extends FragmentActivity{

    private ScrollView scrollView;
    private LinearLayout lay;
    private Manejador_BD BD;
    private String mensaje;
    private Variables VAR;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.historial);


        // Se encuentran los objetos a utilizar del frameLayout historial.xml
        scrollView = (ScrollView)findViewById(R.id.scrollViewHistorial);
        lay = (LinearLayout)findViewById(R.id.layoutHistorial);

        BD = new Manejador_BD(this);

        // Se extraen de la BD los resultados de los examenes realizados
        String queryResultados = "SELECT * FROM examen";
        Cursor consulta;
        consulta = BD.Get_BD(queryResultados);

        // Se colocan en pantalla todos los resultados de examenes realizados

        if (consulta.moveToFirst()){

        }else
        if(!consulta.moveToFirst()){

        }

    // ----------------- SE SETEAN LAS DIMENSIONES DE LOS ELEMENTOS CON LOS CUALES TRABAJAREMOS ----------------- //

        // Tamaño del Lauyout Contenedor
        final LinearLayout.LayoutParams parametroLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 200
        );

        // Tamaño del TextView fecha
        final TableRow.LayoutParams parametroTextViewFecha = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, 55
        );

        // Tamaño del LinearLayout resultado
        final LinearLayout.LayoutParams parametroLayoutResultado = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 60
        );

        // Tamaño del TextView nombre test
        final TableRow.LayoutParams parametroNombreTest = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 0.3f
        );

        // Tamaño del FrameLayout divisor
        final FrameLayout.LayoutParams parametroFrameDivisor = new FrameLayout.LayoutParams(
                4, FrameLayout.LayoutParams.MATCH_PARENT
        );
        parametroFrameDivisor.setMargins(0,10,0,10);

        // Tamaño del TextView resultado test
        final TableRow.LayoutParams parametroResultadoTest = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 0.7f
        );


        int cantidad = 0;
        cantidad = 2;
        int test=0;

    // ----------------- Se declaran los arreglos de TextView y LinearLayout ----------------- //

        LinearLayout[] layo = new LinearLayout[cantidad];
        TextView tv[] = new TextView[cantidad];
        LinearLayout[][] layoh = new LinearLayout[9][cantidad];
        TextView[][] tv1 = new TextView[9][cantidad];
        TextView[][] frame = new TextView[9][cantidad];
        TextView[][] tv2 = new TextView[9][cantidad];


        for (int i=0; i<cantidad; i++) {

         // <LinearLayout>
            layo[i] = new LinearLayout(this);
            layo[i].setOrientation(LinearLayout.VERTICAL);
            layo[i].setLayoutParams(parametroLayout);

             // <TextView>
                tv[i] = new TextView(this);
                tv[i].setLayoutParams(parametroTextViewFecha);
                tv[i].setText("05-05-2015");
                tv[i].setTextColor(getResources().getColor(R.color.md_white_1000));
                tv[i].setBackgroundColor(getResources().getColor(R.color.md_pink_100));
                tv[i].setTextSize(20);
                tv[i].setTypeface(Typeface.create("Verdana", Typeface.NORMAL));
                tv[i].setGravity(Gravity.CENTER_VERTICAL);
                tv[i].setPadding(20, 0, 0, 0);
             // </TextView>

                layo[i].addView(tv[i]);     // Se añade el TextView al Layout principal

             for (test=0; test<9; test++) {

                 // <LinearLayout>
                 layoh[test][i] = new LinearLayout(this);
                 layoh[test][i].setOrientation(LinearLayout.HORIZONTAL);
                 layoh[test][i].setLayoutParams(parametroLayoutResultado);
                 layoh[test][i].setWeightSum(1);

                     // <TextView>
                     tv1[test][i] = new TextView(this);
                     tv1[test][i].setLayoutParams(parametroNombreTest);
                     tv1[test][i].setText("TEST 1");
                     tv1[test][i].setTextColor(getResources().getColor(R.color.md_black_1000));
                     tv1[test][i].setTextSize(18);
                     tv1[test][i].setTypeface(Typeface.create("Verdana", Typeface.NORMAL));
                     tv1[test][i].setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                     tv1[test][i].setPadding(0, 0, 30, 0);
                     // </TextView>

                    layoh[test][i].addView(tv1[test][i]);     // Se añade el TextView al Layout secundario

                     // <TextView>
                     frame[test][i] = new TextView(this);
                     frame[test][i].setLayoutParams(parametroFrameDivisor);
                     frame[test][i].setBackgroundColor(getResources().getColor(R.color.md_black_1000));
                     // </TextView>

                     layoh[test][i].addView(frame[test][i]);   // Se añade el TextView al Layout secundario

                     // <TextView>
                     tv2[test][i] = new TextView(this);
                     tv2[test][i].setLayoutParams(parametroResultadoTest);
                     tv2[test][i].setText("Presenta Sintoma");
                     tv2[test][i].setTextColor(getResources().getColor(R.color.md_black_1000));
                     tv2[test][i].setTextSize(18);
                     tv2[test][i].setTypeface(Typeface.create("Verdana", Typeface.NORMAL));
                     tv2[test][i].setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                     tv2[test][i].setPadding(30, 0, 0, 0);
                     // </TextView>

                 layoh[test][i].addView(tv2[test][i]);     // Se añade el TextView al Layout secundario

                 // </LinearLayout>

                 layo[i].addView(layoh[test][i]);   // Se añade al Layout secundario al layout principal
             }

         // </LinearLayout>

            lay.addView(layo[i]);       // Se añade al Layout principal a la pantalla

        }  //-- FIN FOR --//
    }

}
