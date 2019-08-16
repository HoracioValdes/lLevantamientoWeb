/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo;

/**
 *
 * @author Horacio
 */
public class Registro {
    private String nombre;
    private String rut;
    private String sindicato;
    private String valido;
    private String trabaja;
    private String pertenece;

    public Registro() {
    }

    public Registro(String nombre, String rut, String sindicato, String valido, String trabaja, String pertenece) {
        this.nombre = nombre;
        this.rut = rut;
        this.sindicato = sindicato;
        this.valido = valido;
        this.trabaja = trabaja;
        this.pertenece = pertenece;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getSindicato() {
        return sindicato;
    }

    public void setSindicato(String sindicato) {
        this.sindicato = sindicato;
    }

    public String getValido() {
        return valido;
    }

    public void setValido(String valido) {
        this.valido = valido;
    }

    public String getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(String trabaja) {
        this.trabaja = trabaja;
    }

    public String getPertenece() {
        return pertenece;
    }

    public void setPertenece(String pertenece) {
        this.pertenece = pertenece;
    } 
}
