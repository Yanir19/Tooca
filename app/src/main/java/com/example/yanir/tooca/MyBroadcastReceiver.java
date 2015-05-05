package com.example.yanir.tooca;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by LjnozZ on 05/05/2015.
 */


public class MyBroadcastReceiver extends BroadcastReceiver {

    private ScheduleClient scheduleClient;
    Manejador_BD BD;


    @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("SISTEMA OPERATIVO INFORMANDO DE UN BOOT EXITOSO");
            //BD = new Manejador_BD(context);
            ArrayList<Date> fechas = new ArrayList<Date>();
            //fechas=BD.buscarFechasDeAlarmas();

        /*for(int i = 0;i<fechas.size();i++){
            System.out.println("SISTEMA OPERATIVO INFORMANDO DE UN BOOT EXITOSO" + fechas.get(i));
        }
*/

        Calendar c = Calendar.getInstance();
        /*for(int i = 0;i<fechas.size();i++){
            c.setTime(fechas.get(i));
        }*/

        System.out.println("SISTEMA OPERATIVO INFORMANDO DE UN BOOT EXITOSO YA PASE EL FOR");

        // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        //BD.modificarNotificacionFecha(formato.format(c.getTime()),0);
        c.setTime(c.getTime());
        Establecer_alarma.nuevaAlarmaFutura(c);
        System.out.println("SISTEMA OPERATIVO INFORMANDO DE UN BOOT EXITOSO"+c.getTime());

        }
    }