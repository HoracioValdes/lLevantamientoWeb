/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dao;

import cl.modelo.ComboComuna;
import cl.modelo.ComboEmpresa;
import cl.modelo.ComboNacion;
import cl.modelo.ComboSindicato;
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
public class DAOportuarios extends Conectar {

    public ArrayList<Ingreso> listarIngreso() {
        ArrayList<Ingreso> lstIngreso = new ArrayList();
        Ingreso ingreso;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM INGRESO";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                ingreso = new Ingreso();
                ingreso.setId(Integer.parseInt(res.getString("ID")));
                ingreso.setUsuario(res.getString("USUARIO"));
                ingreso.setClave(res.getString("CLAVE"));
                //Se agrega el INGRESO a la lista.
                lstIngreso.add(ingreso);
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return lstIngreso;
    }

    public ArrayList<ComboSindicato> listarSindicatos() {
        ArrayList<ComboSindicato> listaSindicatos = new ArrayList();
        ComboSindicato sindicato;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT CODIGO, NOMBRE FROM SINDICATO ORDER BY NOMBRE";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                sindicato = new ComboSindicato();
                sindicato.setCodigo(res.getString("CODIGO"));
                sindicato.setNombre(res.getString("NOMBRE"));
                //Se agrega el sindicato a la lista.
                listaSindicatos.add(sindicato);
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return listaSindicatos;
    }

    public ArrayList<ComboComuna> listarComunas() {
        ArrayList<ComboComuna> listaComunas = new ArrayList();
        ComboComuna comuna;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT CUT, NOMBRE_COMUNA FROM COMUNA ORDER BY NOMBRE_COMUNA";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                comuna = new ComboComuna();
                comuna.setCut(Integer.parseInt(res.getString("CUT")));
                comuna.setNombre_comuna(res.getString("NOMBRE_COMUNA"));
                //Se agrega la comuna a la lista.
                listaComunas.add(comuna);
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return listaComunas;
    }

    public ArrayList<ComboNacion> listarNacion() {
        ArrayList<ComboNacion> listaNacion = new ArrayList();
        ComboNacion nacion;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT NOMBRE FROM NACIONALIDAD";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                nacion = new ComboNacion();
                nacion.setNombre(res.getString("NOMBRE"));
                //Se agrega la nacionalidad a la lista.
                listaNacion.add(nacion);
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return listaNacion;
    }

    public String obtenerNombreComuna(int codigo_comuna) {
        String comuna = "";
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT Nombre_Comuna FROM COMUNA WHERE CUT = " + codigo_comuna + "";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                comuna = res.getString("Nombre_Comuna");
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return comuna;
    }

    public String obtenerNombreSindicato(String codigo) {
        String sindicato = "";
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
                sindicato = res.getString("NOMBRE");
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return sindicato;
    }

    public int consultarNumeroPuertos(String rut_socio) {
        int numeroPuertos = 0;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT COUNT(*) AS NUMERO_PUERTOS FROM TRABAJA WHERE RUT_SOCIO = '" + rut_socio + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                numeroPuertos = res.getInt("NUMERO_PUERTOS");
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return numeroPuertos;
    }
    
    public int consultarNumeroPertenece(String rut_socio) {
        int numeroPertenece = 0;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT COUNT(*) AS NUMERO_PERTENECE FROM PERTENECE WHERE RUT_SOCIO = '" + rut_socio + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                numeroPertenece = res.getInt("NUMERO_PERTENECE");
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return numeroPertenece;
    }
    
    public boolean existenciaPrevia(String rut_socio) {
        boolean existencia = false;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM SOCIO WHERE RUT_SOCIO = '" + rut_socio + "'";
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
    
    public boolean consultaPertenece(String rut_socio) {
        boolean existencia = false;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM PERTENECE WHERE RUT_SOCIO = '" + rut_socio + "'";
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
    
    public boolean consultaTrabaja(String rut_socio) {
        boolean existencia = false;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM TRABAJA WHERE RUT_SOCIO = '" + rut_socio + "'";
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

    public boolean eliminarTrabajoPuertos(String rut_socio) {
        boolean confirmacion = true;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            PreparedStatement eliminar;
            eliminar = con.prepareStatement("DELETE TRABAJA FROM TRABAJA WHERE RUT_SOCIO = '" + rut_socio + "';");
            eliminar.execute();
        } catch (ClassNotFoundException | SQLException error) {
            confirmacion = false;
        }
        return confirmacion;
    }
    
    public boolean eliminarPerteneceEmpresa(String rut_socio) {
        boolean confirmacion = true;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            PreparedStatement eliminar;
            eliminar = con.prepareStatement("DELETE PERTENECE FROM PERTENECE WHERE RUT_SOCIO = '" + rut_socio + "';");
            eliminar.execute();
        } catch (ClassNotFoundException | SQLException error) {
            confirmacion = false;
        }
        return confirmacion;
    }

    public boolean eliminarSocio(String rut_socio) {
        boolean confirmacionEliminacionPuertos = false;
        boolean confirmacionEliminacionPertenece = false;
        boolean confirmacion = false;

        if(consultarNumeroPertenece(rut_socio) > 0) {
            confirmacionEliminacionPertenece = eliminarPerteneceEmpresa(rut_socio);
        } else {
            confirmacionEliminacionPertenece = true;
        }
        
        if (consultarNumeroPuertos(rut_socio) > 0) {
            confirmacionEliminacionPuertos = eliminarTrabajoPuertos(rut_socio);
        } else {
            confirmacionEliminacionPuertos = true;
        }

        if (confirmacionEliminacionPuertos && confirmacionEliminacionPertenece) {
            try {
                //Recuperar una conexión.
                Connection con = this.getConexion();
                PreparedStatement eliminarSocio;
                eliminarSocio = con.prepareStatement("DELETE SOCIO FROM SOCIO WHERE RUT_SOCIO = '" +rut_socio+ "';");
                eliminarSocio.execute();
                confirmacion = true;
            } catch (ClassNotFoundException | SQLException error) {
                confirmacion = false;
            }
        }
        return confirmacion;
    }
    
    public boolean ingresarAuditoria(int id_ingreso, String operacion, String tabla_alterada, String pk_socio_rut){
        int cantFilas = 0;
        boolean ingreso = false;
        
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "INSERT INTO AUDITORIA VALUES(NEXT VALUE FOR ID_AUDITORIA_SEC, "+id_ingreso+", '"+operacion+"', '"+tabla_alterada+"', '"+pk_socio_rut+"', NULL, NULL, GETUTCDATE(), NULL, NULL, NULL)";
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

    public int registrarSocio(Socio socio) {
        int cantFilas = 0;

        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "INSERT INTO SOCIO VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //Definir el valor de los parámetros ("?")
            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getSexo());
            ps.setInt(3, socio.getEdad());
            ps.setString(4, socio.getComuna_residencia());
            ps.setInt(5, socio.getCodigo_comuna_residencia());
            ps.setString(6, socio.getComuna_antigua());
            ps.setInt(7, socio.getCodigo_comuna_antigua());
            ps.setString(8, socio.getNacionalidad());
            ps.setString(9, socio.getEducacion());
            ps.setInt(10, socio.getMiembros_hogar());
            ps.setInt(11, socio.getProveedor_principal());
            ps.setInt(12, socio.getPersonas_trabajando());
            ps.setInt(13, socio.getPersonas_buscando());
            ps.setInt(14, socio.getOtro_hogar());
            ps.setInt(15, socio.getExperiencia_años());
            ps.setInt(16, socio.getOcupacion_aparte());
            ps.setString(17, socio.getActividad_principal());
            ps.setInt(18, socio.getMas_puerto());
            ps.setString(19, socio.getAfiliado());
            ps.setString(20, socio.getAfp());
            ps.setInt(21, socio.getCotizacion());
            ps.setString(22, socio.getCanal_informacion());
            ps.setString(23, socio.getCodigo());
            ps.setString(24, socio.getRut_socio());
            ps.setString(25, socio.getFecha_nacimiento());
            ps.setString(26, socio.getCasa_propia());
            ps.setInt(27, socio.getIsapre());
            ps.setString(28, socio.getDireccion());
            ps.setInt(29, socio.getNumero_puertos());
            ps.setInt(30, socio.getFamilia_puertos());
            ps.setString(31, socio.getRut_familiar_uno());
            ps.setString(32, socio.getRut_familiar_dos());
            cantFilas = ps.executeUpdate();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName()).log(Level.SEVERE, "Problema registro del Driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName()).log(Level.SEVERE, "Error SQL.", ex);
        }
        return cantFilas;
    }

    public int modificarSocio(Socio socio) {
        int cantFilas = 0;

        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "UPDATE SOCIO SET NOMBRE = '" + socio.getNombre() + "', SEXO = '" + socio.getSexo() + "', EDAD = '" + socio.getEdad() + "', "
                    + "COMUNA_RESIDENCIA = '" + socio.getComuna_residencia() + "', CODIGO_COMUNA_RESIDENCIA = '" + socio.getCodigo_comuna_residencia() + "', COMUNA_ANTIGUA = '" + socio.getComuna_antigua() + "', "
                    + "CODIGO_COMUNA_ANTIGUA = '" + socio.getCodigo_comuna_antigua() + "', NACIONALIDAD = '" + socio.getNacionalidad() + "', EDUCACION = '" + socio.getEducacion() + "', MIEMBROS_HOGAR = '" + socio.getMiembros_hogar() + "', "
                    + "PROVEEDOR_PRINCIPAL = '" + socio.getProveedor_principal() + "', PERSONAS_TRABAJANDO = '" + socio.getPersonas_trabajando() + "', PERSONAS_BUSCANDO = '" + socio.getPersonas_buscando() + "', "
                    + "OTRO_HOGAR = '" + socio.getOtro_hogar() + "', EXPERIENCIA_AÑOS = '" + socio.getExperiencia_años() + "', "
                    + "OCUPACION_APARTE = '" + socio.getOcupacion_aparte() + "', ACTIVIDAD_PRINCIPAL = '" + socio.getActividad_principal() + "', MAS_PUERTO = '" + socio.getMas_puerto() + "', "
                    + "AFILIADO = '" + socio.getAfiliado() + "', AFP = '" + socio.getAfp() + "', COTIZACION = '" + socio.getCotizacion() + "', CANAL_INFORMACION = '" + socio.getCanal_informacion() + "', "
                    + "CODIGO = '" + socio.getCodigo() + "', FECHA_NACIMIENTO = '" + socio.getFecha_nacimiento() + "', CASA_PROPIA = '" + socio.getCasa_propia() + "', ISAPRE = '" + socio.getIsapre() + "', DIRECCION = '" + socio.getDireccion()  + "', NUMERO_PUERTOS = '" + socio.getNumero_puertos() + "', FAMILIA_PUERTOS = '" + socio.getFamilia_puertos() + "', RUT_FAMILIAR_UNO = '" + socio.getRut_familiar_uno() + "', RUT_FAMILIAR_DOS = '" + socio.getRut_familiar_dos() + "' WHERE RUT_SOCIO = '" + socio.getRut_socio() + "'";
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

    public Socio buscarSocio(String rut) {
        Socio socio = new Socio();
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM SOCIO WHERE RUT_SOCIO = '" + rut + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                socio.setNombre(res.getString("NOMBRE"));
                socio.setSexo(res.getString("SEXO"));
                socio.setEdad(Integer.parseInt(res.getString("EDAD")));
                socio.setComuna_residencia(res.getString("COMUNA_RESIDENCIA"));
                socio.setCodigo_comuna_residencia(Integer.parseInt(res.getString("CODIGO_COMUNA_RESIDENCIA")));
                socio.setComuna_antigua(res.getString("COMUNA_ANTIGUA"));
                socio.setCodigo_comuna_antigua(Integer.parseInt(res.getString("CODIGO_COMUNA_ANTIGUA")));
                socio.setNacionalidad(res.getString("NACIONALIDAD"));
                socio.setEducacion(res.getString("EDUCACION"));
                socio.setMiembros_hogar(Integer.parseInt(res.getString("MIEMBROS_HOGAR")));
                socio.setProveedor_principal(Integer.parseInt(res.getString("PROVEEDOR_PRINCIPAL")));
                socio.setPersonas_trabajando(Integer.parseInt(res.getString("PERSONAS_TRABAJANDO")));
                socio.setPersonas_buscando(Integer.parseInt(res.getString("PERSONAS_BUSCANDO")));
                socio.setOtro_hogar(Integer.parseInt(res.getString("OTRO_HOGAR")));
                socio.setExperiencia_años(Integer.parseInt(res.getString("EXPERIENCIA_AÑOS")));
                socio.setOcupacion_aparte(Integer.parseInt(res.getString("OCUPACION_APARTE")));
                socio.setActividad_principal(res.getString("ACTIVIDAD_PRINCIPAL"));
                socio.setMas_puerto(Integer.parseInt(res.getString("MAS_PUERTO")));
                socio.setAfiliado(res.getString("AFILIADO"));
                socio.setAfp(res.getString("AFP"));
                socio.setCotizacion(Integer.parseInt(res.getString("COTIZACION")));
                socio.setCanal_informacion(res.getString("CANAL_INFORMACION"));
                socio.setCodigo(res.getString("CODIGO"));
                socio.setRut_socio(res.getString("RUT_SOCIO"));
                socio.setFecha_nacimiento(res.getString("FECHA_NACIMIENTO"));
                socio.setCasa_propia(res.getString("CASA_PROPIA"));
                socio.setIsapre(Integer.parseInt(res.getString("ISAPRE")));
                socio.setDireccion(res.getString("DIRECCION"));
                socio.setNumero_puertos(Integer.parseInt(res.getString("NUMERO_PUERTOS")));
                socio.setFamilia_puertos(Integer.parseInt(res.getString("FAMILIA_PUERTOS")));
                socio.setRut_familiar_uno(res.getString("RUT_FAMILIAR_UNO"));
                socio.setRut_familiar_dos(res.getString("RUT_FAMILIAR_DOS"));
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return socio;
    }
}
