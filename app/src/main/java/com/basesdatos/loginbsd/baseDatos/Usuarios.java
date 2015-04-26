package com.basesdatos.loginbsd.baseDatos;


/** Para leer registros vamos a crear una clase "Contactos" con un constructor
 y varios métodos get/set para convertir una fila de la tabla en un objeto
 y nos sea mas fácil acceder a cualquier valor de ese registro. Los métodos
 get/set simplemente son métodos para recuperar o establecer un valor del
 objeto Contactos.*/

public class Usuarios {

    private int id;
    private String usuario;
    private int telefono;
    private String email;

    // Constructor de un objeto Contactos
    public Usuarios(int id, String usuario, String email) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
    }

    // Recuperar/establecer ID
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }

    // Recuperar/establecer NOMBRE
    public String getUSUARIO() {
        return usuario;
    }
    public void setUSUARIO(String usuario) {
        this.usuario = usuario;
    }

    // Recuperar/establecer EMAIL
    public String getEMAIL() {
        return email;
    }
    public void setEMAIL(String email) {
        this.email = email;
    }
}
