package com.example.yanir.tooca;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Personal on 13/04/2015.
 */
public class Sintomas extends FragmentActivity {
    protected LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sintomas);
        //Se verifica la version de la API que dispone el usuario
        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


        // * * Inicio seccion de codigo del dialogo del ¿Sabias que? * * //

        final DialogFragment dialogoSabiasQue = new Sabias_Que();
        dialogoSabiasQue.show(getSupportFragmentManager(), "Sabias_Que");

        // * * Final seccion de codigo del dialogo del ¿Sabias que? * * //

    }

}
