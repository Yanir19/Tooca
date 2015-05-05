package com.example.yanir.tooca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yanir on 24-03-2015.
 */
public class BdHelper extends SQLiteOpenHelper {

    private static final String Nombre_BD = "Tooca.sqlite";
    private static final int BD_VERSION_ESQUEMA = 1;

    public BdHelper(Context context) {
        super(context, Nombre_BD, null, BD_VERSION_ESQUEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Manejador_BD.Tabla_Usuario);
        db.execSQL(Manejador_BD.tabla_muneca);
        db.execSQL(Manejador_BD.sabias_que);
        db.execSQL(Manejador_BD.centros_asistenciales);
        db.execSQL(Manejador_BD.sintomas);
        db.execSQL(Manejador_BD.notas);
        db.execSQL(Manejador_BD.animo);
        db.execSQL(Manejador_BD.examen);
        db.execSQL(Manejador_BD.munequita);
        db.execSQL(Manejador_BD.notificacion);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS notas");

        //Se crea la nueva versión de la tabla
        db.execSQL(Manejador_BD.notas);
    }
}
