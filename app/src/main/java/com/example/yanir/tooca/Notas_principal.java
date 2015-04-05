package com.example.yanir.tooca;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jesus on 4/4/2015.
 */
public class Notas_principal extends ActionBarActivity {

    ListView list;
    String [] titulos;
    int[] images = {R.mipmap.make_note,R.mipmap.make_note,R.mipmap.make_note};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       Resources res =  getResources();
       titulos = res.getStringArray(R.array.notes_titles);
       list = (ListView) findViewById(R.id.listViewNotas);
       NotasAdapter adapter = new NotasAdapter(this,titulos,images);
       list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {


                Toast.makeText(getBaseContext(),titulos[arg2],Toast.LENGTH_LONG).show();
            }
        });
    }


}


class NotasAdapter extends ArrayAdapter<String>{
    Context c;
    int[] images;
    String[] titleArray;
    NotasAdapter(Context context, String[] titles, int images[]){
        super(context,R.layout.single_row_notes,R.id.textViewNotas,titles);
        this.c = context;
        this.images = images;
        this.titleArray = titles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_notes,parent,false);

        ImageView myImage = (ImageView)row.findViewById(R.id.imageView);
        TextView myTitle = (TextView) row.findViewById(R.id.textViewNotas);

        myImage.setImageResource(images[position]);
        myTitle.setText(titleArray[position]);

        return row;
    }
}