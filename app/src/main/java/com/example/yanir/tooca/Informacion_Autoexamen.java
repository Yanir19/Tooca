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

public class Informacion_Autoexamen extends DialogFragment {

    private Button cerrar;
    private TextView contenido, titulo, testActual;
    private LinearLayout lay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.info_autoexamen, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);

        contenido = (TextView)view.findViewById(R.id.textViewMensajeInformacionTest);
        titulo = (TextView)view.findViewById(R.id.tituloInformacionTest);
        testActual = (TextView)getActivity().findViewById(R.id.tituloTest);

        //----  SE SETEA EL CONTENIDO DE CADA TEST ----//

        // Test1
        final String test1 = "Párate frente al espejo, y lleva tus manos detrás de la cabeza con los codos elevados. Observa tus senos: \n\n- Forma                      \n- Tamaño                   \n- Posición del pezón";

        // Test2.1
        final String test2_1 = "Para examinar tu mama derecha, coloca la mano derecha detrás de la cabeza elevando el  codo.\n\n Con tu mano izquierda, utilizando la yema de los dedos (índice, medio y anular) presiona suavemente con movimientos circulares, dando vuelta a la mama como las agujas del reloj.\n\n Palpa toda la superficie.";

        // Test2.2
        final String test2_2 = "Examina tu pezón derecho con los dedos pulgar e índice, presiona suavemente.";

        // Test2.3
        final String test2_3 = "Para examinar la axila debes realizar movimientos circulares con la yema de los dedos, tratando de determinar la presencia de masas";

        // Test3.1
        final String test3_1 = "Para examinar tu mama izquierda, coloca la mano izquierda detrás de la cabeza elevando el codo.\n\n Con tu mano derecha, utilizando la yema de los dedos (índice, medio y anular) presiona suavemente con movimientos circulares, dando vuelta a la mama como las agujas del reloj.\n\n Palpa toda la superficie.";

        // Test3.2
        final String test3_2 = "Examina tu pezón izquierdo con los dedos pulgar e índice, presiona suavemente.";

        // Test3.3
        final String test3_3 = "Para examinar la axila debes realizar movimientos circulares con la yema de los dedos, tratando de determinar la presencia de masas";

        // Test4
        final String test4 = "Ahora acuéstate y coloca una almohada bajo tu hombro derecho.\n\nPara examinar tu mama derecha, coloca tu mano derecha detrás de la cabeza elevando el codo.\n\n Con la mano izquierda, haciendo uso de la yema de los dedos, presiona suavemente con movimientos circulares.\n\n Palpa toda la superficie en busca de una masa o zona hundida.";

        // Test5
        final String test5 = "De igual forma que en la posicion anterior, pero esta vez coloca la almohada bajo tu hombro izquierdo.\n\n Para examinar tu mama izquierda, coloca tu mano izquierda detrás de la cabeza elevando el codo.\n\n Con la mano derecha, con la yema de los dedos, presiona suavemente con movimientos circulares.\n\n Palpa toda la superficie en busca de una masa o zona hundida. ";


        String str = (String) testActual.getText();
        String mensaje ="";
        String title = "";

        //----  SE SETEA EL CONTENIDO DE CADA ELEMENTO DE LA VENTANA AUTOEXAMEN----//
        switch (str) {
            case "TEST 1":
                mensaje = test1;
                title = "TEST 1";
                break;

            case "TEST 2.1":
                mensaje = test2_1;
                title = "TEST 2.1";
                break;

            case "TEST 2.2":
                mensaje = test2_2;
                title = "TEST 2.2";
                break;

            case "TEST 2.3":
                mensaje = test2_3;
                title = "TEST 2.3";
                break;

            case "TEST 3.1":
                mensaje = test3_1;
                title = "TEST 3.1";
                break;

            case "TEST 3.2":
                mensaje = test3_2;
                title = "TEST 3.2";
                break;

            case "TEST 3.3":
                mensaje = test3_3;
                title = "TEST 3.3";
                break;

            case "TEST 4":
                mensaje = test4;
                title = "TEST 4";
                break;

            case "TEST 5":
                mensaje = test5;
                title = "TEST 5";
                break;
        }

        contenido.setText(mensaje);
        titulo.setText(title);

        // Se establece la funcionalidad de cerrar el dialogo al tocar la pantalla
        lay = (LinearLayout) view.findViewById(R.id.layoutMensajeDiagnostico);
        lay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Informacion_Autoexamen.this.getDialog().dismiss();
                return false;
            }
        });
        getDialog().setCanceledOnTouchOutside(true);


        return view;
    }
}

/*         Para llamar al dialogo Informacion_Autoexamen

                final DialogFragment dialogoInfoTest = new Informacion_Autoexamen();
                dialogoInfoTest.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
                dialogoInfoTest.show(getSupportFragmentManager(), "Informacion_Autoexamen");
 */