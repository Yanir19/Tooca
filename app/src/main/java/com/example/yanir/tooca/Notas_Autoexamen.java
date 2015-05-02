package com.example.yanir.tooca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
        import android.os.Bundle;
        import android.support.v4.app.DialogFragment;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;


/**
 * Created by Miguel on 30/04/2015.
 */

public class Notas_Autoexamen extends FragmentActivity {


    private EditText observaciones;
    private Button finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notas_autoexamen);

        // Se llama al dialogo del Diagnostico
        final DialogFragment dialogoDiagnostico = new Diagnostico();
        dialogoDiagnostico.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
        dialogoDiagnostico.show(getSupportFragmentManager(), "Diagnostico");

        String[] datos = new String[9];

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
           datos = extras.getStringArray("arreglo");

        String todo = "";
        todo = "\n1-" + datos[0] + " \n2.1-" + datos[1] + " \n2.2-" +datos[2] + " \n2.3-" +datos[3] + " \n3.1-" +datos[4] + " \n3.2-" +datos[5] + " \n3.3-" +datos[6] + " \n4-" +datos[7] + " \n5-" +datos[8];
        Toast.makeText(Notas_Autoexamen.this, todo+" ", Toast.LENGTH_LONG).show();

        observaciones = (EditText)findViewById(R.id.observaciones_autoexamen);
        finalizar = (Button)findViewById(R.id.buttonConfirmarObservaciones);
        observaciones.setText("");

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notas_Autoexamen.this.finish();



            }
        });




    }
}

/*
----------- PARA LLAMAR EL DIALOGO EN OTRA CLASE PEGAR EL SIGUIENTE CODIGO "-----------

        // * * Inicio seccion de codigo del dialogo del ¿Sabias que? * * //

        final DialogFragment dialogoConfirmarSalirTest = new Confirmacion();
        dialogoConfirmarSalirTest.show(getSupportFragmentManager(), "Confirmacion");

        // * * Final seccion de codigo del dialogo del ¿Sabias que? * * //

 */



