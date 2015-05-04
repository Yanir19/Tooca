package com.example.yanir.tooca;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Miguel on 01/05/2015.
 */
public class Diagnostico extends DialogFragment {

    private Button confirmar;
    private TextView diagnostico;
    private String mensaje;
    private Manejador_BD BD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.diagnostico, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);

      // Se encuentran los elementos a utilizar del dialogo
        confirmar = (Button)view.findViewById(R.id.buttonSalirDiagnostico);
        diagnostico = (TextView)view.findViewById(R.id.textViewMensajeDiagnostico);
        BD = new Manejador_BD(getActivity());

      // Para obtener la fecha
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());

      // Se extraen de la BD los resultados del examen si estos no reflejan presencia de ningun sintoma
        String queryResultados = "SELECT test1, test2_1, test2_2, test2_3, test3_1, test3_2,test3_3, test4, test5 FROM examen WHERE fecha = \""+ strDate +"\" AND \t(test1=\"SI\" OR test2_1=\"SI\" OR test2_2=\"SI\" OR test2_3=\"SI\" OR test3_1=\"SI\" OR test3_2=\"SI\" OR test3_3=\"SI\" OR test4=\"SI\" OR test5=\"SI\")";
        Cursor consulta;
        consulta = BD.Get_BD(queryResultados);
        mensaje = "";


      // Se establece el mensaje a mostrar en el diagnostico de acuerdo a los resultados
        if (consulta.moveToFirst()){
            mensaje = " Presentas Síntomas que podrían ser riesgosos \n\nTe recomendamos dirigirte al medico más cercano para que revise tu Autoevaluación.";
        }else
        if(!consulta.moveToFirst()){
            mensaje = " No tienes nada de que preocuparte, no presentas ningún Síntoma. \n\nMantente atenta al próximo Autoexamen.";
        }

      // Se coloca el mesaje en el dialogo
        diagnostico.setText(mensaje);


      // Se setea la accion a realizar cuando se presione el boton "CONTINUAR" del diaogo
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Diagnostico.this.getDialog().dismiss();
            }
        });

        getDialog().setCanceledOnTouchOutside(false);

        return view;
    }
}

/*
----------- PARA LLAMAR EL DIALOGO EN OTRA CLASE PEGAR EL SIGUIENTE CODIGO "-----------

        // * * Inicio seccion de codigo del dialogo del Diagnostico * * //

        final DialogFragment dialogoDiagnostico = new Diagnostico();
        dialogoDiagnostico.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
        dialogoDiagnostico.show(getSupportFragmentManager(), "Diagnostico");

        // * * Final seccion de codigo del dialogo del Diagnostico * * //

 */


