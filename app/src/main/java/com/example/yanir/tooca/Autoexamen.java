package com.example.yanir.tooca;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Personal on 13/04/2015.
 */
public class Autoexamen extends FragmentActivity {
    private TextView tituloTest, evaluacionTest;
    private ImageView imagenTest;
    private RadioButton radioSI, radioNO;
    private Button buttonSiguiente, buttonAnterior;
    private Manejador_BD BD;
    private Variables VAR;
    private ImageButton info;
    private LinearLayout ll_swipe;
    private Toast_Personalizado mensajeExamen;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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





        // Se llama al dialogo del mensaje ¿Sabias que?
        final DialogFragment dialogoSabiasQue = new Sabias_Que();
        dialogoSabiasQue.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
        dialogoSabiasQue.show(getSupportFragmentManager(), "Sabias_Que");

        // SE ENCUENTRAN LOS ELEMENTOS EN EL LAYOUT "autoexamen.xml"
        tituloTest = (TextView) findViewById(R.id.tituloTest);
        evaluacionTest = (TextView) findViewById(R.id.evaluacionTest);
        imagenTest = (ImageView) findViewById(R.id.imagenTest);
        radioSI = (RadioButton) findViewById(R.id.radioSI);
        radioNO = (RadioButton) findViewById(R.id.radioNO);
        buttonSiguiente = (Button) findViewById(R.id.buttonSiguiente);
        buttonAnterior = (Button) findViewById(R.id.buttonAnterior);
        info = (ImageButton) findViewById(R.id.botonInformacionTest);
        ll_swipe = (LinearLayout) findViewById(R.id.swipe);

        ll_swipe.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                buttonAnterior.callOnClick();
            }
            public void onSwipeLeft() {
                buttonSiguiente.callOnClick();
            }
            public void onSwipeBottom() {
            }
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }


        });


        //----  SE SETEA EL CONTENIDO DE CADA TEST ----//

        // Test1
        final String resultadoTest1 = "¿Observas alguna anomalia? \n\n- Masas o bultos\n- Hundimientos\n -Cambios de textura\n- Coloración de la piel";

        // Test2.1
        final String resultadoTest2_1 = "¿Sientes dolor o presencia de alguna masa?";

        // Test2.2
        final String resultadoTest2_2 = "¿Observas salida de secreción o sangrado?";

        // Test2.3
        final String resultadoTest2_3 = "¿Sientes dolor o presencia de alguna masa?";

        // Test3.1
        final String resultadoTest3_1 = "¿Sientes dolor o presencia de alguna masa?";

        // Test3.2
        final String resultadoTest3_2 = "¿Observas salida de secreción o sangrado?";

        // Test3.3
        final String resultadoTest3_3 = "¿Sientes dolor o presencia de alguna masa?";

        // Test4
        final String resultadoTest4 = "¿Sientes dolor o presencia de alguna masa?";

        // Test5
        final String resultadoTest5 = "¿Sientes dolor o presencia de alguna masa?";

        // Se inicializan variables de interes
        BD = new Manejador_BD(this);
        VAR = (Variables)getApplication();
        imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test1));
        tituloTest.setText("TEST 1");
        evaluacionTest.setText(resultadoTest1);

        // Se setea la accion a ejecutar cuando se presione el boton Informacion
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogFragment dialogoInfoTest = new Informacion_Autoexamen();
                dialogoInfoTest.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
                dialogoInfoTest.show(getSupportFragmentManager(), "Informacion_Autoexamen");
            }
        });



        final String[] datos = new String[9];
        mensajeExamen = new Toast_Personalizado(this,"Para pasar de examen desliza tu dedo hacia la izquierda",Toast.LENGTH_SHORT);
        //mensajeExamen.show();


        // Se setea la accion a ejecutar cuando se presione el boton Siguiente Test
        buttonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sw=0;

                if (radioSI.isChecked() == true) {
                    sw=1;

                } else if (radioNO.isChecked() == true) {
                    sw=2;

                } else {
                    Toast_Personalizado toast = new Toast_Personalizado(Autoexamen.this, "DEBES SELECCIONAR UNA OPCION", Toast.LENGTH_SHORT);
                    toast.show();
                }

                if(sw>0) {

                    String str = (String) tituloTest.getText();
                    String seleccion="";

                    // Se verifica resultado seleccionado para el test actual
                    if(sw == 1){
                        seleccion = "SI";
                    }else
                    if(sw == 2){
                        seleccion = "NO";
                    }

                    //----  SE SETEA EL CONTENIDO DE CADA ELEMENTO DE LA VENTANA AUTOEXAMEN----//
                    switch (str) {
                        case "TEST 1":
                            tituloTest.setText("TEST 2.1");
                            // contenidoTest.setText(test2_1);
                            evaluacionTest.setText(resultadoTest2_1);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test2_1));
                            datos[0]=seleccion;
                            break;

                        case "TEST 2.1":
                            tituloTest.setText("TEST 2.2");
                            // contenidoTest.setText(test2_2);
                            evaluacionTest.setText(resultadoTest2_2);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test2_2));
                            datos[1]=seleccion;
                            break;

                        case "TEST 2.2":
                            tituloTest.setText("TEST 2.3");
                            //   contenidoTest.setText(test2_3);
                            evaluacionTest.setText(resultadoTest2_3);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test2_3));
                            datos[2]=seleccion;
                            break;

                        case "TEST 2.3":
                            tituloTest.setText("TEST 3.1");
                            //  contenidoTest.setText(test3_1);
                            evaluacionTest.setText(resultadoTest3_1);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test3_1));
                            datos[3]=seleccion;
                            break;

                        case "TEST 3.1":
                            tituloTest.setText("TEST 3.2");
                            //  contenidoTest.setText(test3_2);
                            evaluacionTest.setText(resultadoTest3_2);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test3_2));
                            datos[4]=seleccion;
                            break;

                        case "TEST 3.2":
                            tituloTest.setText("TEST 3.3");
                            // contenidoTest.setText(test3_3);
                            evaluacionTest.setText(resultadoTest3_2);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test3_3));
                            datos[5]=seleccion;
                            break;

                        case "TEST 3.3":
                            tituloTest.setText("TEST 4");
                            // contenidoTest.setText(test4);
                            evaluacionTest.setText(resultadoTest4);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test4));
                            datos[6]=seleccion;
                            break;

                        case "TEST 4":
                            tituloTest.setText("TEST 5");
                            //     contenidoTest.setText(test5);
                            evaluacionTest.setText(resultadoTest5);
                            imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test5));
                            buttonSiguiente.setText("FINALIZAR TEST");
                            datos[7]=seleccion;
                            break;

                        case "TEST 5":
                            datos[8]=seleccion;

                            // Para obtener la fecha actual
                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            String strDate = sdf.format(c.getTime());

                            // Se guardan los resultados del autoexamen en las variables globales de autoexamen
                            for(int i=0; i<9; i++){
                                VAR.setResultados_autoexamen(datos[i], i);
                            }

                            // Se extraen de la BD los resultados del exameneshechos en la fecha actual
                            String queryResultadosExtraidos = "SELECT test1, test2_1, test2_2, test2_3, test3_1, test3_2,test3_3, test4, test5 FROM examen WHERE fecha = \""+ strDate +"\" ";
                            Cursor consulta;
                            consulta = BD.Get_BD(queryResultadosExtraidos);

                            // Se verifica si ya existe un autoexamen en la fecha actual
                            if(consulta.moveToFirst()){
                                //  Se llama al dialogo de confirmacion de guardar autoexamen
                                final DialogFragment dialogoConfirmarGuardarTest = new Confirmacion_Guardar_Autoexamen();
                                dialogoConfirmarGuardarTest.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FondoTransparente);
                                dialogoConfirmarGuardarTest.show(getSupportFragmentManager(), "Confirmacion_Guardar_Autoexamen");

                            }else{
                                // Sentencia SQL para insertar los resultados del dia actual en la tabla examen
                                String queryResultados = "INSERT INTO examen  " +
                                        "(fecha, test1, test2_1, test2_2, test2_3, test3_1, test3_2,test3_3, test4, test5) " +
                                        "VALUES(\""+strDate+ "\"," +
                                        "\""+datos[0]+"\"," +
                                        "\""+datos[1]+"\"," +
                                        "\""+datos[2]+"\"," +
                                        "\""+datos[3]+"\"," +
                                        "\""+datos[4]+"\"," +
                                        "\""+datos[5]+"\"," +
                                        "\""+datos[6]+"\"," +
                                        "\""+datos[7]+"\"," +
                                        "\""+datos[8]+"\");";

                                // Se insertan los resultados en la BD
                                BD.Push_BD(queryResultados);

                                // Se inicia la actividad de Agregar notas al Autoexaamen Realizado
                                Intent intent = new Intent(Autoexamen.this, Notas_Autoexamen.class);
                                startActivity(intent);
                                Autoexamen.this.finish();
                            }
                            break;
                    }
                }
            }
        });

        buttonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String str = (String) tituloTest.getText();



                //----  SE SETEA EL CONTENIDO DE CADA ELEMENTO DE LA VENTANA AUTOEXAMEN----//
                switch (str) {
                    case "TEST 2.1":
                        tituloTest.setText("TEST 1");
                        // contenidoTest.setText(test2_1);
                        evaluacionTest.setText(resultadoTest1);
                        imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test1));
                        break;

                    case "TEST 2.2":
                        tituloTest.setText("TEST 2.1");
                        // contenidoTest.setText(test2_1);
                        evaluacionTest.setText(resultadoTest2_1);
                        imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test2_1));
                        break;

                    case "TEST 2.3":
                        tituloTest.setText("TEST 2.2");
                        // contenidoTest.setText(test2_2);
                        evaluacionTest.setText(resultadoTest2_2);
                        imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test2_2));
                        break;

                    case "TEST 3.1":
                        tituloTest.setText("TEST 2.3");
                        //   contenidoTest.setText(test2_3);
                        evaluacionTest.setText(resultadoTest2_3);
                        imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test2_3));
                        break;

                    case "TEST 3.2":
                        tituloTest.setText("TEST 3.1");
                        //  contenidoTest.setText(test3_1);
                        evaluacionTest.setText(resultadoTest3_1);
                        imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test3_1));
                        break;

                    case "TEST 3.3":
                        tituloTest.setText("TEST 3.2");
                        //  contenidoTest.setText(test3_2);
                        evaluacionTest.setText(resultadoTest3_2);
                        imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test3_2));
                        break;

                    case "TEST 4":
                        tituloTest.setText("TEST 3.3");
                        // contenidoTest.setText(test3_3);
                        evaluacionTest.setText(resultadoTest3_2);
                        imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test3_3));
                        break;

                    case "TEST 5":
                        tituloTest.setText("TEST 4");
                        // contenidoTest.setText(test4);
                        evaluacionTest.setText(resultadoTest4);
                        imagenTest.setImageDrawable(getResources().getDrawable(R.drawable.test4));
                        break;
                }
            }

        });


    }

    // ----------------- Metodo a ejecutar si se presiona la tecla "<- BACK" ----------------- //

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            //  Se llama al dialogo de confirmacion de abandonar test
            final DialogFragment dialogoConfirmarSalirTest = new Confirmacion();
            dialogoConfirmarSalirTest.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FondoTransparente);
            dialogoConfirmarSalirTest.show(getSupportFragmentManager(), "Confirmacion");

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}

