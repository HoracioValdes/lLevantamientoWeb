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
public class ComboEmpresa {
    private String rut_empresa;
    private String nombre_agencia;

    public ComboEmpresa() {
    }

    public ComboEmpresa(String rut_empresa, String nombre_agencia) {
        this.rut_empresa = rut_empresa;
        this.nombre_agencia = nombre_agencia;
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
    
    
}
