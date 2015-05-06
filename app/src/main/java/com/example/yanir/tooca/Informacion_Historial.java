package com.example.yanir.tooca;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Miguel on 04/05/2015.
 */

public class Informacion_Historial extends DialogFragment {


    private LinearLayout lay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.informacion_historial, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);

        // Se establece la funcionalidad de cerrar el dialogo al tocar la pantalla
        lay = (LinearLayout) view.findViewById(R.id.layoutInfoHistorial);
        lay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Informacion_Historial.this.getDialog().dismiss();
                return false;
            }
        });
        getDialog().setCanceledOnTouchOutside(true);


        return view;
    }
}


/*         Para llamar al dialogo Informacion_Historial

                final DialogFragment dialogoInfoHistorial = new Informacion_Historial();
                dialogoInfoHistorial.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
                dialogoInfoHistorial.show(getSupportFragmentManager(), "Informacion_Historial");
 */
