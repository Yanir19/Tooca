package com.example.yanir.tooca;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
/**
 * Created by Jesus on 4/18/2015.
 */
public class Establecer_alarma  extends ActionBarActivity {
    // This is a handle so that we can call methods on our service
    private static ScheduleClient scheduleClient;
    // This is the date picker used to select the date for our notification
    private DatePicker picker;
    private TimePicker timepicker;
    DatePickerDialog.OnDateSetListener d;
    TimePickerDialog.OnTimeSetListener e;
    DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
   // DateFormat formatoHora = new SimpleDateFormat("HH:mm");
    Button fechaCalen;
    TextView fechaCalenTxt;
    Button horaExamenBtn;
    TextView horaExamenTxt;

    Manejador_BD BD;
    Calendar calendarioFechaExamen= Calendar.getInstance();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.establecer_alarma);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BD = new Manejador_BD(this);
        //Se verifica la version de la API que dispone el usuario
        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


        // Create a new service client and bind our activity to this service
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();



        d= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendarioFechaExamen.set(Calendar.YEAR, year);
                calendarioFechaExamen.set(Calendar.MONTH, monthOfYear);
                calendarioFechaExamen.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                update();
            }
        };
        e= new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendarioFechaExamen.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendarioFechaExamen.set(Calendar.MINUTE, minute);
                updateTime();
            }
        };


        fechaCalenTxt = (TextView)findViewById(R.id.fechaCalenTxt);
        fechaCalenTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });
        fechaCalen = (Button)findViewById(R.id.fechaCalen);
        fechaCalen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });


        horaExamenTxt = (TextView)findViewById(R.id.horaExamenTxt);
        horaExamenTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();

            }
        });
        horaExamenBtn = (Button)findViewById(R.id.horaExamenBtn);
        horaExamenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();

            }
        });


    }

    /**
     * This is the onClick called from the XML to set a new notification
     */
    public void onDateSelectedButtonClick(View v){
        // Get the date from our datepicker

        // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        BD.modificarNotificacionFecha(formato.format(calendarioFechaExamen.getTime()),0);
        scheduleClient.setAlarmForNotification(calendarioFechaExamen);

        Toast_Personalizado mensajeScreenshot;

        mensajeScreenshot = new Toast_Personalizado(this,"Tu alarma ha sido cambiada exitosamente!",Toast.LENGTH_SHORT);

        mensajeScreenshot.show();

       // Notas_apuntes.refrescarCaldroid();
        setResult(2);
        finish();

    }

    public static void nuevaAlarmaFutura(Calendar c){
        scheduleClient.setAlarmForNotification(c);
    }


    @Override
    protected void onStop() {
        // When our activity is stopped ensure we also stop the connection to the service
        // this stops us leaking our activity into the system *bad*
        if(scheduleClient != null)
            scheduleClient.doUnbindService();
        super.onStop();
    }


    public void setDate(){
        new DatePickerDialog(Establecer_alarma.this,d,calendarioFechaExamen.get(Calendar.YEAR),calendarioFechaExamen.get(Calendar.MONTH),calendarioFechaExamen.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void setTime(){
        new TimePickerDialog(Establecer_alarma.this,e,calendarioFechaExamen.get(Calendar.HOUR_OF_DAY),calendarioFechaExamen.get(Calendar.MINUTE),true).show();
    }


    public void update (){
        fechaCalenTxt.setText(formato.format(calendarioFechaExamen.getTime()));
    }
    public void updateTime (){
        horaExamenTxt.setText(calendarioFechaExamen.get(Calendar.HOUR_OF_DAY)+":"+calendarioFechaExamen.get(Calendar.MINUTE));
       // horaExamenTxt.setText(formatoHora.format(calendarioFechaExamen));
    }
}
