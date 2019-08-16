/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dao;

import cl.modelo.Encuestador;
import cl.modelo.Ingreso;
import cl.modelo.ValidacionRegistro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Horacio
 */
public class DAOauditoria extends Conectar {

    public Encuestador identificarEncuestador(int id) {
        int id_encuestador = id;
        Encuestador encuestador;
        String codigo = "";
        encuestador = new Encuestador();

        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM INGRESO WHERE ID = " + id_encuestador + "";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                encuestador.setUsuario(res.getString("USUARIO"));
                encuestador.setRut(res.getString("RUT_EMPADRONADOR"));
                encuestador.setNombre(res.getString("NOMBRE_EMPADRONADOR"));
                codigo = res.getString("CODIGO");
            }

            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }

        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT NOMBRE FROM SINDICATO WHERE CODIGO = '" + codigo + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                encuestador.setSindicato(res.getString("NOMBRE"));
            }

            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }

        return encuestador;
    }

    public ArrayList<String> listarRegistros(int id) {
        ArrayList<String> lstRegistros = new ArrayList();
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT DISTINCT PK_SOCIO_RUT FROM AUDITORIA WHERE ID = " + id + " AND TABLA_ALTERADA LIKE 'SOCIO' AND OPERACION LIKE 'INGRESAR'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                //Se agrega el RUT a la lista.
                lstRegistros.add(res.getString("PK_SOCIO_RUT"));
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return lstRegistros;
    }

    public ValidacionRegistro consultarTrabajador(String rut_socio) {
        ValidacionRegistro registro = new ValidacionRegistro();
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT NOMBRE, RUT_SOCIO FROM SOCIO WHERE RUT_SOCIO LIKE '" + rut_socio + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                registro.setNombre(res.getString("NOMBRE"));
                registro.setRut(res.getString("RUT_SOCIO"));
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

    public String consultarTrabajadorDos(String rut_socio) {
        String rut = "";
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT DISTINCT(SO.RUT_SOCIO) AS RUT FROM SOCIO SO, TRABAJA TR, PERTENECE PE WHERE SO.RUT_SOCIO LIKE TR.RUT_SOCIO AND SO.RUT_SOCIO LIKE PE.RUT_SOCIO AND SO.RUT_SOCIO LIKE '" + rut_socio + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.

            while (res.next()) {
                rut = res.getString("RUT");
            }

            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return rut;
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

    public int consultarTrabaja(String rut_socio) {
        int trabaja = 0;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT COUNT(*) AS ESTA FROM TRABAJA WHERE RUT_SOCIO LIKE '" + rut_socio + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                trabaja = Integer.parseInt(res.getString("ESTA"));
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return trabaja;
    }

    public int consultarPertenece(String rut_socio) {
        int pertenece = 0;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT COUNT(*) AS ESTA FROM PERTENECE WHERE RUT_SOCIO LIKE '" + rut_socio + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                pertenece = Integer.parseInt(res.getString("ESTA"));
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return pertenece;
    }
}
