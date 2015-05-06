package com.example.yanir.tooca;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by Miguel on 03/05/2015.
 */

public class modificar_informacion_personal extends DialogFragment {

    private Button sobrescribir, conservar;
    private Variables VAR;
    private Manejador_BD BD;
    Button actualizar;
    Button cancelar;

    EditText nombreNuevo;
    EditText apellidoNuevo;
    EditText direccion1Nueva;
    EditText direccion2Nueva;
    EditText fechaNuevaNacimiento;
    DatePickerDialog.OnDateSetListener d;
    DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    Calendar calendar = Calendar.getInstance();
    Button btnFechaNuevoNacimiento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.modificar_informacion_personal, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        VAR = (Variables)getActivity().getApplication();
        ArrayList<String> datosUsuario = new ArrayList<String>();

        BD = new Manejador_BD(getActivity());
        datosUsuario = BD.extraerUsuario();


        nombreNuevo = (EditText) view.findViewById(R.id.nombreNuevo);
        nombreNuevo.setText(datosUsuario.get(0));
        apellidoNuevo = (EditText) view.findViewById(R.id.apellidoNuevo);
        apellidoNuevo.setText(datosUsuario.get(1));
        direccion1Nueva = (EditText)view.findViewById(R.id.direccion1Nueva);
        direccion1Nueva.setText(datosUsuario.get(2));
        direccion2Nueva = (EditText)view.findViewById(R.id.direccion2Nueva);
        direccion2Nueva.setText(datosUsuario.get(3));
        fechaNuevaNacimiento = (EditText)view.findViewById(R.id.fechaNuevaNacimiento);
        fechaNuevaNacimiento.setText(datosUsuario.get(4));
        fechaNuevaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });
        actualizar = (Button)view.findViewById(R.id.actualizarInformacionPersonal);
        cancelar = (Button)view.findViewById(R.id.botonCancelar);


        d= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                update();
            }
        };
        btnFechaNuevoNacimiento = (Button)view.findViewById(R.id.botonFechaNuevaNacimiento);
        btnFechaNuevoNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreNew = nombreNuevo.getText().toString();
                String apellidoNew = apellidoNuevo.getText().toString();
                String direccion1New = direccion1Nueva.getText().toString();
                String direccion2New = direccion1Nueva.getText().toString();
                BD.modificarUsuario(nombreNew,apellidoNew,direccion1New, direccion2New,formato.format(calendar.getTime()));
                Toast_Personalizado toast = new Toast_Personalizado(getActivity(), "Informacion actualizada", Toast.LENGTH_SHORT);
                toast.show();
                getActivity().finish();


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

    public void update (){
        fechaNuevaNacimiento.setText(formato.format(calendar.getTime()));
    }


    public void setDate(){
        new DatePickerDialog(getActivity(),d,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
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