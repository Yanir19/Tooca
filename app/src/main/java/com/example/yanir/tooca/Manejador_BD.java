package com.example.yanir.tooca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Yanir on 24-03-2015.
 */
public class Manejador_BD {

        public static final String Nombre_Tabla = "usuarios";

        // Codigo SQLite para la creacion de la tabla usuario;
        public static final  String Tabla_Usuario = " create table usuarios ( "
                + " id " + "  integer primary key autoincrement,  "
                + "nombre " + " text not null, "
                + "apellido " + " text not null, "
                + "fecha " + " date not null, "
                + "Direccion1 " + " text not null, "
                + "Direccion2 " + " text not null );" ;

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
        public void Cargar_DDatos (String consulta){
            System.out.println("Estoy entrando a cargar los datos");
            System.out.println("Voy a cargar: " + consulta);
            BD.execSQL(consulta);
        }



        /* Funciones de manejo de base de datos de Android si acaso se necesitan
        //Genera el contenedor de los valores a insertar en una tupla de una tabla
        private ContentValues generarContentValues(String nombre, String apellido, String fecha){
            ContentValues valores = new ContentValues();
            valores.put(Nombre, nombre);
            valores.put(Apellido, apellido);
            valores.put(Fecha, fecha);
            return valores;
        }

        // Funcion para insertar valores a las tablas de la base de datos.
        public void insertar (String codigo) {
            BD.execSQL(codigo);
        }


        // Funcion para eliminar tuplas a las tablas de la base de datos.
        public void elimnar (String nombre) {
            BD.delete(Nombre_Tabla, Nombre + " = ?", new String[]{nombre});
        }

        // Funcion para modificar tuplas a las tablas de la base de datos.
        public void modificar (String nombre, String apellido, String fecha) {
            BD.update(Nombre_Tabla, generarContentValues(nombre,apellido,fecha), Nombre + " = ?",new String[]{nombre}  );
        }

        */
}
