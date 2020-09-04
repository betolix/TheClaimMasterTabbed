package net.blix.theclaimmaster.Beans;

import android.content.ContentValues;

import net.blix.theclaimmaster.Contract.UserContract;
//ADAPTAR A LO Q REQUIERE MI USER BEAN
public class UserBean {

    private String id;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String dni;
    private String usuario;
    private String password;

    public UserBean() {
    }

    public UserBean(String id, String nombre, String apellido_paterno, String apellido_materno, String dni, String usuario, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.dni = dni;
        this.usuario = usuario;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public ContentValues contentValues(){
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.IDCLIENTE,id);
        values.put(UserContract.UserEntry.NOMBRE,nombre);
        values.put(UserContract.UserEntry.APELLIDOPATERNO,id);
        values.put(UserContract.UserEntry.APELLIDOMATERNO,id);
        values.put(UserContract.UserEntry.DNI,dni);
        values.put(UserContract.UserEntry.USUARIO,usuario);
        values.put(UserContract.UserEntry.PASSWORD,password);
        return values;
    }


}

