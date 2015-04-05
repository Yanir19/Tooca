package com.example.yanir.tooca;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;


public class Mapas extends ActionBarActivity {

    GoogleMap mapa ;
    MapView vista_de_mapa;

    @Override
    protected void onResume(){
        super.onResume();
        vista_de_mapa.onResume();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        vista_de_mapa.onDestroy();
    }

   @Override
   protected void onPause(){
       super.onPause();
       vista_de_mapa.onPause();
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);

        vista_de_mapa = (MapView) findViewById(R.id.mapa);
        vista_de_mapa.onCreate(savedInstanceState);
        mapa = vista_de_mapa.getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.setMyLocationEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mapas, menu);
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
