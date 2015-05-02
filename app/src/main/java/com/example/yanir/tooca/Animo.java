package com.example.yanir.tooca;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Jesus on 4/6/2015.
 */
public class Animo extends ActionBarActivity implements AdapterView.OnItemClickListener {

    Manejador_BD BD;
    GridView mygrid;

    public  animoAdapter adaptador;
    Button guardarAnimo;
    Calendar c;
    SimpleDateFormat formato;
    String fecha,fecha_actual;
    Bundle bundle;
    TextView fechaTXV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.animo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//Se verifica la version de la API que dispone el usuario
        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        BD = new Manejador_BD(this);
        fechaTXV = (TextView) findViewById(R.id.fecha_actual);
        bundle = getIntent().getExtras();
        fecha_actual = bundle.getString("fecha_actual");

        fechaTXV.setText(fecha_actual);
        c = Calendar.getInstance();
        formato = new SimpleDateFormat("dd-MM-yyyy");
        fecha  = formato.format(c.getTime());

        guardarAnimo = (Button) findViewById(R.id.guardarAnimo);

        adaptador = new animoAdapter(this,fecha_actual);
        mygrid = (GridView) findViewById(R.id.gridview);
        mygrid.setAdapter(adaptador);

        //mygrid.setAdapter(new animoAdapter((this)));
        mygrid.setOnItemClickListener(this);

        for(int i=0;i<mygrid.getAdapter().getCount();i++){
            ViewHolder holder =  (ViewHolder) (mygrid.getAdapter().getView(i, null, null)).getTag();
            SingleAnimo temp = (SingleAnimo) holder.myAnimo.getTag();
            Toast.makeText(getBaseContext(),"Inicializando la vista: "+temp.countryName, Toast.LENGTH_LONG).show();

            //   holder.myAnimo.setBackgroundColor(R.color.md_blue_A100);

            if(BD.buscarAnimo(fecha_actual,temp.countryName)){
                Toast.makeText(getBaseContext(),"Verdadero Inicilizando aniimo: "+temp.countryName, Toast.LENGTH_LONG).show();
                System.out.println("Deberia estar pintando: "+temp.countryName);
                holder.myAnimo.setBackgroundResource(R.drawable.botoncircularpresionado);
            }else{

                Toast.makeText(getBaseContext()," falso  Inicilizando aniimo: "+temp.countryName, Toast.LENGTH_LONG).show();

            }
        }

        guardarAnimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Presione el boton guardar");
                for(int i=0;i<adaptador.getCount();i++){
                   ViewHolder holder =  (ViewHolder) (mygrid.getAdapter().getView(i, null, null)).getTag();
                   SingleAnimo temp = (SingleAnimo) holder.myAnimo.getTag();

                   SingleAnimo temp1 =(SingleAnimo) mygrid.getAdapter().getItem(i);
                   System.out.println("Animo: "+temp1.countryName +" Estado: "+temp.estado);
                    //SingleAnimo temp = adaptador.list.get(i);
                    Toast.makeText(getBaseContext(),"Encontre la vista: "+temp.countryName, Toast.LENGTH_LONG).show();

                    if(temp.estado ){
                        BD.agregarAnimo(fecha_actual,temp.countryName,temp.imageId);
                        Toast.makeText(getBaseContext(),"Agrege a la BD el animo: "+temp1.countryName, Toast.LENGTH_LONG).show();
                        System.out.println("Animo 0: " + temp.countryName + " Estado: " + temp.estado + " Agregado");

                        System.out.println("Animo 1: "+temp1.countryName +" Estado: "+temp1.estado +" Agregado");

                    }else{
                        BD.EliminarrAnimo(fecha_actual,temp.countryName);
                        System.out.println("Animo 0: "+temp.countryName +" Estado: "+temp.estado +" Eliminado");

                        System.out.println("Animo: "+temp1.countryName +" Estado: "+temp1.estado +" Eliminado");
                        Toast.makeText(getBaseContext(),"Elimine de la BD el animo: "+temp1.countryName, Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
        Toast.makeText(getBaseContext(),"NUMERO adaptador: "+adaptador.getCount(), Toast.LENGTH_LONG).show();


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("position: "+position+" Nombre: ");

        Toast.makeText(getBaseContext(),"NUMERO: "+parent.getCount(), Toast.LENGTH_LONG).show();

        ViewHolder holder = (ViewHolder) view.getTag();
       // ViewHolder holder = (ViewHolder) adaptador.getView(position,null,null).getTag();


        SingleAnimo temp = (SingleAnimo) holder.myAnimo.getTag();
        System.out.println("Le di click Animo item: "+temp.countryName +" Estado item: "+temp.estado +" ");

        if(temp.estado ){
           // holder.myAnimo.setBackgroundResource(0);
            holder.myAnimo.setBackgroundResource(0);
            temp.setEstado(false);
            System.out.println("Animo item: "+temp.countryName +" Estado item: "+temp.estado +" ");
            //adaptador.list.get(position).setEstado(false);
        }else{
            //holder.myAnimo.setBackgroundResource(R.drawable.botoncircularpresionado);
            holder.myAnimo.setBackgroundResource(R.drawable.botoncircularpresionado);
            temp.setEstado(true);
            System.out.println("Animo item: "+temp.countryName +" Estado item: "+temp.estado +" ");

            //adaptador.list.get(position).setEstado(true);
        }

        System.out.println("NOMBRE ANIMO: "+temp.countryName);
        Toast.makeText(getBaseContext(),temp.countryName, Toast.LENGTH_LONG).show();



    }

    public void inicializarAnimos(String fecha){
        for(int i=0;i<mygrid.getAdapter().getCount();i++){
            ViewHolder holder =  (ViewHolder) (mygrid.getAdapter().getView(i, null, null)).getTag();
            SingleAnimo temp = (SingleAnimo) holder.myAnimo.getTag();
            Toast.makeText(getBaseContext(),"Inicializando la vista: "+temp.countryName, Toast.LENGTH_LONG).show();

         //   holder.myAnimo.setBackgroundColor(R.color.md_blue_A100);

            if(BD.buscarAnimo(fecha,temp.countryName)){
                 Toast.makeText(getBaseContext(),"Verdadero Inicilizando aniimo: "+temp.countryName, Toast.LENGTH_LONG).show();
                System.out.println("Deberia estar pintando");
                holder.myAnimo.setBackgroundResource(R.drawable.botoncircularpresionado);
            }else{

                Toast.makeText(getBaseContext()," falso  Inicilizando aniimo: "+temp.countryName, Toast.LENGTH_LONG).show();

            }
        }

    }
}


class SingleAnimo {
    int imageId;
    String countryName;
    Boolean estado;

    SingleAnimo(int imageId, String countryName,Boolean estado) {
        this.imageId = imageId;
        this.countryName = countryName;
        this.estado = estado;

    }

    public void setEstado(Boolean estado){
        this.estado = estado;
    }

}

class ViewHolder {
    ImageView myAnimo;
    TextView nombreAnimo;
    Boolean estado;

    ViewHolder(View v) {
        myAnimo = (ImageView) v.findViewById(R.id.imageView2);
        nombreAnimo = (TextView) v.findViewById(R.id.nombreAnimo);
        estado = false;

    }
    public void setEstadoHolder(Boolean estado){
        this.estado = estado;

    }
}

class animoAdapter extends BaseAdapter {

    ArrayList<SingleAnimo> list;
    Context context;
    Manejador_BD BD;
    String fecha;


    animoAdapter(Context context,String fecha) {
        this.context = context;
        this.fecha = fecha;
        BD = new Manejador_BD(context);
        list = new ArrayList<SingleAnimo>();
        Resources res = context.getResources();
        String[] tempAnimoNames = res.getStringArray(R.array.edo_animo);
        int[] animoImages = {R.mipmap.cry_icon, R.mipmap.haha_icon, R.mipmap.pudency_icon,R.drawable.ic_burn_joss_stick};
        for (int i = 0; i < animoImages.length; i++) {
            if(BD.buscarAnimo(fecha,tempAnimoNames[i])){
                SingleAnimo tempAnimo = new SingleAnimo(animoImages[i], tempAnimoNames[i],true);
                list.add(tempAnimo);
            }else{
                SingleAnimo tempAnimo = new SingleAnimo(animoImages[i], tempAnimoNames[i],false);
                list.add(tempAnimo);

            }

        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.singleanimo, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();

        }
        SingleAnimo temp = list.get(position);
        System.out.println("JaaaaAAAA"+temp.imageId);
        System.out.println("JaaaaAAAA"+temp.countryName);
        if(BD.buscarAnimo(fecha,temp.countryName))
            holder.myAnimo.setBackgroundResource(R.color.md_red_400);
        holder.myAnimo.setImageResource(temp.imageId);
        holder.nombreAnimo.setText(temp.countryName);
        /*
        if(BD.buscarAnimo(fecha,temp.countryName)){
            holder.myAnimo.setBackgroundResource(R.drawable.botoncircularpresionado);
            temp.setEstado(true);
            holder.setEstadoHolder(true);
        }else{

            temp.setEstado(false);
            holder.myAnimo.setBackgroundResource(0);
            holder.setEstadoHolder(false);


        }*/

        holder.myAnimo.setTag(temp);
        return row;

    }


}