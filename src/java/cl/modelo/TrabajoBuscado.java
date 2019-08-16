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
public class TrabajoBuscado {
    private String rut_socio;
    private String nombre_socio;
    private String puerto;
    private int id_puerto;
    private String enero;
    private String febrero;
    private String marzo;
    private String abril;
    private String mayo;
    private String junio;
    private String julio;
    private String agosto;
    private String septiembre;
    private String octubre;
    private String noviembre;
    private String diciembre;
    private int turnos;
    private String seccion;
    private String funcion;
    private String accidente;

    public TrabajoBuscado() {
    }

    public TrabajoBuscado(String rut_socio, String nombre_socio, String puerto, int id_puerto, String enero, String febrero, String marzo, String abril, String mayo, String junio, String julio, String agosto, String septiembre, String octubre, String noviembre, String diciembre, int turnos, String seccion, String funcion, String accidente) {
        this.rut_socio = rut_socio;
        this.nombre_socio = nombre_socio;
        this.puerto = puerto;
        this.id_puerto = id_puerto;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
        this.abril = abril;
        this.mayo = mayo;
        this.junio = junio;
        this.julio = julio;
        this.agosto = agosto;
        this.septiembre = septiembre;
        this.octubre = octubre;
        this.noviembre = noviembre;
        this.diciembre = diciembre;
        this.turnos = turnos;
        this.seccion = seccion;
        this.funcion = funcion;
        this.accidente = accidente;
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

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public int getId_puerto() {
        return id_puerto;
    }

    public void setId_puerto(int id_puerto) {
        this.id_puerto = id_puerto;
    }

    public String getEnero() {
        return enero;
    }

    public void setEnero(String enero) {
        this.enero = enero;
    }

    public String getFebrero() {
        return febrero;
    }

    public void setFebrero(String febrero) {
        this.febrero = febrero;
    }

    public String getMarzo() {
        return marzo;
    }

    public void setMarzo(String marzo) {
        this.marzo = marzo;
    }

    public String getAbril() {
        return abril;
    }

    public void setAbril(String abril) {
        this.abril = abril;
    }

    public String getMayo() {
        return mayo;
    }

    public void setMayo(String mayo) {
        this.mayo = mayo;
    }

    public String getJunio() {
        return junio;
    }

    public void setJunio(String junio) {
        this.junio = junio;
    }

    public String getJulio() {
        return julio;
    }

    public void setJulio(String julio) {
        this.julio = julio;
    }

    public String getAgosto() {
        return agosto;
    }

    public void setAgosto(String agosto) {
        this.agosto = agosto;
    }

    public String getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(String septiembre) {
        this.septiembre = septiembre;
    }

    public String getOctubre() {
        return octubre;
    }

    public void setOctubre(String octubre) {
        this.octubre = octubre;
    }

    public String getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(String noviembre) {
        this.noviembre = noviembre;
    }

    public String getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(String diciembre) {
        this.diciembre = diciembre;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getAccidente() {
        return accidente;
    }

    public void setAccidente(String accidente) {
        this.accidente = accidente;
    }
}
