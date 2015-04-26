package com.basesdatos.loginbsd.baseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    public void insertarUSUARIO(int id, String usr, String email) {//como parámetros los datos que queremos insertar
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

    //leer un registro
    /**Este método devuelve un objeto Usuarios con los datos del usuario (id, usurio, email).
     En este caso como queremos leer hacemos uso del método "getReadableDatabase()". Creamos una variable
     "valores_recuperar" con las columnas que queremos recuperar, en este caso vamos a recuperar todos los
      datos de un registro. Continuamos creando un "Cursor" que se encarga de devolver el resultado de un
      registro de la tabla y lo almacena en la memoria, le aplicamos el método:

      query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)

      Con este método conseguimos leer un registro de la tabla. Como primer parámetro "table" nos pide el
      nombre de la tabla , "columns" las columnas que queremos recuperar, con "selection" le indicamos el
      registro a recuperar (en este caso recuperamos con el id), o los registros a recuperar "selectionArgs",
      "groupBy" para agrupar los registros consultados , "having" es un filtro para incluir los registros en
       el cursor (este parámetro se usaría con groupBy), "orderBy" para ordenar las filas y "limit" para
        limitar el numero de filas consultadas.

      Con el método "moveToFirst()" ponemos el cursor al inicio de los datos almacenados. Lo encapsulamos
       en un if por si acaso no hay datos almacenados.

    Continuamos creando un objeto "Contactos" para almacenar los datos consultados de un registro, y
    los vamos recuperando del cursor con métodos get indicando la posición de la columna.

    Para terminar debemos cerrar la base de datos y el cursor.
    */

    public Usuarios recuperarUsuario(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"_id", "usuario", "email"};
        Cursor c = db.query("usuarios", valores_recuperar, "_id=" + id,
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
        Usuarios usuario = new Usuarios(c.getInt(0), c.getString(1), c.getString(2));
        db.close();
        c.close();
        return usuario;
    }


    //Leer todos los registro
    /**El método es muy similar al de leer un registro pero
     *  en este caso no especificamos que registro queremos recuperar, por lo tanto
     *  ponemos su parámetro a null. A parte creamos una variable "lista_contactos"
     *  donde almacenaremos todos los registros de la tabla en objetos contactos.
     *  En el bucle do-while usamos el método "moveToNext()" como parámetro que se
     *  encargara de pasar al siguiente registro de la tabla y por lo tanto recorrer
     *  todos los registros de la tabla.*/

    public List<Usuarios> recuperarCONTACTOS() {
        SQLiteDatabase db = getReadableDatabase();
        List<Usuarios> lista_contactos = new ArrayList<Usuarios>();
        String[] valores_recuperar = {"_id", "usuario", "email"};
        Cursor c = db.query("usuarios", valores_recuperar,
                null, null, null, null, null, null);
        c.moveToFirst();
        do {
            Usuarios usuarios = new Usuarios(c.getInt(0), c.getString(1),c.getString(2));
            lista_contactos.add(usuarios);
        } while (c.moveToNext());
        db.close();
        c.close();
        return lista_contactos;
    }
}
