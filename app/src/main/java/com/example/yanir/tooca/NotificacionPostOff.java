package com.example.yanir.tooca;

/**
 * Created by LjnozZ on 06/05/2015.
 */

    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.Date;

    import android.app.Activity;
    import android.app.AlarmManager;
    import android.app.PendingIntent;
    import android.content.Intent;
    import android.os.Bundle;

    public class NotificacionPostOff extends Activity
    {

        Manejador_BD BD;

        private PendingIntent pendingIntent;

        @Override
        public void onCreate(Bundle savedInstanceState)
        {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.autoexamen);

            Calendar calendar = Calendar.getInstance();
            BD = new Manejador_BD(this);
            ArrayList<Date> fechas = new ArrayList<Date>();
            fechas=BD.buscarFechasDeAlarmas();
            for(int i = 0;i<fechas.size();i++){
                calendar.setTime(fechas.get(i));
                System.out.println("FECHAS EN EL NOTICICACIONPOSTOFF SISTEMA"+fechas.get(i));
            }

            System.out.println("FECHAS EN EL NOTICICACIONPOSTOFF SISTEMA FECHA FINAL DEL CALENDAR"+calendar.getTime());
            Intent myIntent = new Intent(NotificacionPostOff.this, MyBroadcastReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(NotificacionPostOff.this, 0, myIntent,0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        } //end onCreate
    }

