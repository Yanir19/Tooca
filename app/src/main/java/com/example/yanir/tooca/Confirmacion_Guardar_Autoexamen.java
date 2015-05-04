package com.example.yanir.tooca;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.DialogFragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;


/**
 * Created by Miguel on 03/05/2015.
 */

public class Confirmacion_Guardar_Autoexamen extends DialogFragment {

    private Button sobrescribir, conservar;
    private Variables VAR;
    private Manejador_BD BD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.confirmacion_guardar_autoexamen, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        VAR = (Variables)getActivity().getApplication();
        BD = new Manejador_BD(getActivity());

      // Para obtener la fecha actual
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());

      // Sentencia SQL para actualizar los resultados del dia actual en la tabla examen
        final String queryActualizarResultados = "UPDATE examen " +
                        "SET " +
                        "test1   = \""+VAR.getResultados_autoexamen(0)+"\"," +
                        "test2_1 = \""+VAR.getResultados_autoexamen(1)+"\"," +
                        "test2_2 = \""+VAR.getResultados_autoexamen(2)+"\"," +
                        "test2_3 = \""+VAR.getResultados_autoexamen(3)+"\"," +
                        "test3_1 = \""+VAR.getResultados_autoexamen(4)+"\"," +
                        "test3_2 = \""+VAR.getResultados_autoexamen(5)+"\"," +
                        "test3_3 = \""+VAR.getResultados_autoexamen(6)+"\"," +
                        "test4   = \""+VAR.getResultados_autoexamen(7)+"\"," +
                        "test5   = \""+VAR.getResultados_autoexamen(8)+"\"" +
                        " WHERE fecha = \""+strDate+"\"";


      // Se encuentran los objetos necesarios del linearLayout confirmacion_guardar_autoexamen.xml
        sobrescribir = (Button)view.findViewById(R.id.buttonSobrescribirTest);
        conservar = (Button)view.findViewById(R.id.buttonConservarTest);

      // Accion a ralizar cuando se presione el boton Sobrescribir
        sobrescribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Confirmacion_Guardar_Autoexamen.this.getDialog().dismiss();
                BD.Push_BD(queryActualizarResultados);
              // Se inicia la actividad de Agregar notas al Autoexaamen Realizado
                Intent intent = new Intent(getActivity(), Notas_Autoexamen.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

      // Accion a ralizar cuando se presione el boton Sobrescribir
        conservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Confirmacion_Guardar_Autoexamen.this.getDialog().dismiss();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
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