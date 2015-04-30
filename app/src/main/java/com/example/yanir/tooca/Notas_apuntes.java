package com.example.yanir.tooca;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jesus on 4/5/2015.
 */
public class Notas_apuntes extends FragmentActivity {

    private CaldroidFragment caldroidFragment;
    EditText apuntes ;
    Manejador_BD BD;
    Button anadir_apunte;
    String fecha_seleccionada;
    private AlertDialog.Builder dialogBuilder;


    private void setCustomResourceForDates() {
        Integer diaDeExamen = 5;
        Integer MAX_POST_EXAMEN = 3;
        Calendar cal = Calendar.getInstance();

        // Min date is last 7 days
        //cal.add(Calendar.DATE, 5);
        cal.set(Calendar.DAY_OF_MONTH,diaDeExamen);
        Date blueDate = cal.getTime();

        // Max date is next 7 days
        cal = Calendar.getInstance();
        cal.set(Calendar.DATE, diaDeExamen+MAX_POST_EXAMEN);
        Date greenDate = cal.getTime();

        if (caldroidFragment != null) {
            caldroidFragment.setBackgroundResourceForDate(R.color.md_pink_100,
                    blueDate);
            caldroidFragment.setBackgroundResourceForDate(R.color.md_red_100,
                    greenDate);
            //Cambiar color de letras para fecha especifica en el calendario
            //caldroidFragment.setTextColorForDate(R.color.caldroid_gray, blueDate);
            //caldroidFragment.setTextColorForDate(R.color.caldroid_darker_gray, greenDate);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notas_apuntes);
         BD = new Manejador_BD(this);
        anadir_apunte = (Button)findViewById(R.id.enviarApunte);
        anadir_apunte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                agregarNota(fecha_seleccionada);

            }
        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        apuntes = (EditText) findViewById(R.id.apuntes);


        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        caldroidFragment = new CaldroidFragment();




        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR,false);

            // Uncomment this to customize startDayOfWeek
            // args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
            // CaldroidFragment.TUESDAY); // Tuesday

            // Uncomment this line to use Caldroid in compact mode
            args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

            caldroidFragment.setArguments(args);
        }

        setCustomResourceForDates();

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.caldroide, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                fecha_seleccionada = formatter.format(date);
                caldroidFragment.setBackgroundResourceForDate(R.color.md_pink_50,date);
                caldroidFragment.refreshView();
                fecha_seleccionada = formatter.format(date).toString();
                apuntes.setText(BD.getApuntesbyFecha(formatter.format(date)), TextView.BufferType.EDITABLE);


            }

            @Override
            public void onChangeMonth(int month, int year) {
                /*String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click " + formatter.format(date),
                        Toast.LENGTH_SHORT).show();
                alarmaDialogo();
            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {
                    /*Toast.makeText(getApplicationContext(),
                            "Caldroid view is created", Toast.LENGTH_SHORT)
                            .show();
                    */
                }
            }

        };


        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);




        //Se verifica la version de la API que dispone el usuario
        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


    }

    public void agregarNota(String date){

        BD.agregarNota(date,apuntes,"triste");
        //BD.update_notas("25-04-2015",apuntes.getText().toString());

        Toast.makeText(Notas_apuntes.this,BD.getNotas(), Toast.LENGTH_LONG).show();

    }
    public void establecer_alarma(View view){

        Intent intent = new Intent(this, Establecer_alarma.class);
        startActivity(intent);

    }

    private void alarmaDialogo(){
        //Variables
        dialogBuilder = new AlertDialog.Builder(this);

        //Proceso
        dialogBuilder.setTitle("Alarma");
        dialogBuilder.setMessage("Deberas tocarte los dias 5 de cada mes");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogBuilder.setNegativeButton("Cambiar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialogoAlarma = dialogBuilder.create();
        dialogoAlarma.show();

    }






}