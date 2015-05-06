package com.example.yanir.tooca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by Miguel on 04/05/2015.
 */


public class Sugerencia_Mapa extends DialogFragment {

    private LinearLayout lay;
    private ImageButton icono;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sugerencia_mapa, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);

        // Se establece la funcionalidad de cerrar el dialogo al tocar la pantalla
        lay = (LinearLayout) view.findViewById(R.id.layoutSugerenciaMapa);
        icono = (ImageButton) view.findViewById(R.id.iconoGPS);

        icono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Centros_Asistenciales.class);
                startActivity(intent);
                Sugerencia_Mapa.this.getDialog().dismiss();
            }
        });
        lay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Sugerencia_Mapa.this.getDialog().dismiss();
                return false;
            }
        });
        getDialog().setCanceledOnTouchOutside(true);

        return view;
    }
}
