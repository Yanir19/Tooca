package com.example.yanir.tooca;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Miguel on 07/04/2015.
 */

public class Sabias_Que extends DialogFragment {

    private TextView zonaTexto;
    private LinearLayout lay;

    public Sabias_Que() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sabias_que, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);



        // Se Setea el texto a Mostrar en el ¿Sabias que?
        String texto = "¿Sabias que Las mujeres tienen una probabilidad del 80% de recuperarse del cancer si este se descubre y se trata a tiempo?";
        zonaTexto = (TextView) view.findViewById(R.id.contenidoInformacion);
        zonaTexto.setText(texto);

        // Se establece la funcionalidad de cerrar el dialogo al tocar la pantalla
        lay = (LinearLayout) view.findViewById(R.id.frameSabiasQue);
        lay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Sabias_Que.this.getDialog().dismiss();
                return false;
            }
        });
        getDialog().setCanceledOnTouchOutside(true);

        return view;
    }
}

/*
----------- PARA LLAMAR EL DIALOGO EN OTRA CLASE PEGAR EL SIGUIENTE CODIGO EN EL METODO "OnCreate"-----------

        // * * Inicio seccion de codigo del dialogo del ¿Sabias que? * * //

            final DialogFragment dialogoSabiasQue = new Sabias_Que();
            dialogoSabiasQue.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
            dialogoSabiasQue.show(getSupportFragmentManager(), "Sabias_Que");

        // * * Final seccion de codigo del dialogo del ¿Sabias que? * * //

 */