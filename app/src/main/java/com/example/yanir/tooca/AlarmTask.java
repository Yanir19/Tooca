package com.example.yanir.tooca;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Set an alarm for the date passed into the constructor
 * When the alarm is raised it will start the NotifyService
 *
 * This uses the android build in alarm manager *NOTE* if the phone is turned off this alarm will be cancelled
 *
 * This will run on it's own thread.
 *
 * @author paul.blundell
 */
/**
 * Created by Jesus on 4/18/2015.
 */
public class AlarmTask implements Runnable{
    // The date selected for the alarm
    private final Calendar date;
    // The android system alarm manager
    private final AlarmManager am;
    // Your context to retrieve the alarm manager from
    private final Context context;

    public AlarmTask(Context context, Calendar date) {
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.date = date;
    }

    @Override
    public void run() {
        Manejador_BD BD = new Manejador_BD(context);
        Random r = new Random(System.currentTimeMillis());
        NotifyService.NOTIFICATION = r.nextInt(300)+1;
        // Request to start are service when the alarm date is upon us
        // We don't start an activity as we just want to pop up a notification into the system bar not a full activity
        Intent intent = new Intent(context, NotifyService.class);
        intent.putExtra(NotifyService.INTENT_NOTIFY, true);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);



        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm");
System.out.println("PUSEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE LA ALARMAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("PUseeeeee Alarma: "+formato.format(date.getTime()));


       // BD.agregarNotificacion(formato.format(date.getTime()),0);
        BD.modificarNotificacionFecha(formato.format(date.getTime()),0);

        // Sets an alarm - note this alarm will be lost if the phone is turned off and on again
        am.set(AlarmManager.RTC, date.getTimeInMillis(), pendingIntent);


    }
}
