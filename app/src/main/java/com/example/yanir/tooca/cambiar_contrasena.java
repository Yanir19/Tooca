package com.example.yanir.tooca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Miguel on 03/05/2015.
 */

public class cambiar_contrasena extends DialogFragment {

    private Button sobrescribir, conservar;
    private Variables VAR;
    private Manejador_BD BD;
    Button cambiarPassword;
    Button cancelar;

    EditText passwordViejo;
    EditText passwordNuevo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cambiar_contrasena, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        VAR = (Variables)getActivity().getApplication();
        BD = new Manejador_BD(getActivity());

        cambiarPassword = (Button)view.findViewById(R.id.actualizarPassword);
        cancelar = (Button)view.findViewById(R.id.botonCancelarPassword);
        passwordViejo = (EditText) view.findViewById(R.id.passwordViejo);
        passwordNuevo = (EditText)view.findViewById(R.id.passwordNuevo);

        cambiarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contrasenaVieja = BD.getPasswordUsuario();
                if (contrasenaVieja.contentEquals(passwordViejo.getText())){
                    BD.modificarUsuarioPassword(passwordNuevo.getText().toString());
                    Toast.makeText(getActivity(),"Password cambiado",Toast.LENGTH_SHORT);
                    getActivity().finish();

                }else{
                    Toast.makeText(getActivity(),"Password Incorrecto",Toast.LENGTH_SHORT);

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