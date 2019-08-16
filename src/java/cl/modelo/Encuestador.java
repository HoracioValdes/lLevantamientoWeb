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
public class Encuestador {
    private String usuario;
    private String rut;
    private String nombre;
    private String sindicato;

    public Encuestador() {
    }

    public Encuestador(String usuario, String rut, String nombre, String sindicato) {
        this.usuario = usuario;
        this.rut = rut;
        this.nombre = nombre;
        this.sindicato = sindicato;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSindicato() {
        return sindicato;
    }

    public void setSindicato(String sindicato) {
        this.sindicato = sindicato;
    }
}
