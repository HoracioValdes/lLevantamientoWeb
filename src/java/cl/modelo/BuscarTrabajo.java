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
public class BuscarTrabajo {
    private String rut_socio;
    private String nombre_socio;
    private String sindicato;
    private int id_puerto;
    private String puerto;

    public BuscarTrabajo() {
    }

    public BuscarTrabajo(String rut_socio, String nombre_socio, String sindicato, int id_puerto, String puerto) {
        this.rut_socio = rut_socio;
        this.nombre_socio = nombre_socio;
        this.sindicato = sindicato;
        this.id_puerto = id_puerto;
        this.puerto = puerto;
    }

    public String getRut_socio() {
        return rut_socio;
    }

    public void setRut_socio(String rut_socio) {
        this.rut_socio = rut_socio;
    }

    public String getNombre_socio() {
        return nombre_socio;
    }

    public void setNombre_socio(String nombre_socio) {
        this.nombre_socio = nombre_socio;
    }

    public String getSindicato() {
        return sindicato;
    }

    public void setSindicato(String sindicato) {
        this.sindicato = sindicato;
    }

    public int getId_puerto() {
        return id_puerto;
    }

    public void setId_puerto(int id_puerto) {
        this.id_puerto = id_puerto;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }
}
