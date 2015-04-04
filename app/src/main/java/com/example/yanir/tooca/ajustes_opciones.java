package com.example.yanir.tooca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesus on 3/27/2015.
 */
public class ajustes_opciones {

    public static HashMap<String,List<String>> getInfo(){

        HashMap<String,List<String>> opciones_detalle = new HashMap<String,List<String>>();
        List<String> personalizar_opcion = new ArrayList<String>();
        personalizar_opcion.add("Avatar");
        personalizar_opcion.add("Tema");

        List<String> informacion_personal = new ArrayList<String>();
        informacion_personal.add("Modificar");
        informacion_personal.add("Contrasena");
        informacion_personal.add("Historial");

        opciones_detalle.put("Personalizar",personalizar_opcion);
        opciones_detalle.put("Informacion personal",informacion_personal);

        return opciones_detalle;
    }


}
