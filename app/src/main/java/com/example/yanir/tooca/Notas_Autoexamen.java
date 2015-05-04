package com.example.yanir.tooca;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Miguel on 30/04/2015.
 */

public class Notas_Autoexamen extends FragmentActivity {

    private EditText observaciones;
    private Button finalizar;
    private Manejador_BD BD;
    private String mensaje;
    private Variables VAR;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notas_autoexamen);

      // Se llama al dialogo del Diagnostico
        final DialogFragment dialogoDiagnostico = new Diagnostico();
        dialogoDiagnostico.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
        dialogoDiagnostico.show(getSupportFragmentManager(), "Diagnostico");

      // Para obtener la fecha actual
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());

      // Se encuentran los objetos a utilizar del frameLayout notas_autoexamen.xml
        observaciones = (EditText)findViewById(R.id.observaciones_autoexamen);
        finalizar = (Button)findViewById(R.id.buttonConfirmarObservaciones);
        BD = new Manejador_BD(this);
        VAR = (Variables)getApplication();
        flag = 0;

      // Se extraen de la BD los resultados del examen si estos no reflejan presencia de ningun sintoma
        String queryResultados = "SELECT test1, test2_1, test2_2, test2_3, test3_1, test3_2,test3_3, test4, test5 FROM examen WHERE fecha = \""+ strDate +"\" AND \t(test1=\"SI\" OR test2_1=\"SI\" OR test2_2=\"SI\" OR test2_3=\"SI\" OR test3_1=\"SI\" OR test3_2=\"SI\" OR test3_3=\"SI\" OR test4=\"SI\" OR test5=\"SI\")";
        Cursor consulta;
        consulta = BD.Get_BD(queryResultados);

      // Se establece el mensaje a mostrar en el diagnostico de acuerdo a los resultados
        if (consulta.moveToFirst()){
            flag = 1;
            mensaje = "  AUTOEXAMEN DEL SENO \n" +
                      "****************************\n" +
                      "       Fecha - ["+strDate+"]\n\nPresentas Síntomas que podrían ser riesgosos \n\nTe recomendamos dirigirte al medico más cercano para que revise tu Autoevaluación.";
        }else
        if(!consulta.moveToFirst()){
            mensaje = "  AUTOEXAMEN DEL SENO \n" +
                      "****************************\n" +
                      "       Fecha - ["+strDate+"]\n\nNo tienes nada de que preocuparte, no presentas ningún Síntoma. \n\nMantente atenta al próximo Autoexamen.";
        }

      // Se setea el mensaje en la ventana
        observaciones.setText(mensaje);

      // Sentencia SQL para introducir observaciones al autoexamen realizado
        final String queryObservacionesTest = "UPDATE examen " +
                "SET observaciones = \""+observaciones.getText()+"\" " +
                "WHERE fecha = \""+strDate+"\"";

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // Se introducen en la BD las observaciones sobre el autoexamen realizado
                BD.Push_BD(queryObservacionesTest);

              // Se limpian las variables globales de autoexamen
                for(int i=0; i<9; i++){
                    VAR.setResultados_autoexamen("", i);
                }

              // Se llama a la actividad principal
                Intent intent = new Intent(Notas_Autoexamen.this, MainActivity.class);
                if(flag == 1)
                    intent.putExtra("bandera",1);
                startActivity(intent);

              // Se cierra la actividad actual
                Notas_Autoexamen.this.finish();
            }
        });
    }

    // ----------------- Metodo a ejecutar si se presiona la tecla "<- BACK" ----------------- //

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            //  Se llama al dialogo de Diagnostico
            final DialogFragment dialogoDiagnostico = new Diagnostico();
            dialogoDiagnostico.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
            dialogoDiagnostico.show(getSupportFragmentManager(), "Diagnostico");

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}



