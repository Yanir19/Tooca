package com.example.yanir.tooca;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ExpandableListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes_principal);
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
                Toast.makeText(getBaseContext(),opciones_ajustes.get(subOpciones_ajustes.get(groupPosition)).get(childPosition)+" from opcion" +subOpciones_ajustes.get(groupPosition)+" is selected",Toast.LENGTH_LONG ).show();


                return false;
            }
        });

    }
}