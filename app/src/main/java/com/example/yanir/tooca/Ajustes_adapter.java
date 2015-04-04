package com.example.yanir.tooca;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesus on 3/27/2015.
 */
public class Ajustes_adapter extends BaseExpandableListAdapter{
    private Context ctx;
    private HashMap<String,List<String>> opciones_ajustes;
    private List<String> subOpciones;

    public Ajustes_adapter(Context ctx,HashMap<String,List<String>> opciones_ajustes, List<String> subOpciones ){

        this.ctx = ctx;
        this.opciones_ajustes = opciones_ajustes;
        this.subOpciones = subOpciones;
    }

    @Override
    public int getGroupCount() {
        return subOpciones.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return opciones_ajustes.get(subOpciones.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return subOpciones.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return opciones_ajustes.get(subOpciones.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group_title = (String) getGroup(groupPosition);
        if(convertView == null)
        {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.ajustes_opciones,parent,false);
        }
        TextView parent_textview = (TextView) convertView.findViewById(R.id.parent_txt);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String child_opcion = (String) getChild(groupPosition, childPosition);
       if(convertView == null)
       {
           LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.ajustes_subopciones,parent, false);
       }
        TextView child_textview = (TextView) convertView.findViewById(R.id.child_txt);
        child_textview.setText(child_opcion);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
