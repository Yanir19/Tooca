package com.example.yanir.tooca;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by LjnozZ on 23/04/2015.
 */

public class ScreenCapture extends Activity {

    private View viewToBeCaptured;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muneca);

    }


    public void screemShot(View viewToBeCaptured){

        //System.out.println("ENTRO AL SCREEM SHOT");
        // En res/layout/main.xml, hemos puesto a la View raíz el identificador main
        //viewToBeCaptured = this.findViewById(R.id.marco);
        //viewToBeCaptured.setOnClickListener(new View.OnClickListener( ){

                Drawable colorPrevio=viewToBeCaptured.getBackground();
                viewToBeCaptured.setBackgroundResource(R.drawable.ic_fondo_jardin);
                viewToBeCaptured.setDrawingCacheEnabled(true);
                Bitmap b = viewToBeCaptured.getDrawingCache();

                // Carpeta dónde guardamos la captura
                // En este caso, la raíz de la SD Card
                File sd = Environment.getExternalStorageDirectory();

                // El archivo que contendrá la captura
                File f = new File(sd, "capturaTooca.png");

                try {
                    if (sd.canWrite()) {
                        f.createNewFile();
                        OutputStream os = new FileOutputStream(f);
                        b.compress(Bitmap.CompressFormat.PNG, 90, os);
                        os.close();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("PRIMER CATCH");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("SEGUNDO CATH");
                }
                viewToBeCaptured.setDrawingCacheEnabled(false);
                viewToBeCaptured.setBackground(colorPrevio);




    }
}