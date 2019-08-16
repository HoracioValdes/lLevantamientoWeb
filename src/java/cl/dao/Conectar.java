/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Horacio-Valdés
 */
public class Conectar {
    private String BaseDeDatos = "jdbc:sqlserver://204.93.193.161:1433;databaseName=fundacio_PROYECTO_PORTUARIOS;user=fundacio_USER_PROYECTO_PORTUARIO;password=AM90A51L;";

    public Conectar() {
    }
    
    /** Permite obtener una conexión con la BD SQL Server.
     * @return Connection
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException  */
    public Connection getConexion() 
            throws ClassNotFoundException, SQLException{
        //Registro del Driver.
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //Retornar al conexión
        return DriverManager.getConnection(BaseDeDatos);
    }
    
    public void Desconectar(Connection cn){
        try{
            cn.close();
        }catch(Exception error){ 
        }
    }
}







