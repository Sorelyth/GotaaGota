package com.example.user.gotaagota.objetos;

import java.util.ArrayList;

/**
 * Created by User on 3/06/2018.
 */

public class prestador {
    private String id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contraseña;
    //private ArrayList<cobrador> cobradores= new ArrayList<>();
    private ArrayList<cliente> clientes = new ArrayList<>();
    private int capital;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ArrayList<cliente> getclientes() {
        return clientes;
    }

    public void setclientes(ArrayList<cliente> clientes) {
        this.clientes = clientes;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public prestador() {
    }

    public prestador(String id, String nombre, String apellido, String usuario, String contraseña, int capital) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.capital = capital;
        this.clientes = new ArrayList<cliente>();
    }

    public prestador(String id, String nombre, String apellido, String usuario, String contraseña, ArrayList<cliente> clientes, int capital) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.clientes = clientes;
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "prestador{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", clientes=" + clientes +
                ", capital=" + capital +
                '}';
    }
}
