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
                            " cabello text not null, " +
                            "cara text not null, " +
                            "ojos text not null, " +
                            "color   text not null ); "  ;

        public static final  String centros_asistenciales = "create table centros_asistenciales ( " +
                             "_id   integer primary key autoincrement,  " +
                             "centro text not null , " +
                             "latitud text not null, " +
                             "logitud text not null, " +
                             "especialidades text not null );";


        public static final  String sabias_que =  "create table sabias_que( " +
                             "_id integer primary key autoincrement,  " +
                             "mensaje text not null );" ;

        public static final  String sintomas = " create table sintomas( " +
                             "_id   integer primary key autoincrement, " +
                             "sintomas text not null );" ;

        public static final  String examen = " create table examen( " +
                             "_id   integer primary key autoincrement, " +
                             "fecha date not null, " +
                             "test1 text not null, " +
                             "test2_1 text not null, " +
                             "test2_2 text not null, " +
                             "test2_3 text not null, " +
                             "test3_1 text not null, " +
                             "test3_2 text not null, " +
                             "test3_3 text not null, " +
                             "test4   text not null, " +
                             "test5   text not null, " +
                             "nota text not null, " +
                             "riesgo boolean not null, " +
                             "resultados text not null );" ;





    private BdHelper helper ;
        private SQLiteDatabase BD ;

        // Constructor de la base de datos.
        public Manejador_BD(Context context) {
            helper = new BdHelper(context);
            BD = helper.getWritableDatabase();
        }




        // Funcion para insertar valores a las tablas de la base de datos.
        public void Push_BD (String codigo) {
            BD.execSQL(codigo);
        }

        //Funcion para realiza consultas sobre la base de datos
        public Cursor Get_BD (String consulta){
            Cursor C ;
            return  C = BD.rawQuery(consulta,null);
        }

        public Cursor Cargar_CA (){
            Cursor C ;

            String [] columnas = new String[] {"_id","centro","latitud","logitud","especialidades" };
            return  C = BD.query("centros_asistenciales",columnas,null,null,null,null,null);

        }


}
