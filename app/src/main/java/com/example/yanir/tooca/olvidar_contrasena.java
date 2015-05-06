package com.example.yanir.tooca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by Miguel on 03/05/2015.
 */

public class olvidar_contrasena extends DialogFragment {

    private Button sobrescribir, conservar;
    private Variables VAR;
    private Manejador_BD BD;

    Button cancelar;

    Button recuperarPassword;
    EditText respuestaPreguntaSecreta;
    TextView password;
    TextView preguntaSecreta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.olvidar_contrasena, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        VAR = (Variables)getActivity().getApplication();
        BD = new Manejador_BD(getActivity());

        ArrayList<String> datosUsuario = new ArrayList<String>();
        datosUsuario = BD.extraerPreguntaYRespuesta();



        password = (TextView)view.findViewById(R.id.passwordActual);
        password.setVisibility(View.INVISIBLE);
        respuestaPreguntaSecreta = (EditText)view.findViewById(R.id.respuestaPreguntaSecreta);

        preguntaSecreta = (TextView)view.findViewById(R.id.preguntaSecreta);
        preguntaSecreta.setText(datosUsuario.get(0));


        cancelar = (Button)view.findViewById(R.id.botonCancelarPassword);

        recuperarPassword = (Button)view.findViewById(R.id.recuperarPassword);
        recuperarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> datosUsuario = new ArrayList<String>();
                datosUsuario = BD.extraerPreguntaYRespuesta();
                if(datosUsuario.get(1).contentEquals(respuestaPreguntaSecreta.getText().toString())){
                    password.setVisibility(View.VISIBLE);
                    password.setText("Password: "+datosUsuario.get(1));
                }else{
                    Toast.makeText(getActivity().getBaseContext(),"Respuesta incorrecta",Toast.LENGTH_SHORT);
                }





            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        });



        getDialog().setCanceledOnTouchOutside(false);
        return view;
    }
}

/*
----------- PARA LLAMAR EL DIALOGO EN OTRA CLASE PEGAR EL SIGUIENTE CODIGO "-----------

        // * * Inicio seccion de codigo del dialogo del Confirmacion * * //

        final DialogFragment dialogoConfirmarGuardarTest = new Confirmacion_Guardar_Autoexamen();
        dialogoConfirmarGuardarTest.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FondoTransparente);
        dialogoConfirmarGuardarTest.show(getSupportFragmentManager(), "Confirmacion_Guardar_Autoexamen");

        // * * Final seccion de codigo del dialogo del Confirmacion * * //

 */