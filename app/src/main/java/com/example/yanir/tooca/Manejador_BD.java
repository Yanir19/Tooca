package com.example.yanir.tooca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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



    public static final  String notas = " create table notas( " +
            "id   integer primary key autoincrement, " +
            "fecha date not null, " +
            "apunte string , " +
            "animo string );" ;

    public static final  String noticaciones = " create table notificaciones( " +
            "idNotificacion   integer primary key autoincrement, " +
            "fecha DATETIME not null, " +
            "status string );" ;

    public static final  String animo = " create table animo( " +
            "id   integer primary key autoincrement, " +
            "fecha date not null, " +
            "animo string );" ;


    private BdHelper helper ;
        private SQLiteDatabase BD ;

        // Constructor de la base de datos.
        public Manejador_BD(Context context) {
            helper = new BdHelper(context);
            BD = helper.getWritableDatabase();
        }

    /*Metodo para agregar una nota*/
    public void agregarNota(String fecha,EditText apunte, String animo){
        String selection = "fecha LIKE ?";
        String[] columna = {"fecha"};
        String[] argumentos = {fecha};
        Cursor cursor = BD.query("notas",columna,"fecha = ?", argumentos, null, null , null);


        if(cursor.moveToFirst()){
            System.out.println("CONSEGUI LA FECHA EN LA BD");
            ContentValues values = new ContentValues();
            values.put("apunte",apunte.getText().toString());
            BD.update("notas",values,"fecha = '"+fecha+"'",null);

        }else{
            System.out.println("NO CONSEGUI LA FECHA ");
            /*String sentencia = " INSERT INTO notas (fecha,apunte,animo) VALUES ('"+fecha+"','" + apunte.getText() + "' , '" +
                    animo + "' ); ";
                    */
            String sentencia = " INSERT INTO notas (fecha,apunte) VALUES ('"+fecha+"','" + apunte.getText() + "' ); ";
            this.Push_BD(sentencia);
        }
    }


        /*Metodo utilziado para extraer apuntes de la base de datos*/
        public String getApuntesbyFecha(String date){
            String[] columna = {"fecha,apunte"};
            String[] argumentos = {date};
            Cursor cursor = BD.query("notas",columna,"fecha = ?", argumentos, null, null , null);
            StringBuffer buffer = new StringBuffer();

            if(cursor.moveToFirst()){
                String apunte = cursor.getString(1);
                buffer.append(apunte+" ");
            }
            return buffer.toString();
        }


           /*Metodo utilziado para extraer notas de la base de datos*/
    public String getNotasbyFecha(String date){
        String[] columna = {"fecha,apunte,animo"};
        String[] argumentos = {date};
        String query = "Select fecha,apunte,animo from notas";
        Cursor cursor = BD.query("notas",columna,"fecha = ?", argumentos, null, null , null);
        StringBuffer buffer = new StringBuffer();


        if(cursor.moveToFirst()){
            String fecha = cursor.getString(0);
            String apunte = cursor.getString(1);
            String animo = cursor.getString(2);
            buffer.append(fecha+"\n"+"Apuntes: "+apunte+"\n"+ "animo:"+animo+"\n");
            System.out.println("\n\n"+buffer.toString());

        }

        return buffer.toString();
    }

    /*Metodo utilziado para extraer notas de la base de datos*/
    public String getNotas() {
        String[] columna = {"fecha,apunte,animo"};
        String query = "Select fecha,apunte,animo from notas";
        Cursor cursor = BD.query("notas", columna, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            String fecha = cursor.getString(0);
            String apunte = cursor.getString(1);
            String animo = cursor.getString(2);
            buffer.append(fecha + "\n" + "Apuntes: " + apunte + "\n" + "animo:" + animo + "\n");

        }

        return buffer.toString();
    }

    public void update_notas(String fecha , String nueva_nota){
        String selection = "fecha LIKE ?";

        String arg[] = {fecha};
        ContentValues values = new ContentValues();
        values.put("apunte",nueva_nota);
        BD.update("notas",values,selection,arg);
    }




    public String getNombreUsuario(){
        String[] columna = {"nombre"};
        String query = "Select nombre from usuarios";
        Cursor cursor = BD.query("usuarios",columna,null, null, null, null , null);
        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext()){
            String nombre = cursor.getString(0);
            buffer.append(nombre);
        }

        return buffer.toString();
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
