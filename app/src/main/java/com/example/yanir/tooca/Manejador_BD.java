package com.example.yanir.tooca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                + "password " + " varchar (25) not null, "
                + "fecha " + " date not null, "
                + "Direccion1 " + " varchar (25) not null, "
                + "Direccion2 " + " varchar (25) not null );" ;

        public static final  String tabla_muneca = "create table muneca ( " +
                "_id   integer primary key,  " +
                " cabello integer not null, " +
                " camisa integer not null, " +
                "pantalon integer not null, " +
                "zapatos integer not null, " +
                "vestido   integer); "  ;

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
                             "test1 char(2) not null, " +
                             "test2_1 char(2) not null, " +
                             "test2_2 char(2) not null, " +
                             "test2_3 char(2) not null, " +
                             "test3_1 char(2) not null, " +
                             "test3_2 char(2) not null, " +
                             "test3_3 char(2) not null, " +
                             "test4   char(2) not null, " +
                             "test5   char(2) not null, " +
                             "observaciones text null );" ;

        public static final  String constantes = " create table constantes( " +
                "_id   integer primary key autoincrement, " +
                "sintomas text not null );" ;


        public static final  String notas = " create table notas( " +
                "id   integer primary key autoincrement, " +
                "fecha date not null, " +
                "apunte string , " +
                "animo string );" ;

        public static final  String notificacion = " create table notificacion( " +
                "idNotificacion   integer primary key autoincrement, " +
                "fecha date not null, " +
                "status integer );" ;

        public static final  String animo = " create table animo( " +
                "id   integer primary key autoincrement, " +
                "fecha date not null, " +
                "imageid integer , " +
                "animo string not null );" ;

    public static final  String munequita = " create table munequita( " +
            "id   integer primary key , " +
            " cabello integer not null, " +
            " camisa integer not null, " +
            "pantalon integer not null, " +
            "zapatos integer not null, " +
            "piel integer not null, " +
            "vestido integer );" ;


    private BdHelper helper ;
        private SQLiteDatabase BD ;

        // Constructor de la base de datos.
        public Manejador_BD(Context context) {
            helper = new BdHelper(context);
            BD = helper.getWritableDatabase();
        }

    /*Metodo para consultar una notificaion*/
    public Integer consultarStatusNotificacion(String fecha){

        String[] columna = {"status"};
        String[] argumentos = {fecha};
        Cursor cursor = BD.query("notificacion",columna,"fecha = ?", argumentos, null, null , null);
        System.out.println("ESTOY REVISANDO SI HAY NOTIFICACION");

        if(cursor.moveToFirst()){
            return cursor.getInt(0);
        }

        return 1;
    }

    /*Metodo para consultar una notificaion*/
    public Boolean consultarNotificacion(String fecha){

        String[] columna = {"fecha"};
        String[] argumentos = {fecha};
        Cursor cursor = BD.query("notificacion",columna,"fecha = ?", argumentos, null, null , null);
        System.out.println("ESTOY REVISANDO SI HAY NOTIFICACION");

        if(cursor.moveToFirst()){
            return true;
        }

        return false;
    }


    /*Metodo para agregar un notificaicion*/
    public void agregarNotificacion(String fecha, int status){

        String[] columna = {"fecha"};
        String[] argumentos = {fecha};
        Cursor cursor = BD.query("notificacion",columna,"fecha = ?", argumentos, null, null , null);
        System.out.println("ESTOY REVISANDO SI HAY NOTIFICACION");

        if(!cursor.moveToFirst()){
            System.out.println(" Agrege la siguiente notifiacion: "+fecha);
            String sentencia = " INSERT INTO notificacion (fecha,status) VALUES ('"+fecha+"','" + status + "' ); ";
            this.Push_BD(sentencia);
        }

    }


    /*Metodo para eliminar una notificacion*/
    public void  EliminarNotificacion(String fecha){

        String[] columna = {"fecha"};
        String[] argumentos = {fecha};
        Cursor cursor = BD.query("notificacion",columna,"fecha = ?", argumentos, null, null , null);
        System.out.println("DESEO BORRAR ESTA NOTIFICACCION: "+fecha);

        if(cursor.moveToFirst()){
            System.out.println("CONSEGUI LA NOTIFICACION Y LO ESTOY BORRANDOEN LA BD");

            String sentencia = " delete from notificacion where fecha='"+fecha+"'; ";
            this.Push_BD(sentencia);

        }
    }


    /*Metodo para agregar una nota*/
    public void modificarNotificacion(String fecha, int status){
        String[] columna = {"fecha"};
        String[] argumentos = {fecha};
        Cursor cursor = BD.query("notificacion",columna,"fecha = ?", argumentos, null, null , null);


        if(cursor.moveToFirst()){
            System.out.println("CONSEGUI LA FECHA EN LA BD Y MODIFICO STATUS");
            ContentValues values = new ContentValues();
            values.put("status",status);
            BD.update("notificacion",values,"fecha = '"+fecha+"'",null);

        }
    }

    /*Metodo para agregar una nota*/
    public void modificarNotificacionFecha(String fecha, int status){
        String[] columna = {"fecha,idNotificacion"};
        String[] argumentos = {"1"};
        Cursor cursor = BD.query("notificacion",columna,"idNotificacion = ?",argumentos, null, null , null);


        if(cursor.moveToFirst()){
            System.out.println("CONSEGUI LA FECHA EN LA BD Y MODIFICO STATUS");
            ContentValues values = new ContentValues();
            values.put("fecha",fecha);
            BD.update("notificacion",values,"idNotificacion = '"+1+"'",null);

        }else{
            System.out.println(" Agrege la siguiente notifiacion: "+fecha);
            String sentencia = " INSERT INTO notificacion (fecha,status) VALUES ('"+fecha+"','" + status + "' ); ";
            this.Push_BD(sentencia);

        }
    }


    /*Metodo para buscar y devolver fechas de notificacion*/
    public ArrayList<Date>  buscarFechasDeAlarmas(){
        SimpleDateFormat formato;
        formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ArrayList<Date> fechas = new ArrayList<Date>();
        String[] columna = {"fecha"};
        Cursor cursor = BD.query("notificacion",columna,null, null, null, null , null);

        if(cursor.moveToFirst()){
            do {
                try {
                    System.out.println("FECHA BD: "+cursor.getString(0));
                    fechas.add(formato.parse(cursor.getString(0)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } while(cursor.moveToNext());

        }
        return fechas;
    }











    /*Metodo para agregar un animo*/
    public void agregarAnimo(String fecha, String animo,int imageid){

        String[] columna = {"fecha","animo"};
        String[] argumentos = {fecha,animo};
        Cursor cursor = BD.query("animo",columna,"fecha = ?"+ " and" +" animo = ?", argumentos, null, null , null);
        System.out.println("ESTOY REVISANDO SI HAY ANIMO");

        if(!cursor.moveToFirst()){

            System.out.println(" LO AGREGE EN LA BD: "+animo);
            String sentencia = " INSERT INTO animo (fecha,animo,imageid) VALUES ('"+fecha+"','" + animo + "','"+imageid+"' ); ";
            this.Push_BD(sentencia);

        }

    }


    /*Metodo para buscar y devolver images animo*/
    public ArrayList<Integer>  buscarImgAnimosPorFecha(String fecha){

        ArrayList<Integer> animos = new ArrayList<Integer>();
        String[] columna = {"imageid"};
        String[] argumentos = {fecha};

        Cursor cursor = BD.query("animo",columna,"fecha = ?", argumentos, null, null , null);

        if(cursor.moveToFirst()){
            do {
                animos.add(cursor.getInt(0));
                System.out.println("BUSQUEEEEEEEEEE"+ cursor.getInt(0));
                System.out.println("ARRALYST: "+animos.toString());
            } while(cursor.moveToNext());
        }
        return animos;
    }
    /*Metodo para buscar y devolver animo*/
    public ArrayList<String>  buscarAnimosPorFecha(String fecha){


        ArrayList<String> animos = new ArrayList<String>();
        String[] columna = {"animo"};
        String[] argumentos = {fecha};

        Cursor cursor = BD.query("animo",columna,"fecha = ?", argumentos, null, null , null);


        if(cursor.moveToFirst()){
            do {
                animos.add(cursor.getString(0));
                System.out.println("BUSQUEEEEEEEEEE"+ cursor.getString(0));
                System.out.println("ARRALYST: "+animos.toString());
            } while(cursor.moveToNext());
        }
        return animos;
    }



    /*Metodo para buscar y devolver animo*/
    public ArrayList<Date>  buscarFechasDeNotas(){
        SimpleDateFormat formato;
        formato = new SimpleDateFormat("dd-MM-yyyy");
        ArrayList<Date> fechas = new ArrayList<Date>();
        String[] columna = {"fecha"};
        Cursor cursor = BD.query("notas",columna,null, null, null, null , null);

        if(cursor.moveToFirst()){
            do {
                try {
                    System.out.println("FECHA BD: "+cursor.getString(0));
                    fechas.add(formato.parse(cursor.getString(0)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } while(cursor.moveToNext());

        }
        return fechas;
    }

    /*Metodo para buscar un animo*/
    public Boolean  buscarAnimo(String fecha, String animo){

        String[] columna = {"fecha","animo"};
        String[] argumentos = {fecha,animo};
        Cursor cursor = BD.query("animo",columna,"fecha = ?"+ " and" +" animo = ?", argumentos, null, null , null);
        System.out.println("DESEO buscar ESE ANIMO: "+animo);

        if(cursor.moveToFirst()){
            System.out.println("Busque y Lo encontre en la BD: "+animo);

            return true;

        }
        return false;
    }
    /*Metodo para eliminar un animo*/
    public void  EliminarrAnimo(String fecha, String animo){

        String[] columna = {"fecha","animo"};
        String[] argumentos = {fecha,animo};
        Cursor cursor = BD.query("animo",columna,"fecha = ?"+ " and" +" animo = ?", argumentos, null, null , null);
        System.out.println("DESEO BORRAR ESE ANIMO: "+animo);

        if(cursor.moveToFirst()){
            System.out.println("CONSEGUI EL ANIMO Y LO ESTOY BORRANDOEN LA BD");

            String sentencia = " delete from animo where fecha='"+fecha+"' AND "+"animo = '"+animo+"'; ";
            this.Push_BD(sentencia);

        }
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



    public ArrayList<Integer> extraerMuneca(){
        ArrayList<Integer> accesorios = new ArrayList<Integer>();
        String[] columna = {"cabello","camisa","pantalon","zapatos","vestido","piel"};
        String[] argumentos = {"1"};

        Cursor cursor = BD.query("munequita",columna,"id = ?", argumentos, null, null , null);


        if(cursor.moveToFirst()){
            System.out.println("CONSEGUI UNA MUNECA EN LA BD");
            accesorios.add(cursor.getInt(0));
            accesorios.add(cursor.getInt(1));
            accesorios.add(cursor.getInt(2));
            accesorios.add(cursor.getInt(3));
            accesorios.add(cursor.getInt(4));
            accesorios.add(cursor.getInt(5));
            System.out.println("EXTRAYENDO cabello:"+cursor.getInt(0)+"camisa:"+cursor.getInt(1)+"pantalon:"+cursor.getInt(2)+"zapatos:"+cursor.getInt(3)+"vestido:"+cursor.getInt(4));

        }else{
            System.out.println("NO CONSEGUI UNA MUNECA EN LA BD");
            accesorios.add(R.drawable.ic_transparente);
            accesorios.add(R.drawable.ic_transparente);
            accesorios.add(R.drawable.ic_transparente);
            accesorios.add(R.drawable.ic_transparente);
            accesorios.add(0);
            accesorios.add(0);

        }
        return accesorios;
    }

    /*Metodo para muneca una nota
   *
   * public static final  String tabla_muneca = "create table muneca ( " +
                           "_id   integer primary key,  " +
                           " cabello integer not null, " +
                           " camisa integer not null, " +
                           "pantalon integer not null, " +
                           "zapatos integer not null, " +
                           "vestido   integer); "  ;
   * */
    public void agregarMuneca(int cabello, int camisa, int pantalon, int zapatos, int vestido,int piel){
        System.out.println("cabello:"+cabello+"camisa:"+camisa+"pantalon:"+pantalon+"zapatos:"+zapatos+"vestido:"+vestido);

        String[] columna = {"id"};
        String[] argumentos = {"1"};

        Cursor cursor = BD.query("munequita",columna,"id = ?", argumentos, null, null , null);


        if(cursor.moveToFirst()){
            System.out.println("CONSEGUI UNA MUNECA EN LA BD");
            ContentValues values = new ContentValues();
            values.put("cabello",cabello);
            values.put("camisa",camisa);
            values.put("pantalon",pantalon);
            values.put("zapatos",zapatos);
            values.put("vestido",vestido);
            values.put("piel",piel);
            BD.update("munequita",values,"id = '1'",null);

        }else{
            System.out.println("NO CONSEGUI LA MUNECA ");
            /*String sentencia = " INSERT INTO notas (fecha,apunte,animo) VALUES ('"+fecha+"','" + apunte.getText() + "' , '" +
                    animo + "' ); ";
                    */
            String sentencia = " INSERT INTO munequita (id,cabello,camisa,pantalon,zapatos,vestido,piel) VALUES ('1','"+cabello+"','" + camisa + "','"+pantalon+"','" + zapatos + "','"+vestido+"','"+piel+"'); ";
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



    public String getPasswordUsuario(){
        String[] columna = {"password"};
        String password = null;
        Cursor cursor = BD.query("usuarios",columna,null, null, null, null , null);
        StringBuffer buffer = new StringBuffer();

        if(cursor.moveToFirst()){
             password = cursor.getString(0);

        }

        return password;
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
