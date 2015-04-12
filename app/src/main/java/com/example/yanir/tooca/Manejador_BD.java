package com.example.yanir.tooca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Yanir on 24-03-2015.
 */
public class Manejador_BD {


        // Codigo SQLite para la creacion de la tabla usuario;
        public static final  String Tabla_Usuario = " create table usuarios ( "
                + "_id " + "  integer primary key autoincrement,  "
                + "nombre " + " varchar (25) not null, "
                + "apellido " + " varchar (25) not null, "
                + "fecha " + " date not null, "
                + "Direccion1 " + " varchar (25) not null, "
                + "Direccion2 " + " varchar (25) not null );" ;

        public static final  String tabla_muneca = "create table muneca ( " +
                            "_id   integer primary key autoincrement,  " +
                            "usuario_id integer , " +
                            " cabello text not null, " +
                            "cara text not null, " +
                            "ojos text not null, " +
                            "color   text not null, " +
                            "FOREIGN KEY(usuario_id)  REFERENCES usuarios(_id) ); " ;

        public static final  String centros_asistenciales = "create table centros_asistenciales ( " +
                             "_id   integer primary key autoincrement,  " +
                             "usuario_id integer , " +
                             "centro text not null , " +
                             "latitud text not null, " +
                             "logitud text not null, " +
                             "especialidades text not null, "+
                             "FOREIGN KEY(usuario_id)  REFERENCES usuarios(_id) ); ";


        public static final  String sabias_que =  "create table sabias_que( " +
                             "_id integer primary key autoincrement,  " +
                             "usuario_id integer , " +
                             "mensaje text not null, " +
                             "FOREIGN KEY(usuario_id)  REFERENCES usuarios(_id) );" ;

        public static final  String sintomas = " create table sintomas( " +
                             "_id   integer primary key autoincrement, " +
                             "usuario_id integer , " +
                             "sintomas text not null, " +
                             "FOREIGN KEY(usuario_id)  REFERENCES usuarios(_id) );;" ;

        public static final  String examen = " create table examen( " +
                             "_id   integer primary key autoincrement, " +
                             "usuario_id integer , " +
                             "fecha date not null, " +
                             "examen1 text not null, " +
                             "examen2 text not null, " +
                             "examen3   text not null, " +
                             "examen4   text not null, " +
                             "examen5   text not null, " +
                             "nota text not null, " +
                             "riesgo boolean not null, " +
                             "resultados text not null, " +
                             "FOREIGN KEY(usuario_id)  REFERENCES usuarios(_id) );;" ;





    private BdHelper helper ;
        private SQLiteDatabase BD ;

        // Constructor de la base de datos.
        public Manejador_BD(Context context) {
            helper = new BdHelper(context);
            BD = helper.getWritableDatabase();
        }




    // Funcion para insertar valores a las tablas de la base de datos.
        public void Query (String codigo) {
            BD.execSQL(codigo);
        }

        //Funcion para realiza consultas en la base de datos
        public Cursor Cargar_Datos (String consulta){
            Cursor C ;
            return  C = BD.rawQuery(consulta,null);
        }

        public Cursor Cargar_CA (){
            Cursor C ;

            String [] columnas = new String[] {"_id","centro","latitud","logitud","especialidades" };
            return  C = BD.query("centros_asistenciales",columnas,null,null,null,null,null);

        }


}
