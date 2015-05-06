package com.example.yanir.tooca;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jesus on 4/5/2015.
 */
public class Notas_apuntes extends FragmentActivity {


    public static  CaldroidFragment caldroidFragment;
    TextView apuntes ;
    TextView apuntes2;
    public static Manejador_BD BD;
    Button anadir_apunte;
    String fecha_seleccionada;
    private AlertDialog.Builder dialogBuilder;
    TextView animos;
    ArrayList<String> animosBD;
    ScrollView imagenes;
    Context contexto;
    LinearLayout sostenedordeanimos;
    SimpleDateFormat formatter;
    ImageView[] animosImagenes ;
    LinearLayout notasLinear;
    TextView notasTXTCAL;
    TextView notificadorExamenTXT;


    static SimpleDateFormat formato;

    public static void setCustomResourceForDates() {
        Integer diaDeExamen = 5;
        Integer MAX_POST_EXAMEN = 3;
        Calendar cal = Calendar.getInstance();
        Date notedate;

        ArrayList<Date> fechas = new ArrayList<Date>();
        ArrayList<Date> fechasAlarmas = new ArrayList<Date>();
        fechas = BD.buscarFechasDeNotas();
        fechasAlarmas = BD.buscarFechasDeAlarmas();
        System.out.println("numero de fechas:"+fechas.size());

        formato = new SimpleDateFormat("dd-MM-yyyy");


        // Min date is last 7 days
        //cal.add(Calendar.DATE, 5);
        /*
        cal.set(Calendar.DAY_OF_MONTH,diaDeExamen);
        Date blueDate = cal.getTime();


        cal = Calendar.getInstance();
        cal.set(Calendar.DATE, diaDeExamen+MAX_POST_EXAMEN);
        Date greenDate = cal.getTime();*/

        notedate = null;

       /* for(int i = 0;i<fechas.size();i++){
            cal.setTime(fechas.get(i));
            System.out.println("Fecha: "+formato.format(fechas.get(i)));
           notedate = cal.getTime();
           caldroidFragment.setBackgroundResourceForDate(R.color.md_blue_500,notedate);
        }*/




            for(int i = 0;i<fechas.size();i++){
                cal.setTime(fechas.get(i));
                notedate = cal.getTime();
                Notas_apuntes.caldroidFragment.setBackgroundResourceForDate(R.color.md_pink_50,notedate);
            }
        for(int i = 0;i<fechasAlarmas.size();i++){
            cal.setTime(fechasAlarmas.get(i));
            notedate = cal.getTime();
            Notas_apuntes.caldroidFragment.setBackgroundResourceForDate(R.color.md_red_300,notedate);

        }



        /*
            cal.setTime(formato.parse("10-05-2015"));
            notedate = cal.getTime();
            caldroidFragment.setBackgroundResourceForDate(R.color.md_blue_500,notedate);*/






        /*if (caldroidFragment != null) {
            caldroidFragment.setBackgroundResourceForDate(R.color.md_blue_200,
                    blueDate);
            caldroidFragment.setBackgroundResourceForDate(R.color.md_blue_200,
                    greenDate);

            //Cambiar color de letras para fecha especifica en el calendario
            //caldroidFragment.setTextColorForDate(R.color.caldroid_gray, blueDate);
            //caldroidFragment.setTextColorForDate(R.color.caldroid_darker_gray, greenDate);
        }*/
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notas_apuntes);
         BD = new Manejador_BD(this);
        /*
        anadir_apunte = (Button)findViewById(R.id.enviarApunte);
        anadir_apunte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                agregarNota(fecha_seleccionada);

            }
        });*/





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        apuntes = (TextView) findViewById(R.id.apuntes);
        animos = (TextView) findViewById(R.id.animo_txt);
        imagenes = (ScrollView) findViewById(R.id.scrollView2);
        contexto = this;
        notificadorExamenTXT = (TextView) findViewById(R.id.notificadorExamenTxt);
        notasTXTCAL = (TextView) findViewById(R.id.notasTXTCAL);
        sostenedordeanimos = (LinearLayout) findViewById(R.id.imagenesdeanimos);
        notasLinear = (LinearLayout)findViewById(R.id.notasLinear);
        notasLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, Notas_principal.class);
                intent.putExtra("fecha",fecha_seleccionada);
                startActivity(intent);
            }
        });



        formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendario = Calendar.getInstance();

        fecha_seleccionada = formatter.format(calendario.getTime());
        notasTXTCAL.setText("Notas: "+fecha_seleccionada);
        apuntes.setText(BD.getApuntesbyFecha(formatter.format(calendario.getTime())), TextView.BufferType.EDITABLE);
        ArrayList<Integer> imagenesId = new ArrayList<Integer>();
        animosBD = BD.buscarAnimosPorFecha(formatter.format(calendario.getTime()));
        animos.setText(animosBD.toString());
        animosImagenes = new ImageView[animosBD.size()];
        for(int i = 0; i<animosBD.size();i++){
            animosImagenes[i] = new ImageView(contexto);
        }
        imagenesId = BD.buscarImgAnimosPorFecha(formatter.format(calendario.getTime()));
        sostenedordeanimos.removeAllViews();
        for(int i = 0; i<animosBD.size();i++){
            animosImagenes[i].setImageResource(imagenesId.get(i));
            sostenedordeanimos.addView(animosImagenes[i]);
            // imagenes.addView(animosImagenes[i]);

        }





        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        caldroidFragment = new CaldroidFragment();




        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR,false);

            // Uncomment this to customize startDayOfWeek
            // args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
            // CaldroidFragment.TUESDAY); // Tuesday

            // Uncomment this line to use Caldroid in compact mode
            args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

           caldroidFragment.setArguments(args);
        }

        setCustomResourceForDates();

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.caldroide, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {

                ArrayList<Integer> imagenesId = new ArrayList<Integer>();

                ArrayList<Date> fechasAlarmas = new ArrayList<Date>();
                fechasAlarmas = BD.buscarFechasDeAlarmas();



                if(date.equals(fechasAlarmas.get(0))){
                    notificadorExamenTXT.setText("Autoexamen");
                    System.out.println("ALA MIERDA TODO");
                }else{
                    notificadorExamenTXT.setText(" ");
                }





                fecha_seleccionada = formatter.format(date);
                notasTXTCAL.setText("Notas: "+fecha_seleccionada);

                //caldroidFragment.setBackgroundResourceForDate(R.color.md_light_blue_300,date);
                //caldroidFragment.refreshView();
                fecha_seleccionada = formatter.format(date).toString();
                apuntes.setText(BD.getApuntesbyFecha(formatter.format(date)), TextView.BufferType.EDITABLE);
                animosBD = BD.buscarAnimosPorFecha(formatter.format(date));
                animos.setText(animosBD.toString());
                animosImagenes = new ImageView[animosBD.size()];
                for(int i = 0; i<animosBD.size();i++){
                    animosImagenes[i] = new ImageView(contexto);
                }
                imagenesId = BD.buscarImgAnimosPorFecha(formatter.format(date));
                sostenedordeanimos.removeAllViews();
                for(int i = 0; i<animosBD.size();i++){
                    animosImagenes[i].setImageResource(imagenesId.get(i));
                        sostenedordeanimos.addView(animosImagenes[i]);
                   // imagenes.addView(animosImagenes[i]);

                }
                   /* animosImagenes.setImageResource(imagenesId.get(0));
                imagenes.removeAllViews();
                    imagenes.addView(animosImagenes);*/




            }

            @Override
            public void onChangeMonth(int month, int year) {
                /*String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click " + formatter.format(date),
                        Toast.LENGTH_SHORT).show();
                alarmaDialogo();
            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {
                    /*Toast.makeText(getApplicationContext(),
                            "Caldroid view is created", Toast.LENGTH_SHORT)
                            .show();
                    */
                }
            }

        };


        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);




        //Se verifica la version de la API que dispone el usuario
        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


    }

    public void agregarNota(String date){

        //BD.agregarNota(date,apuntes,"triste");
        //BD.update_notas("25-04-2015",apuntes.getText().toString());

        Toast.makeText(Notas_apuntes.this,BD.getNotas(), Toast.LENGTH_LONG).show();

    }
    public void establecer_alarma(View view){

        Intent intent = new Intent(this, Establecer_alarma.class);
        startActivityForResult(intent,2);

    }

    public static void refrescarCaldroid(){
        ArrayList<Date> fechasAlarmas = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();

        Notas_apuntes.caldroidFragment.refreshView();
        fechasAlarmas = Notas_apuntes.BD.buscarFechasDeAlarmas();
        Date notedate = null;
        Notas_apuntes.caldroidFragment.refreshView();
        for(int i = 0;i<fechasAlarmas.size();i++){
            cal.setTime(fechasAlarmas.get(i));
            notedate = cal.getTime();
            Notas_apuntes.caldroidFragment.setBackgroundResourceForDate(R.color.md_red_300,notedate);
        }
    }



    private void alarmaDialogo(){
        //Variables
        dialogBuilder = new AlertDialog.Builder(this);

        //Proceso
        dialogBuilder.setTitle("Alarma");
        dialogBuilder.setMessage("Deberas tocarte los dias 5 de cada mes");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogBuilder.setNegativeButton("Cambiar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialogoAlarma = dialogBuilder.create();
        dialogoAlarma.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            System.out.println("Cerre la alarma");
            finish();
            startActivity(getIntent());
            //setCustomResourceForDates();
        }
    }



}