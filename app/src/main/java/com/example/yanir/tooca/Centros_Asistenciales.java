package com.example.yanir.tooca;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class Centros_Asistenciales extends ActionBarActivity {

    private TextView cabecera;
    private ListView lstOpciones;
    private Manejador_BD BD;
    private Cursor cursor;
    SimpleCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centros__asistenciales);
        BD = new Manejador_BD(this);

        cursor = BD.Cargar_CA();
        lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        cabecera = (TextView)findViewById(R.id.cabecera);


        //Cabecera del listview
        View header = getLayoutInflater().inflate(R.layout.list_header, null);
        lstOpciones.addHeaderView(header);
      /*  setListAdapter(new ArrayAdapter<String>(
                this,R.layout.estilo_listview,R.id.list_content, listItems));
*/


        String [] from = new String[]{"centro", "especialidades", "logitud", "latitud", "_id"};
        int [] to = new int[] {android.R.id.text1,android.R.id.text2};

        adapter = new SimpleCursorAdapter(this,android.R.layout.list_content,cursor,from,to,0);

        lstOpciones.setAdapter(adapter);

        //Action Listener, aqui se ejecutan las acciones a realizar cuando se selecciona un elemento del listview
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Se obtiene la informacion del cursor del item seleccionado.
                Cursor mycursor = (Cursor) lstOpciones.getItemAtPosition(position);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("latitud", mycursor.getString(2));
                b.putString("longitud", mycursor.getString(3));
                b.putString("centro", mycursor.getString(1));
                //Creamos y añadimos la información al intent
                Intent intent = new Intent(Centros_Asistenciales.this, Mapas.class);
                intent.putExtras(b);
                startActivity(intent);
            }

            });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_centros__asistenciales, menu);
        return true;
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



}
