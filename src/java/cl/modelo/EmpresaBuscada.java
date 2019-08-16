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
public class EmpresaBuscada {
    private String rut_socio;
    private String rut_empresa;
    private String nombre_agencia;
    private int id_puerto;
    private String nombre_puerto;
    private String tipo_contrato;

    public EmpresaBuscada() {
    }

    public EmpresaBuscada(String rut_socio, String rut_empresa, String nombre_agencia, int id_puerto, String nombre_puerto, String tipo_contrato) {
        this.rut_socio = rut_socio;
        this.rut_empresa = rut_empresa;
        this.nombre_agencia = nombre_agencia;
        this.id_puerto = id_puerto;
        this.nombre_puerto = nombre_puerto;
        this.tipo_contrato = tipo_contrato;
    }

    public String getRut_socio() {
        return rut_socio;
    }

    public void setRut_socio(String rut_socio) {
        this.rut_socio = rut_socio;
    }

    public String getRut_empresa() {
        return rut_empresa;
    }

    public void setRut_empresa(String rut_empresa) {
        this.rut_empresa = rut_empresa;
    }

    public String getNombre_agencia() {
        return nombre_agencia;
    }

    public void setNombre_agencia(String nombre_agencia) {
        this.nombre_agencia = nombre_agencia;
    }

    public int getId_puerto() {
        return id_puerto;
    }

    public void setId_puerto(int id_puerto) {
        this.id_puerto = id_puerto;
    }

    public String getNombre_puerto() {
        return nombre_puerto;
    }

    public void setNombre_puerto(String nombre_puerto) {
        this.nombre_puerto = nombre_puerto;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }
    
}
