package com.example.yanir.tooca;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Personal on 13/04/2015.
 */
public class Autoexamen extends FragmentActivity {
    private TextView tituloTest, contenidoTest, evaluacionTest;
    private ImageView imagenTest;
    private RadioButton radioSI, radioNO;
    private Button buttonSiguiente;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.autoexamen);
        //Se verifica la version de la API que dispone el usuario
        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

/*
        // * * Inicio seccion de codigo del dialogo del ¿Sabias que? * * //

        final DialogFragment dialogoSabiasQue = new Sabias_Que();
        dialogoSabiasQue.show(getSupportFragmentManager(), "Sabias_Que");

        // * * Final seccion de codigo del dialogo del ¿Sabias que? * * //
*/

        //----  SE ENCUENTRAN LOS ELEMENTOS EN EL LAYOUT "autoexamen.xml" ----//

        tituloTest = (TextView) findViewById(R.id.tituloTest);
        contenidoTest = (TextView) findViewById(R.id.contenidoTest);
        evaluacionTest = (TextView) findViewById(R.id.evaluacionTest);
        imagenTest = (ImageView) findViewById(R.id.imagenTest);
        radioSI = (RadioButton) findViewById(R.id.radioSI);
        radioNO = (RadioButton) findViewById(R.id.radioNO);
        buttonSiguiente = (Button) findViewById(R.id.buttonSiguiente);




        imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test1));


        //----  SE EXTRAE DE LA BASE DE DATOS EL CONTENIDO DE CADA TEST ----//

        // Test1
        String test1 = "Párate frente al espejo, y lleva tus manos detrás de la cabeza con los codos elevados. Observa tus senos: \\n\\n   - Forma\\n   - Tamaño\\n   - Posición del pezón";
        String resultadoTest1 = "¿Observas alguna anomalia? \\n(masas o bultos, hundimientos, cambios de textura, coloración de la piel)";

        // Test2.1
        final String test2_1 = "Para examinar tu mama derecha, coloca la mano derecha detrás de la cabeza elevando el  codo. Con tu mano izquierda, con la yema de los dedos (índice, medio y anular) presiona suavemente con movimientos circulares, dando vuelta a la mama como las agujas del reloj. Palpa toda la superficie.";
        final String resultadoTest2_1 = "¿Sientes dolor o presencia de alguna masa?";

        // Test2.2
        final String test2_2 = "Examina tu pezón derecho con los dedos pulgar e índice, presiona suavemente.";
        final String resultadoTest2_2 = "¿Observas salida de secreción o sangrado?";

        // Test2.3
        final String test2_3 = "Para examinar la axila debes realizar movimientos circulares con la yema de los dedos, tratando de determinar la presencia de masas";
        final String resultadoTest2_3 = "¿Sientes dolor o presencia de alguna masa?";

        // Test3.1
        final String test3_1 = "Para examinar tu mama izquierda, coloca la mano izquierda detrás de la cabeza elevando el codo. Con tu mano derecha, con la yema de los dedos (índice, medio y anular) presiona suavemente con movimientos circulares, dando vuelta a la mama como las agujas del reloj. Palpa toda la superficie.";
        final String resultadoTest3_1 = "¿Sientes dolor o presencia de alguna masa?";

        // Test3.2
        final String test3_2 = "Examina tu pezón izquierdo con los dedos pulgar e índice, presiona suavemente.";
        final String resultadoTest3_2 = "¿Observas salida de secreción o sangrado?";

        // Test3.3
        final String test3_3 = "Para examinar la axila debes realizar movimientos circulares con la yema de los dedos, tratando de determinar la presencia de masas";
        final String resultadoTest3_3 = "¿Sientes dolor o presencia de alguna masa?";

        // Test4
        final String test4 = "Ahora acuéstate y coloca una almohada bajo tu hombro derecho. Para examinar tu mama derecha, coloca tu mano derecha detrás de la cabeza elevando el codo. Con la mano izquierda, con la yema de los dedos, presiona suavemente con movimientos circulares. Palpa toda la superficie en busca de una masa o zona hundida. ";
        final String resultadoTest4 = "¿Sientes dolor o presencia de alguna masa?";

        // Test5
        final String test5 = "Ahora acuéstate y coloca una almohada bajo tu hombro izquierdo. Para examinar tu mama izquierda, coloca tu mano izquierda detrás de la cabeza elevando el codo. Con la mano derecha, con la yema de los dedos, presiona suavemente con movimientos circulares. Palpa toda la superficie en busca de una masa o zona hundida. ";
        final String resultadoTest5 = "¿Sientes dolor o presencia de alguna masa?";



        final String[] datos = new String[9];


        buttonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sw=0;

                if (radioSI.isChecked() == true) {

                    // Se guarda seleccion en la BD
                    sw=1;

                } else if (radioNO.isChecked() == true) {

                    // // Se guarda seleccion en la BD
                    sw=2;

                } else {
                    Toast.makeText(Autoexamen.this, "DEBES SELECCIONAR UNA OPCION", Toast.LENGTH_SHORT).show();

                }


                if(sw>0) {


                    //----  SE SETEA EL CONTENIDO DE CADA ELEMENTO DE LA VENTANA AUTOEXAMEN----//
                    String str = (String) tituloTest.getText();
                    String seleccion="";

                    if(sw == 1){
                        seleccion = "SI";
                    }else
                    if(sw == 2){
                        seleccion = "NO";
                    }

                    switch (str) {
                        case "TEST 1":
                            tituloTest.setText("TEST 2.1");
                            contenidoTest.setText(test2_1);
                            evaluacionTest.setText(resultadoTest2_1);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test2_1));
                            datos[0]=seleccion;
                            break;

                        case "TEST 2.1":
                            tituloTest.setText("TEST 2.2");
                            contenidoTest.setText(test2_2);
                            evaluacionTest.setText(resultadoTest2_2);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test2_2));
                            datos[1]=seleccion;
                            break;

                        case "TEST 2.2":
                            tituloTest.setText("TEST 2.3");
                            contenidoTest.setText(test2_3);
                            evaluacionTest.setText(resultadoTest2_3);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test2_3));
                            datos[2]=seleccion;
                            break;

                        case "TEST 2.3":
                            tituloTest.setText("TEST 3.1");
                            contenidoTest.setText(test3_1);
                            evaluacionTest.setText(resultadoTest3_1);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test3_1));
                            datos[3]=seleccion;
                            break;

                        case "TEST 3.1":
                            tituloTest.setText("TEST 3.2");
                            contenidoTest.setText(test3_2);
                            evaluacionTest.setText(resultadoTest3_2);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test3_2));
                            datos[4]=seleccion;
                            break;

                        case "TEST 3.2":
                            tituloTest.setText("TEST 3.3");
                            contenidoTest.setText(test3_3);
                            evaluacionTest.setText(resultadoTest3_2);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test3_3));
                            datos[5]=seleccion;
                            break;

                        case "TEST 3.3":
                            tituloTest.setText("TEST 4");
                            contenidoTest.setText(test4);
                            evaluacionTest.setText(resultadoTest4);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test4));
                            datos[6]=seleccion;
                            break;

                        case "TEST 4":
                            tituloTest.setText("TEST 5");
                            contenidoTest.setText(test5);
                            evaluacionTest.setText(resultadoTest5);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test5));
                            buttonSiguiente.setText("FINALIZAR TEST");
                            datos[7]=seleccion;
                            break;
                        case "TEST 5":
                            datos[8]=seleccion;
                            String todo = "";
                            todo = "\n1-" + datos[0] + " \n2.1-" + datos[1] + " \n2.2-" +datos[2] + " \n2.3-" +datos[3] + " \n3.1-" +datos[4] + " \n3.2-" +datos[5] + " \n3.3-" +datos[6] + " \n4-" +datos[7] + " \n5-" +datos[8];
                            Toast.makeText(Autoexamen.this, todo+" ", Toast.LENGTH_LONG).show();



                            break;
                    }
                /*      radioNO.setChecked(false);
                radioSI.setChecked(false);*/

                }

            }
        });

    }

    // Metodo a ejecutar si se presiona la tecla "<- BACK"
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            //  Se llama al dialogo de confirmacion
            final DialogFragment dialogoConfirmarSalirTest = new Confirmacion();
            dialogoConfirmarSalirTest.show(getSupportFragmentManager(), "Confirmacion");

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}



