/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dao;

import cl.modelo.Registro;
import cl.modelo.ValidacionRegistro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Horacio
 */
public class DAOregistro extends Conectar{
    
    public Registro consultaSocio(String rut_socio) {
        Registro registro = new Registro();
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT SO.NOMBRE AS NOMBRE, SO.RUT_SOCIO AS RUT, SI.NOMBRE AS SINDICATO FROM SOCIO SO, SINDICATO SI WHERE SO.CODIGO LIKE SI.CODIGO AND RUT_SOCIO LIKE '" + rut_socio + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                registro.setNombre(res.getString("NOMBRE"));
                registro.setRut(res.getString("RUT"));
                registro.setSindicato(res.getString("SINDICATO"));
            }
            
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return registro;
    }
    
    public String consultaTrabaja(String rut_socio) {
        String confirmacion = "NO";
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM TRABAJA WHERE RUT_SOCIO LIKE '"+rut_socio+"'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                confirmacion = "SÍ";
            }
            
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return confirmacion;
    }
    
    public String consultaPertenece(String rut_socio) {
        String confirmacion = "NO";
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM PERTENECE WHERE RUT_SOCIO LIKE '"+rut_socio+"'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                confirmacion = "SÍ";
            }
            
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return confirmacion;
    }
    
    public boolean verificarExistencia(String rut_socio) {
        boolean confirmacion = false;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM SOCIO WHERE RUT_SOCIO LIKE '" + rut_socio + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.

            while (res.next()) {
                confirmacion = true;
            }

            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return confirmacion;
    }
}
