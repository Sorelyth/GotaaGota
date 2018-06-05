package com.example.user.gotaagota.objetos;

import java.util.ArrayList;

/**
 * Created by User on 3/06/2018.
 */

public class cliente {
    private String id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String barrio;
    private ArrayList<deuda> deudas= new ArrayList<>();

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public ArrayList<deuda> getDeudas() {
        return deudas;
    }

    public void setDeudas(ArrayList<deuda> deudas) {
        this.deudas = deudas;
    }

    public cliente(String id, String nombre, String apellido, String telefono, String barrio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.barrio = barrio;
        this.deudas= new ArrayList<deuda>();
    }

    public cliente(String id, String nombre, String apellido, String telefono, String barrio, ArrayList<deuda> deudas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.barrio = barrio;
        this.deudas = deudas;
    }

    public cliente() {
    }


    @Override
    public String toString() {
        return "cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", barrio='" + barrio + '\'' +
                ", deudas=" + deudas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof cliente)) return false;

        cliente cliente = (cliente) o;

        return getId() != null ? getId().equals(cliente.getId()) : cliente.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
