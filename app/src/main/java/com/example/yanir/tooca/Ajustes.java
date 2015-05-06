package com.example.yanir.tooca;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesus on 3/27/2015.
 */
public class Ajustes extends ActionBarActivity {
    HashMap<String,List<String>> opciones_ajustes;
    List<String> subOpciones_ajustes;
    ExpandableListView exp_list;
    Ajustes_adapter adapter;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contexto = this;
        //Se verifica la version de la API que dispone el usuario
        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        exp_list = (ExpandableListView) findViewById(R.id.expandableListView);
        opciones_ajustes = ajustes_opciones.getInfo();
        subOpciones_ajustes = new ArrayList<String>(opciones_ajustes.keySet());
        adapter = new Ajustes_adapter(this,opciones_ajustes,subOpciones_ajustes);
        exp_list.setAdapter(adapter);

        exp_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getBaseContext(),subOpciones_ajustes.get(groupPosition)+ " is expanded",Toast.LENGTH_LONG).show();

            }
        });

        exp_list.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getBaseContext(),subOpciones_ajustes.get(groupPosition)+ " is collapse",Toast.LENGTH_LONG).show();

            }
        });

        exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
               switch(opciones_ajustes.get(subOpciones_ajustes.get(groupPosition)).get(childPosition)){
                   case "Cambiar contrasena":
                       Toast.makeText(getBaseContext(),"Cambiar contrasena",Toast.LENGTH_LONG ).show();
                       break;
                   case "Ver historial":
                       Intent intento = new Intent(Ajustes.this, Historial.class);
                       startActivity(intento);
                       break;
                   case "Dia del examen":
                       Toast.makeText(getBaseContext(),"dia del examen",Toast.LENGTH_LONG ).show();
                       break;
                   case "Avatar":
                       Toast.makeText(getBaseContext(),"Avatar",Toast.LENGTH_LONG ).show();
                       Intent intent = new Intent(contexto,muneca.class);
                       startActivity(intent);

                       break;
               }
                return false;
            }
        });

    }
}
