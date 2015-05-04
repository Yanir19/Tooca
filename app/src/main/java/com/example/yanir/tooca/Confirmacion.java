package com.example.yanir.tooca;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Miguel on 27/04/2015.
 */

public class Confirmacion extends DialogFragment {

    private Button confirmar, cancelar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.confirmacion_salir_test, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);


        confirmar = (Button)view.findViewById(R.id.buttonConfirmarSalirTest);
        cancelar = (Button)view.findViewById(R.id.buttonCancelarSalirTest);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Confirmacion.this.getDialog().dismiss();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Confirmacion.this.getDialog().dismiss();
            }
        });
        getDialog().setCanceledOnTouchOutside(false);

        return view;
    }
}

/*
----------- PARA LLAMAR EL DIALOGO EN OTRA CLASE PEGAR EL SIGUIENTE CODIGO "-----------

        // * * Inicio seccion de codigo del dialogo del Confirmacion * * //

        final DialogFragment dialogoConfirmarSalirTest = new Confirmacion();
        dialogoConfirmarSalirTest.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
        dialogoConfirmarSalirTest.show(getSupportFragmentManager(), "Confirmacion");

        // * * Final seccion de codigo del dialogo del Confirmacion * * //

 */