package com.example.yanir.tooca;

import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnTouchListener {

    private Button UsuarioBtn;
    private Button botoncalendario;
    public Layout mControls;
    private static Manejador_BD BD;
    private static View ll_vestido;
    private static  ImageView iv_cabello;
    private static  ImageView iv_camisa;
    private static  ImageView iv_pantalon;
    private static  ImageView iv_zapato;
    private static LinearLayout ll_muneca;
    private static int cabello_id;
    private static int camisa_id;
    private static int pantalon_id;
    private static int zapatos_id;
    private static int vestido_id;
    public static int ic_logo_toca = R.mipmap.ic_launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        BD = new Manejador_BD(this);
//        BD.Push_BD("insert into usuarios values (1,'Yanir','Castillo','11-04-2015','Poz','Poz'); " );
      // BD.Push_BD("insert into centros_asistenciales values (1,'Chilemex','8.304223','-62.724277','cancer');");

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstFun", true);
        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
        //    ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        //    layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ;
        }
        /*Aqui revisamos si es la primera vez que entra el usuario
        * Si es verdad se ejecutara la actividad de registro y
        * Si es falso entrara normalmente al menu
        */

       // Se verifica si es la primera vez del usuario

        if (isFirstRun) {
            //Iniciamos el registro
            Toast.makeText(MainActivity.this, "Bienvenida", Toast.LENGTH_LONG).show();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstFun", false).commit();
            Intent intent = new Intent(MainActivity.this, Datos_Usuario_Activity.class);
            System.out.println("Voy a la nueva vista");

            //Iniciamos la nueva actividad

            startActivityForResult(intent,3);
        } else {
            setContentView(R.layout.menu);
            //Toast.makeText(MainActivity.this, "No First run", Toast.LENGTH_LONG).show();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            setSupportActionBar(toolbar);

            toolbar.setLogo(R.drawable.ic_logo_cancer_blanco_pegado2);
            toolbar.setTitle(null);
            ArrayList<Integer> accesorios_BD = new ArrayList<Integer>();
            accesorios_BD = BD.extraerMuneca();
            cabello_id = accesorios_BD.get(0);
            camisa_id = accesorios_BD.get(1);
            pantalon_id = accesorios_BD.get(2);
            zapatos_id = accesorios_BD.get(3);
            iv_cabello = (ImageView) findViewById(R.id.iv_cabello);
            iv_camisa  = (ImageView) findViewById(R.id.iv_camisa);
            iv_pantalon = (ImageView) findViewById(R.id.iv_pantalon);
            iv_zapato = (ImageView) findViewById(R.id.iv_zapatos);
            ll_vestido = (View) findViewById(R.id.ll_vestido);
            /* Inicializo imagenes */
            iv_cabello.setImageResource(cabello_id);
            iv_camisa.setImageResource(camisa_id);
            iv_pantalon.setImageResource(pantalon_id);
            iv_zapato.setImageResource(zapatos_id);
            if(accesorios_BD.get(4)==0){
                ll_vestido.setBackground(null);
            }else{
                ll_vestido.setBackgroundResource(accesorios_BD.get(4));
            }
            ll_muneca = (LinearLayout) findViewById(R.id.muneca);
            ll_muneca.setOnTouchListener(this);
            if(accesorios_BD.get(5)==0){
                ll_muneca.setBackgroundResource(R.drawable.ic_muequitablanquitap);
            }else{
                ll_muneca.setBackgroundResource(accesorios_BD.get(5));
            }




            Intent iin= getIntent();
            Bundle b = iin.getExtras();

            if(b!=null)
            {
                Integer bandera =(Integer) b.get("bandera");
                if(bandera == 1){
                    final DialogFragment dialogoSugerenciaMapa = new Sugerencia_Mapa();
                    dialogoSugerenciaMapa.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
                    dialogoSugerenciaMapa.show(getSupportFragmentManager(), "Sugerencia_Mapa");
                }

            }



            /*TextView nombreUsuario = (TextView) findViewById(R.id.nombre);
            nombreUsuario.setText(BD.getNombreUsuario());*/



        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void sabiasQue (View view){
        // Se llama al dialogo del mensaje ¿Sabias que?
        final DialogFragment dialogoSabiasQue = new Sabias_Que();
        dialogoSabiasQue.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
        dialogoSabiasQue.show(getSupportFragmentManager(), "Sabias_Que");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*Accion que se ejecuta al presionar el boton calendario*/
    public void abrirCalendario(View view) {
        /*Intent int1 = new Intent(this, Calendario.class);
        startActivity(int1);*/
        Intent intent = new Intent(this, Notas_apuntes.class);
        startActivity(intent);
    }

    public static void renderizar(){

        ArrayList<Integer> accesorios_BD = new ArrayList<Integer>();
        accesorios_BD = BD.extraerMuneca();
        cabello_id = accesorios_BD.get(0);
        camisa_id = accesorios_BD.get(1);
        pantalon_id = accesorios_BD.get(2);
        zapatos_id = accesorios_BD.get(3);
        iv_cabello.setImageResource(cabello_id);
        iv_camisa.setImageResource(camisa_id);
        iv_pantalon.setImageResource(pantalon_id);
        iv_zapato.setImageResource(zapatos_id);
        if(accesorios_BD.get(4)==0){
            ll_vestido.setBackground(null);
        }else{
            ll_vestido.setBackgroundResource(accesorios_BD.get(4));
        }

        if(accesorios_BD.get(5)==0){
            ll_muneca.setBackgroundResource(R.drawable.ic_muequitablanquitap);
        }else{
            ll_muneca.setBackgroundResource(accesorios_BD.get(5));
        }

    }

    public void abrirAjustes(View view) {
        Intent intent = new Intent(this, Ajustes.class);
        startActivity(intent);

    }

    public void AbrirMapas(View view) {
        Intent intent = new Intent(MainActivity.this, Centros_Asistenciales.class);
        startActivity(intent);
    }

    public void abrirNotas(View view) {
        Intent intent = new Intent(this, Notas_principal.class);
        startActivity(intent);
    }

    public void abrirAutoexamen(View view) {
        Intent intent = new Intent(MainActivity.this, Autoexamen.class);
        startActivity(intent);
        this.finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==3){
            System.out.println("Cerre la alarma");
            finish();
            startActivity(getIntent());
            //setCustomResourceForDates();
        }
    }


//MODIFICAR LA MUNECA DESDE EN MENU
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        System.out.println("entre al onclick de la muneca");
        Intent intent = new Intent(this,muneca.class);
        startActivity(intent);
        return false;
    }
}
