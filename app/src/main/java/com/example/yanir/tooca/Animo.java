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
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jesus on 4/6/2015.
 */
public class Animo extends ActionBarActivity implements AdapterView.OnItemClickListener {

    GridView mygrid;

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
        mygrid = (GridView) findViewById(R.id.gridview);
        mygrid.setAdapter(new animoAdapter((this)));
        mygrid.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("position: "+position+" Nombre: ");

        ViewHolder holder = (ViewHolder) view.getTag();
        SingleAnimo temp = (SingleAnimo) holder.myAnimo.getTag();
        System.out.println("NOMBRE ANIMO: "+temp.countryName);
    }
}


class SingleAnimo {
    int imageId;
    String countryName;

    SingleAnimo(int imageId, String countryName) {
        this.imageId = imageId;
        this.countryName = countryName;

    }
}

class ViewHolder {
    ImageView myAnimo;

    ViewHolder(View v) {
        myAnimo = (ImageView) v.findViewById(R.id.imageView2);
    }
}

class animoAdapter extends BaseAdapter {

    ArrayList<SingleAnimo> list;
    Context context;

    animoAdapter(Context context) {
        this.context = context;
        list = new ArrayList<SingleAnimo>();
        Resources res = context.getResources();
        String[] tempAnimoNames = res.getStringArray(R.array.edo_animo);
        int[] animoImages = {R.mipmap.cry_icon, R.mipmap.haha_icon, R.mipmap.pudency_icon};
        for (int i = 0; i < 3; i++) {
            SingleAnimo tempAnimo = new SingleAnimo(animoImages[i], tempAnimoNames[i]);
            list.add(tempAnimo);
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
        holder.myAnimo.setImageResource(temp.imageId);

        holder.myAnimo.setTag(temp);
        return row;

    }


}