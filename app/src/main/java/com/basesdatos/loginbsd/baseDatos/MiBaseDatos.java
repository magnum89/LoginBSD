package com.basesdatos.loginbsd.baseDatos;

import android.content.ContentValues;
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

    //Insertar registros:

    public void insertarUSUARIO(int id, String usr, int tlf, String email) {//como parámetros los datos que queremos insertar
    // en la tabla (id, usuario email)
        SQLiteDatabase db = getWritableDatabase();//creamos una instancia de la clase "SQLiteDatabase"
        // y usamos su método "getWritableDatabase() para poder escribir en la base de datos"

        if(db != null){//por si acaso la base de datos no existe

            ContentValues valores = new ContentValues();//una instancia de la clase "ContentValues" que como su nombre indica
            // es un almacenador de un conjunto de datos
            //Usamos el metodo "put(key, value)"el nombre donde establecer el valor almacenado
            // y como segundo parámetro el valor que queremos almacenar
            valores.put("_id", id);
            valores.put("usuario", usr);
            valores.put("email", email);
            //insertamos una fila en la tabla usando el método "insert(table, nullColumnHack, values)"
            db.insert("usuarios", null, valores);
            db.close();//deberemos cerrar la base de datos con el método "close()".
        }
    }

    //Modificar registros
//Prácticamente es igual que el anterior método pero con la excepción de que aquí estamos usando el método
// "update(table, values, whereClause, whereArgs)"
// para actualizar/modificar registros de nuestra tabla
    public void modificarUSUARIO(int id, String usr, String email){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("_id", id);
        valores.put("usuario", usr);
        valores.put("email", email);
        db.update("usuarios", valores, "_id=" + id, null);
        //el nombre de la tabla, los valores a modificar/actualizar "values" (ContentValues), una condición WHERE "whereClause"
        // que nos sirve para indicarle que valor queremos que actualicé (en este caso cogemos como referencia la id de nuestro contacto)
        // y como ultimo parámetro "whereArgs" podemos pasarle los valores nuevos a insertar, y lo tanto lo ponemos a null.
        db.close();//siempre cerrar la bsd
    }

    //borrar registros
    //el método "delete(table, whereClause, whereArgs)"
    public void borrarCONTACTO(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("usuarios", "_id="+id, null);//el nombre de la tabla "table", el registro a borrar "whereClause" como referencia su id
        // y como ultimo parámetro "whereArgs" los valores a borrar.
        db.close();
    }
}
