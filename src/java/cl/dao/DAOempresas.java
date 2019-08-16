/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dao;

import cl.modelo.ComboComuna;
import cl.modelo.ComboEmpresa;
import cl.modelo.ComboNacion;
import cl.modelo.ComboPuerto;
import cl.modelo.ComboSindicato;
import cl.modelo.EmpresaBuscada;
import cl.modelo.Ingreso;
import cl.modelo.Socio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Horacio Valdés
 */
public class DAOempresas extends Conectar {
    
    public ArrayList<ComboPuerto> listarPuertos() {
        ArrayList<ComboPuerto> listaPuertos = new ArrayList();
        ComboPuerto puerto;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT ID_PUERTO, NOMBRE FROM PUERTO ORDER BY NOMBRE";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                puerto = new ComboPuerto();
                puerto.setId_puerto(Integer.parseInt(res.getString("ID_PUERTO")));
                puerto.setNombre(res.getString("NOMBRE"));
                //Se agrega el sindicato a la lista.
                listaPuertos.add(puerto);
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return listaPuertos;
    }

    public ArrayList<ComboEmpresa> listarEmpresas() {
        ArrayList<ComboEmpresa> listaEmpresa = new ArrayList();
        ComboEmpresa empresa;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT RUT_EMPRESA, NOMBRE_AGENCIA FROM EMPRESA ORDER BY NOMBRE_AGENCIA";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                empresa = new ComboEmpresa();
                empresa.setRut_empresa(res.getString("RUT_EMPRESA"));
                empresa.setNombre_agencia(res.getString("NOMBRE_AGENCIA"));
                //Se agrega la nacionalidad a la lista.
                listaEmpresa.add(empresa);
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return listaEmpresa;
    }

    public String obtenerNombreEmpresa(String rut_empresa) {
        String empresa = "";
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT NOMBRE_AGENCIA FROM EMPRESA WHERE RUT_EMPRESA = '" + rut_empresa + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                empresa = res.getString("NOMBRE_AGENCIA");
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return empresa;
    }

    public int consultarNumeroEmpresas(String rut_socio, int id_puerto) {
        int numeroEmpresas = 0;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT COUNT(*) AS NUMERO_EMPRESAS FROM PERTENECE WHERE RUT_SOCIO = '" + rut_socio + "' AND ID_PUERTO = " + id_puerto + "";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                numeroEmpresas = res.getInt("NUMERO_EMPRESAS");
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return numeroEmpresas;
    }
    
    public boolean existenciaPrevia(String rut_socio, int id_puerto) {
        boolean existencia = false;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM TRABAJA WHERE RUT_SOCIO = '" + rut_socio + "' AND ID_PUERTO  = " + id_puerto + "";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                existencia = true;
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return existencia;
    }
    
    public boolean existenciaPreviaEmpresa(String rut_socio, int id_puerto, String rut_empresa) {
        boolean existencia = false;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM PERTENECE WHERE RUT_SOCIO = '" + rut_socio + "' AND ID_PUERTO  = " + id_puerto + " AND RUT_EMPRESA  = '" + rut_empresa + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                existencia = true;
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return existencia;
    }

    public boolean eliminarPerteneceEmpresa(int id_puerto, String rut_socio, String rut_empresa) {
        boolean confirmacion = true;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            PreparedStatement eliminar;
            eliminar = con.prepareStatement("DELETE PERTENECE FROM PERTENECE WHERE ID_PUERTO = " + id_puerto + " AND RUT_SOCIO LIKE '" + rut_socio + "' AND RUT_EMPRESA LIKE '"+ rut_empresa +"';");
            eliminar.execute();
        } catch (ClassNotFoundException | SQLException error) {
            confirmacion = false;
        }
        return confirmacion;
    }
    
    public boolean ingresarAuditoria(int id_ingreso, String operacion, String tabla_alterada, int pk_pertenece_id_puerto, String pk_pertenece_rut_socio, String pk_pertenece_rut_empresa){
        int cantFilas = 0;
        boolean ingreso = false;
        
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "INSERT INTO AUDITORIA VALUES(NEXT VALUE FOR ID_AUDITORIA_SEC, "+id_ingreso+", '"+operacion+"', '"+tabla_alterada+"', NULL, NULL, NULL, GETUTCDATE(), '"+pk_pertenece_rut_socio+"', '"+pk_pertenece_rut_empresa+"', "+pk_pertenece_id_puerto+")";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            cantFilas = ps.executeUpdate();
            if(cantFilas > 0){
                ingreso = true;
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName()).log(Level.SEVERE, "Problema registro del Driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName()).log(Level.SEVERE, "Error SQL.", ex);
        }
        return ingreso;
    }

    public int registrarPertenece(int id_puerto, String rut_socio, String rut_empresa, String tipo_contrato) {
        int cantFilas = 0;

        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "INSERT INTO PERTENECE VALUES(?,?,?,?)";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //Definir el valor de los parámetros ("?")
            ps.setInt(1, id_puerto);
            ps.setString(2, rut_socio);
            ps.setString(3, rut_empresa);
            ps.setString(4, tipo_contrato);
            cantFilas = ps.executeUpdate();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName()).log(Level.SEVERE, "Problema registro del Driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName()).log(Level.SEVERE, "Error SQL.", ex);
        }
        return cantFilas;
    }
    
    public int modificarPertenece(int id_puerto, String rut_socio, String rut_empresa, String tipo_contrato) {
        int cantFilas = 0;

        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "UPDATE PERTENECE SET TIPO_CONTRATO = '" + tipo_contrato + "' WHERE RUT_SOCIO = '" + rut_socio + "' AND ID_PUERTO = " + id_puerto + " AND RUT_EMPRESA = '" + rut_empresa + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            cantFilas = ps.executeUpdate();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName()).log(Level.SEVERE, "Problema registro del Driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName()).log(Level.SEVERE, "Error SQL.", ex);
        }
        return cantFilas;
    }
    
    public ArrayList<EmpresaBuscada> listarEmpresasIngresadas(String rut_socio, int id_puerto) {
        ArrayList<EmpresaBuscada> listaEmpresasIngresadas = new ArrayList();
        EmpresaBuscada empresaBuscada;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT P.RUT_SOCIO, P.RUT_EMPRESA, P.ID_PUERTO, P.TIPO_CONTRATO, PU.NOMBRE AS NOMBRE_PUERTO, E.NOMBRE_AGENCIA FROM PERTENECE P, EMPRESA E, TRABAJA T, PUERTO PU WHERE E.RUT_EMPRESA = P.RUT_EMPRESA AND P.ID_PUERTO = T.ID_PUERTO AND P.RUT_SOCIO = T.RUT_SOCIO AND T.ID_PUERTO = PU.ID_PUERTO AND P.RUT_SOCIO LIKE '" + rut_socio + "' AND P.ID_PUERTO = " + id_puerto + "";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                empresaBuscada = new EmpresaBuscada();
                empresaBuscada.setRut_socio(res.getString("RUT_SOCIO"));
                empresaBuscada.setRut_empresa(res.getString("RUT_EMPRESA"));
                empresaBuscada.setId_puerto(Integer.parseInt(res.getString("ID_PUERTO")));
                empresaBuscada.setTipo_contrato(res.getString("TIPO_CONTRATO"));
                empresaBuscada.setNombre_puerto(res.getString("NOMBRE_PUERTO"));
                empresaBuscada.setNombre_agencia(res.getString("NOMBRE_AGENCIA"));
                //Se agrega el sindicato a la lista.
                listaEmpresasIngresadas.add(empresaBuscada);
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return listaEmpresasIngresadas;
    }
    
    public EmpresaBuscada buscarPertenece(String rut_empresa, String rut_socio, int id_puerto) {
        EmpresaBuscada empresaBuscada = new EmpresaBuscada();;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT P.RUT_SOCIO, P.RUT_EMPRESA, P.ID_PUERTO, P.TIPO_CONTRATO, PU.NOMBRE AS NOMBRE_PUERTO, E.NOMBRE_AGENCIA FROM PERTENECE P, EMPRESA E, TRABAJA T, PUERTO PU WHERE E.RUT_EMPRESA = P.RUT_EMPRESA AND P.ID_PUERTO = T.ID_PUERTO AND P.RUT_SOCIO = T.RUT_SOCIO AND T.ID_PUERTO = PU.ID_PUERTO AND P.RUT_SOCIO LIKE '" + rut_socio + "' AND P.ID_PUERTO = " + id_puerto + " AND P.RUT_EMPRESA LIKE '" + rut_empresa + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                empresaBuscada.setRut_socio(res.getString("RUT_SOCIO"));
                empresaBuscada.setRut_empresa(res.getString("RUT_EMPRESA"));
                empresaBuscada.setId_puerto(Integer.parseInt(res.getString("ID_PUERTO")));
                empresaBuscada.setTipo_contrato(res.getString("TIPO_CONTRATO"));
                empresaBuscada.setNombre_puerto(res.getString("NOMBRE_PUERTO"));
                empresaBuscada.setNombre_agencia(res.getString("NOMBRE_AGENCIA"));
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return empresaBuscada;
    }
}
