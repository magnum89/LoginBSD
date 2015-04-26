package com.basesdatos.loginbsd;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.basesdatos.loginbsd.baseDatos.MiBaseDatos;


public class Principal extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        MiBaseDatos MDB = new MiBaseDatos(getApplicationContext());

        // Escribimos 4 registros en nuestra tabla
        MDB.insertarUSUARIO(1, "Pedro", "pedro@DB.es");
        MDB.insertarUSUARIO(2, "Sandra", "sandra@DB.es");
        MDB.insertarUSUARIO(3, "Maria", "maria@DB.es");
        MDB.insertarUSUARIO(4, "Daniel", "daniel@DB.es");

        // Recuperamos los 4 registros y los mostramos en el log
        Log.d("TOTAL", Integer.toString(MDB.recuperarCONTACTOS().size()));
        int[] ids = new int[MDB.recuperarCONTACTOS().size()];
        String[] usr = new String[MDB.recuperarCONTACTOS().size()];
        String[] emls = new String[MDB.recuperarCONTACTOS().size()];

        for (int i = 0; i < MDB.recuperarCONTACTOS().size(); i++)
        {
            ids[i] = MDB.recuperarCONTACTOS().get(i).getID();
            usr[i] = MDB.recuperarCONTACTOS().get(i).getUSUARIO();
            emls[i] = MDB.recuperarCONTACTOS().get(i).getEMAIL();
            Log.d(""+ids[i], usr[i] + ", " + emls[i]);
        }

        // Modificamos el registro 3
        MDB.modificarUSUARIO(3, "PPPPP", "xxxx@xxxx.es");

        // Recuperamos el 3 registro y lo mostramos en el log
        int id = MDB.recuperarUsuario(3).getID();
        String nombre = MDB.recuperarUsuario(3).getUSUARIO();
        String email = MDB.recuperarUsuario(3).getEMAIL();
        Log.d(""+id, nombre + ", " + email);

        // Borramos el registro 3
        MDB.borrarCONTACTO(3);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
