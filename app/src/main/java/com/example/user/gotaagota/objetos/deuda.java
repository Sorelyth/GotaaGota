package com.example.user.gotaagota.objetos;

import java.util.ArrayList;

/**
 * Created by User on 3/06/2018.
 */

public class deuda {
    private int valor_prestado;
    private int p_intereses;
    private int cuotas;
    private int c_pagadas;
    private int c_no_pagadas;
    private int saldo_pendiente;
    private ArrayList<String> dias_cobro = new ArrayList<>();
    private boolean estado;

    public int getValor_prestado() {
        return valor_prestado;
    }

    public void setValor_prestado(int valor_prestado) {
        this.valor_prestado = valor_prestado;
    }

    public double getP_intereses() {
        return p_intereses;
    }

    public void setP_intereses(int p_intereses) {
        this.p_intereses = p_intereses;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public int getC_pagadas() {
        return c_pagadas;
    }

    public void setC_pagadas(int c_pagadas) {
        this.c_pagadas = c_pagadas;
    }

    public int getC_no_pagadas() {
        return c_no_pagadas;
    }

    public void setC_no_pagadas(int c_no_pagadas) {
        this.c_no_pagadas = c_no_pagadas;
    }

    public int getSaldo_pendiente() {
        return saldo_pendiente;
    }
    public void setSaldo_pendiente(int saldo_pendiente) {
        this.saldo_pendiente = saldo_pendiente;
    }

    public ArrayList<String> getDias_cobro() {
        return dias_cobro;
    }

    public void setDias_cobro(ArrayList<String> dias_cobro) {
        this.dias_cobro = dias_cobro;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public deuda() {
    }

    public deuda(int valor_prestado, int p_intereses, int cuotas, ArrayList<String> dias_cobro) {
        this.valor_prestado = valor_prestado;
        this.p_intereses = p_intereses;
        this.cuotas = cuotas;
        this.dias_cobro = dias_cobro;
        this.c_no_pagadas=0;
        this.c_pagadas=0;
        this.saldo_pendiente=0;
        this.estado=true;
    }

    @Override
    public String toString() {
        return "deuda{" +
                "valor_prestado=" + valor_prestado +
                ", p_intereses=" + p_intereses +
                ", cuotas=" + cuotas +
                ", c_pagadas=" + c_pagadas +
                ", c_no_pagadas=" + c_no_pagadas +
                ", saldo_pendiente=" + saldo_pendiente +
                ", dias_cobro=" + dias_cobro +
                ", estado=" + estado +
                '}';
    }

    //---------------------------------------------------------------------------

    public int valor_interes(int i){
        switch (i){
            case 1:
                return 5;
            case 2:
                return 10;
            case 3:
                return 15;
            case 4:
                return 20;
        }
        return -1;
    }

    public double ValorCuota(){
        double f = intereses();
        double auxcuota = ((valor_prestado + f ) / this.cuotas);
        return (int) auxcuota+(this.c_no_pagadas*auxcuota)+this.saldo_pendiente;

    }

    public double intereses(){
        return  this.getValor_prestado() * (this.p_intereses/100.0);
    }

    public int debecapital(){
        return this.valor_prestado/cuotas;
    }

    public int debeintereses(){
        return (int) (intereses()/cuotas);
    }

}