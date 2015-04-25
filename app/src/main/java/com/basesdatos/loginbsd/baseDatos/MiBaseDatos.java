package com.basesdatos.loginbsd.baseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MiBaseDatos extends SQLiteOpenHelper{

    public static final String LOGTAG="BaseD";
    private static final int VERSION_BASEDATOS = 1;

    // Nombre de nuestro archivo de base de datos
    private static final String NOMBRE_BASEDATOS = "mibasedatos.db";

    // Sentencia SQL para la creación de una tabla
    private static final String TABLA_USUARIOS = "CREATE TABLE usuarios" +
            "(_id INT PRIMARY KEY, usuario TEXT, email TEXT)";

    // CONSTRUCTOR de la clase
    public MiBaseDatos(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLA_USUARIOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);
        onCreate(db);
    }
}
