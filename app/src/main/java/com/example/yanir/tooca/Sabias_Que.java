package com.example.yanir.tooca;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Miguel on 07/04/2015.
 */

public class Sabias_Que extends DialogFragment {

    private TextView zonaTexto;
    private LinearLayout lay;

    public Sabias_Que() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        String texto;
        View view = inflater.inflate(R.layout.sabias_que, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);

        ArrayList<String> sabiasQues = new ArrayList<String>();
        Random rand = new Random();
        int x = rand.nextInt(25);

        switch (x){
            case 0:
                texto = "¿Sabias que Las mujeres tienen una probabilidad del 80% de recuperarse del cancer si este se descubre y se trata a tiempo? Toocate!";
            break;
            case 1:
                texto = "¿Sabías que dos minutos de tu día pueden representar el resto de tu vida? Toocate!";
            break;
            case 2:
                texto = "¿Sabías que cada año se registran en todo el mundo más de 1 millón de nuevos casos de cáncer de seno? Toocate!";
            break;
            case 3:
                texto = "¿Sabias que cada año se registran más de 90.000 nuevos casos de cáncer de seno en LatinoAmérica? Toocate!";
            break;
            case 4:
                texto = "¿Sabías que el cáncer de mama es la 2º causa predominante de muertes por cáncer en América Latina? Toocate!";
            break;
            case 5:
                texto = "¿Sabías que el 19 de octubre se celebra el dia mundial contra el cancer de mama? Toocate!";
            break;
            case 6:
                texto = "¿Sabías que el autoexamen de mama es la acción mas importante para prevenir el cáncer? Toocate!";
            break;
            case 7:
                texto = "¿Sabias que en la mayoría de los casos el cáncer de mama es diagnosticado en etapas avanzadas? Toocate!";
            break;
            case 8:
                texto = "¿Sabias que el síntoma más común del cáncer de seno es una masa o protuberancia? Toocate!";
            break;
            case 9:
                texto = "¿Sabias que la exposición al Estrógeno durante un tiempo prolongado aumenta el riesgo de cáncer de mama? Toocate!";
            break;
            case 10:
                texto = "¿Sabias que tomar complejos vitamínicos, en especial vitamina D, ayuda a tener pronósticos más favorables? Toocate!";
            break;
            case 11:
                texto = "¿Sabias que ejercitarse 4 horas o más a la semana cambia la forma en cómo el cuerpo maneja el estrógeno y ayuda a reducir el riesgo de cáncer de mama? Toocate!";
            break;
            case 12:
                texto = "¿Sabias que la lactancia por un período prolongado [al menos por un año] está asociada con una reducción del riesgo del cáncer de seno? Toocate!";
            break;
            case 13:
                texto = "¿Sabias que El mejor momento para hacerse un autoexamen de los senos es cuando no están sensibles ni hinchados? Toocate!";
            break;
            case 14:
                texto = "¿Sabias que debes realizar mamografías periódicas a partir de los 40 años de edad? Toocate!";
            break;
            case 15:
                texto = "¿Sabias que regular el consumo de alcohol previene el cancer de mama? Toocate!";
            break;
            case 16:
                texto = "¿Sabias que una investigación realizada por la Universidad de Lund, en Suecia, sugiere que beber café puede prevenir el cáncer de mama.? Toocate!";
                break;
            case 17:
                texto = "¿Sabias que debes limitar el consumo de bebidas alcohólicas, carnes rojas y carnes procesadas, así como de los dulces? Toocate!";
                break;
            case 18:
                texto = "¿Sabias que existen alimentos que son beneficiosos y debes incorporar a tu dieta porque reducen las probabilidades de contraer la enfermedad? Toocate!";
                break;
            case 19:
                texto = "¿Sabias que el aceite de oliva virgen extra es un grasa monosaturada con alto contenido de antioxidantes y fitonutrientes que limita la producción de radicales libres? Toocate!";
                break;
            case 20:
                texto = "¿Sabias que los vegetales crucíferos como el brócoli, la col, la coliflor y muchos otros vegetales tienen varios componentes que estimulan a las encimas a combatir las toxinas cancerígenas antes de que dañen las células? Toocate!";
                break;
            case 21:
                texto = "¿Sabias que los vegetales de hojas de color verde intenso son ricos en folato, que es un tipo de vitamina D que fortalece el ADN y disminuye el riesgo de ser víctima de cáncer? Toocate!";
                break;
            case 22:
                texto = "¿Sabias que los ácidos grasos omega 3 que contienen los pescados azules o grasos pueden reducir la inflamación, una de las causas de cáncer de seno? Toocate!";
                break;
            case 23:
                texto = "¿Sabias que el tomate posee un poderoso antioxidante, el licopeno, el cual frena el crecimiento de células cancerosas en el seno? Toocate!";
                break;


            default:
                texto = "¿Sabias que debes evitar el sobrepeso y la obesidad, especialmente después de la menopausia? Toocate!";

        }




        sabiasQues.add(texto);


        // Se Setea el texto a Mostrar en el ¿Sabias que?

        zonaTexto = (TextView) view.findViewById(R.id.contenidoInformacion);
        zonaTexto.setText(texto);

        // Se establece la funcionalidad de cerrar el dialogo al tocar la pantalla
        lay = (LinearLayout) view.findViewById(R.id.frameSabiasQue);
        lay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Sabias_Que.this.getDialog().dismiss();
                return false;
            }
        });
        getDialog().setCanceledOnTouchOutside(true);

        return view;
    }
}

/*
----------- PARA LLAMAR EL DIALOGO EN OTRA CLASE PEGAR EL SIGUIENTE CODIGO EN EL METODO "OnCreate"-----------

        // * * Inicio seccion de codigo del dialogo del ¿Sabias que? * * //

            final DialogFragment dialogoSabiasQue = new Sabias_Que();
            dialogoSabiasQue.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.FondoTransparente);
            dialogoSabiasQue.show(getSupportFragmentManager(), "Sabias_Que");

        // * * Final seccion de codigo del dialogo del ¿Sabias que? * * //

 */