package com.example.yanir.tooca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Miguel on 03/05/2015.
 */

public class Confirmacion_password extends DialogFragment {

    private Button sobrescribir, conservar;
    private Variables VAR;
    private Manejador_BD BD;
    Button entrarHistorial;
    Button cancelarHistorial;

    EditText contrasenaIntroducida;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.confirmacion_password, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        VAR = (Variables)getActivity().getApplication();
        BD = new Manejador_BD(getActivity());

        entrarHistorial = (Button)view.findViewById(R.id.botonAccesoHistorial);
        cancelarHistorial = (Button)view.findViewById(R.id.botonCancelarAcceso);
       contrasenaIntroducida = (EditText) view.findViewById(R.id.passwordIntroducido);

        entrarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getActivity(), Historial.class);
                startActivity(intento);

            }
        });

        cancelarHistorial.setOnClickListener(new View.OnClickListener() {
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