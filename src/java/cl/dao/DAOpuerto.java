/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dao;

import cl.modelo.BuscarTrabajo;
import cl.modelo.ComboPuerto;
import cl.modelo.Trabajo;
import cl.modelo.TrabajoBuscado;
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
public class DAOpuerto extends Conectar {

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

    public int registrarTrabajo(Trabajo trabajo) {
        int cantFilas = 0;

        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "INSERT INTO TRABAJA VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //Definir el valor de los parámetros ("?")
            ps.setInt(1, trabajo.getId_puerto());
            ps.setInt(2, trabajo.getEnero());
            ps.setInt(3, trabajo.getFebrero());
            ps.setInt(4, trabajo.getMarzo());
            ps.setInt(5, trabajo.getAbril());
            ps.setInt(6, trabajo.getMayo());
            ps.setInt(7, trabajo.getJunio());
            ps.setInt(8, trabajo.getJulio());
            ps.setInt(9, trabajo.getAgosto());
            ps.setInt(10, trabajo.getSeptiembre());
            ps.setInt(11, trabajo.getOctubre());
            ps.setInt(12, trabajo.getNoviembre());
            ps.setInt(13, trabajo.getDiciembre());
            ps.setInt(14, trabajo.getTurnos());
            ps.setString(15, trabajo.getSeccion());
            ps.setString(16, trabajo.getFuncion());
            ps.setInt(17, trabajo.getAccidente());
            ps.setString(18, trabajo.getRut_socio());
            cantFilas = ps.executeUpdate();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName()).log(Level.SEVERE, "Problema registro del Driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName()).log(Level.SEVERE, "Error SQL.", ex);
        }
        return cantFilas;
    }

    public boolean consultaMasPuerto(String rut_socio) {
        boolean mas_puerto = false;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT MAS_PUERTO FROM SOCIO WHERE RUT_SOCIO = '" + rut_socio + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                if (res.getInt("MAS_PUERTO") == 1) {
                    mas_puerto = true;
                }
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return mas_puerto;
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

    public ArrayList<BuscarTrabajo> listarTrabajos(String rut_socio) {
        ArrayList<BuscarTrabajo> listaTrabajo = new ArrayList();
        BuscarTrabajo trabajo_buscado;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT S.RUT_SOCIO, S.NOMBRE, SI.NOMBRE AS SINDICATO, PU.ID_PUERTO, PU.NOMBRE AS PUERTO FROM SOCIO S, SINDICATO SI, TRABAJA TR, PUERTO PU WHERE S.CODIGO = SI.CODIGO AND TR.RUT_SOCIO = S.RUT_SOCIO AND PU.ID_PUERTO = TR.ID_PUERTO AND S.RUT_SOCIO LIKE '" + rut_socio + "'";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                trabajo_buscado = new BuscarTrabajo();
                trabajo_buscado.setRut_socio(res.getString("RUT_SOCIO"));
                trabajo_buscado.setNombre_socio(res.getString("NOMBRE"));
                trabajo_buscado.setSindicato(res.getString("SINDICATO"));
                trabajo_buscado.setId_puerto(Integer.parseInt(res.getString("ID_PUERTO")));
                trabajo_buscado.setPuerto(res.getString("PUERTO"));
                //Se agrega el sindicato a la lista.
                listaTrabajo.add(trabajo_buscado);
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return listaTrabajo;
    }

    public boolean ingresarAuditoria(int id_ingreso, String operacion, String tabla_alterada, int pk_trabaja_id_puerto, String pk_trabaja_rut) {
        int cantFilas = 0;
        boolean ingreso = false;

        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "INSERT INTO AUDITORIA VALUES(NEXT VALUE FOR ID_AUDITORIA_SEC, " + id_ingreso + ", '" + operacion + "', '" + tabla_alterada + "', NULL, '" + pk_trabaja_id_puerto + "', '" + pk_trabaja_rut + "', GETUTCDATE(), NULL, NULL, NULL)";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            cantFilas = ps.executeUpdate();
            if (cantFilas > 0) {
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

    public TrabajoBuscado buscarTrabajo(int id_puerto, String rut_socio) {
        TrabajoBuscado trabajoBuscado = new TrabajoBuscado();;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT S.RUT_SOCIO, S.NOMBRE, PU.ID_PUERTO, PU.NOMBRE AS PUERTO, TR.TURNOS, TR.ENERO, TR.FEBRERO, TR.MARZO, TR.ABRIL, TR.MAYO, TR.JUNIO, TR.JULIO, TR.AGOSTO, TR.SEPTIEMBRE, TR.OCTUBRE, TR.NOVIEMBRE, TR.DICIEMBRE, TR.SECCION, TR.FUNCION, TR.ACCIDENTE FROM SOCIO S, TRABAJA TR, PUERTO PU WHERE TR.RUT_SOCIO = S.RUT_SOCIO AND PU.ID_PUERTO = TR.ID_PUERTO AND S.RUT_SOCIO LIKE '" + rut_socio + "' AND TR.ID_PUERTO = " + id_puerto + "";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                trabajoBuscado.setRut_socio(res.getString("RUT_SOCIO"));
                trabajoBuscado.setNombre_socio(res.getString("NOMBRE"));
                trabajoBuscado.setId_puerto(Integer.parseInt(res.getString("ID_PUERTO")));
                trabajoBuscado.setPuerto(res.getString("PUERTO"));
                trabajoBuscado.setTurnos(Integer.parseInt(res.getString("TURNOS")));
                int enero = Integer.parseInt(res.getString("ENERO"));
                if (enero == 1) {
                    trabajoBuscado.setEnero("SÍ");
                } else {
                    trabajoBuscado.setEnero("NO");
                }
                int febrero = Integer.parseInt(res.getString("FEBRERO"));
                if (febrero == 1) {
                    trabajoBuscado.setFebrero("SÍ");
                } else {
                    trabajoBuscado.setFebrero("NO");
                }
                int marzo = Integer.parseInt(res.getString("MARZO"));
                if (marzo == 1) {
                    trabajoBuscado.setMarzo("SÍ");
                } else {
                    trabajoBuscado.setMarzo("NO");
                }
                int abril = Integer.parseInt(res.getString("ABRIL"));
                if (abril == 1) {
                    trabajoBuscado.setAbril("SÍ");
                } else {
                    trabajoBuscado.setAbril("NO");
                }
                int mayo = Integer.parseInt(res.getString("MAYO"));
                if (mayo == 1) {
                    trabajoBuscado.setMayo("SÍ");
                } else {
                    trabajoBuscado.setMayo("NO");
                }
                int junio = Integer.parseInt(res.getString("JUNIO"));
                if (junio == 1) {
                    trabajoBuscado.setJunio("SÍ");
                } else {
                    trabajoBuscado.setJunio("NO");
                }
                int julio = Integer.parseInt(res.getString("JULIO"));
                if (julio == 1) {
                    trabajoBuscado.setJulio("SÍ");
                } else {
                    trabajoBuscado.setJulio("NO");
                }
                int agosto = Integer.parseInt(res.getString("AGOSTO"));
                if (agosto == 1) {
                    trabajoBuscado.setAgosto("SÍ");
                } else {
                    trabajoBuscado.setAgosto("NO");
                }
                int septiembre = Integer.parseInt(res.getString("SEPTIEMBRE"));
                if (septiembre == 1) {
                    trabajoBuscado.setSeptiembre("SÍ");
                } else {
                    trabajoBuscado.setSeptiembre("NO");
                }
                int octubre = Integer.parseInt(res.getString("OCTUBRE"));
                if (octubre == 1) {
                    trabajoBuscado.setOctubre("SÍ");
                } else {
                    trabajoBuscado.setOctubre("NO");
                }
                int noviembre = Integer.parseInt(res.getString("NOVIEMBRE"));
                if (noviembre == 1) {
                    trabajoBuscado.setNoviembre("SÍ");
                } else {
                    trabajoBuscado.setNoviembre("NO");
                }
                int diciembre = Integer.parseInt(res.getString("DICIEMBRE"));
                if (diciembre == 1) {
                    trabajoBuscado.setDiciembre("SÍ");
                } else {
                    trabajoBuscado.setDiciembre("NO");
                }
                trabajoBuscado.setSeccion(res.getString("SECCION"));
                trabajoBuscado.setFuncion(res.getString("FUNCION"));
                int accidente = Integer.parseInt(res.getString("ACCIDENTE"));
                if (accidente == 1) {
                    trabajoBuscado.setAccidente("SÍ");
                } else {
                    trabajoBuscado.setAccidente("NO");
                }
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOportuarios.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return trabajoBuscado;
    }
    
    public boolean existenciaPreviaTrabajo(int id_puerto, String rut_socio) {
        boolean existencia = false;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM TRABAJA WHERE RUT_SOCIO LIKE '" + rut_socio + "' AND ID_PUERTO = " + id_puerto + "";
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

    public int modificarTrabajo(Trabajo trabajo) {
        int cantFilas = 0;

        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "UPDATE TRABAJA SET ENERO = '" + trabajo.getEnero() + "', FEBRERO = '" + trabajo.getFebrero() + "', "
                    + "MARZO = '" + trabajo.getMarzo() + "', ABRIL = '" + trabajo.getAbril() + "', MAYO = '" + trabajo.getMayo() + "', "
                    + "JUNIO = '" + trabajo.getJunio() + "', JULIO = '" + trabajo.getJulio() + "', AGOSTO = '" + trabajo.getAgosto() + "', "
                    + "SEPTIEMBRE = '" + trabajo.getSeptiembre() + "', OCTUBRE = '" + trabajo.getOctubre() + "', NOVIEMBRE = '" + trabajo.getNoviembre() + "', "
                    + "DICIEMBRE = '" + trabajo.getDiciembre() + "', TURNOS = " + trabajo.getTurnos() + ", SECCION = '" + trabajo.getSeccion() + "', FUNCION = '" + trabajo.getFuncion() + "', "
                    + "ACCIDENTE = '" + trabajo.getAccidente() + "' WHERE RUT_SOCIO = '" + trabajo.getRut_socio() + "' AND ID_PUERTO = " + trabajo.getId_puerto() + "";
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

    public boolean eliminarTrabajo(String rut_socio, int id_puerto) {
        boolean confirmacion = true;

        boolean confirmacionPertenece = false;

        confirmacionPertenece = consultarNumeroPerteneceRutId_puerto(rut_socio, id_puerto);

        if (confirmacionPertenece) {
            try {
                //Recuperar una conexión.
                Connection con = this.getConexion();
                PreparedStatement eliminarSocio;
                eliminarSocio = con.prepareStatement("DELETE PERTENECE FROM PERTENECE WHERE RUT_SOCIO = '" + rut_socio + "' AND ID_PUERTO = '" + id_puerto + "';");
                eliminarSocio.execute();
                confirmacionPertenece = false;
            } catch (ClassNotFoundException | SQLException error) {
                confirmacionPertenece = true;
            }
        }

        if (!confirmacionPertenece) {
            try {
                //Recuperar una conexión.
                Connection con = this.getConexion();
                PreparedStatement eliminarSocio;
                eliminarSocio = con.prepareStatement("DELETE TRABAJA FROM TRABAJA WHERE RUT_SOCIO = '" + rut_socio + "' AND ID_PUERTO = '" + id_puerto + "';");
                eliminarSocio.execute();
            } catch (ClassNotFoundException | SQLException error) {
                confirmacion = false;
            }
        }
        return confirmacion;
    }

    public boolean consultarNumeroPerteneceRutId_puerto(String rut_socio, int id_puerto) {
        boolean existencia = false;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT * FROM PERTENECE WHERE RUT_SOCIO = '" + rut_socio + "' AND ID_PUERTO = " + id_puerto + "";
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

}
