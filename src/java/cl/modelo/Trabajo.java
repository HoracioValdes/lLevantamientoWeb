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
public class Trabajo {
    private int id_puerto;
    private int enero;
    private int febrero;
    private int marzo;
    private int abril;
    private int mayo;
    private int junio;
    private int julio;
    private int agosto;
    private int septiembre;
    private int octubre;
    private int noviembre;
    private int diciembre;
    private int turnos;
    private String seccion;
    private String funcion;
    private int accidente;
    private String rut_socio;

    public Trabajo() {
    }

    public Trabajo(int id_puerto, int enero, int febrero, int marzo, int abril, int mayo, int junio, int julio, int agosto, int septiembre, int octubre, int noviembre, int diciembre, int turnos, String seccion, String funcion, int accidente, String rut_socio) {
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
        this.rut_socio = rut_socio;
    }

    public int getId_puerto() {
        return id_puerto;
    }

    public void setId_puerto(int id_puerto) {
        this.id_puerto = id_puerto;
    }

    public int getEnero() {
        return enero;
    }

    public void setEnero(int enero) {
        this.enero = enero;
    }

    public int getFebrero() {
        return febrero;
    }

    public void setFebrero(int febrero) {
        this.febrero = febrero;
    }

    public int getMarzo() {
        return marzo;
    }

    public void setMarzo(int marzo) {
        this.marzo = marzo;
    }

    public int getAbril() {
        return abril;
    }

    public void setAbril(int abril) {
        this.abril = abril;
    }

    public int getMayo() {
        return mayo;
    }

    public void setMayo(int mayo) {
        this.mayo = mayo;
    }

    public int getJunio() {
        return junio;
    }

    public void setJunio(int junio) {
        this.junio = junio;
    }

    public int getJulio() {
        return julio;
    }

    public void setJulio(int julio) {
        this.julio = julio;
    }

    public int getAgosto() {
        return agosto;
    }

    public void setAgosto(int agosto) {
        this.agosto = agosto;
    }

    public int getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(int septiembre) {
        this.septiembre = septiembre;
    }

    public int getOctubre() {
        return octubre;
    }

    public void setOctubre(int octubre) {
        this.octubre = octubre;
    }

    public int getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(int noviembre) {
        this.noviembre = noviembre;
    }

    public int getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(int diciembre) {
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

    public int getAccidente() {
        return accidente;
    }

    public void setAccidente(int accidente) {
        this.accidente = accidente;
    }

    public String getRut_socio() {
        return rut_socio;
    }

    public void setRut_socio(String rut_socio) {
        this.rut_socio = rut_socio;
    }
}
