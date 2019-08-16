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
public class ComboComuna {
    private int cut;
    private String nombre_comuna;

    public ComboComuna() {
    }

    public ComboComuna(int cut, String nombre_comuna) {
        this.cut = cut;
        this.nombre_comuna = nombre_comuna;
    }

    public int getCut() {
        return cut;
    }

    public void setCut(int cut) {
        this.cut = cut;
    }

    public String getNombre_comuna() {
        return nombre_comuna;
    }

    public void setNombre_comuna(String nombre_comuna) {
        this.nombre_comuna = nombre_comuna;
    }
    
    
}
