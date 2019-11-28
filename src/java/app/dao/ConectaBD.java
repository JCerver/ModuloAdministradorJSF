/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author todos
 */
public class ConectaBD {

    private static ConectaBD instancia;
 
    private static String bd = "restaurante";//Nombre de la base de datos
    private String urls = "jdbc:mysql://localhost/" + bd;
    private String usuarioDB = "root";
    //private String usuarioDB = "fredy";
    
    private String passwordDB = "";
    //public static String passwordDB = "Fredy97";
    //private String passwordDB = "";
    private Connection conexion;
    

    private ConectaBD() throws SQLException, ClassNotFoundException {
        abrir();//para que se abra la conexion al inicio
    }

    public static ConectaBD getInstance() throws SQLException, ClassNotFoundException {
        if (instancia == null) {
            instancia = new ConectaBD();
        }
        return instancia;
    }

    public void abrir() throws SQLException, ClassNotFoundException {
        conexion = null;
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost/"+bd
                + "?autoReconnect=true&useSSL=false",usuarioDB,passwordDB);
    }

    public void cerrar() throws SQLException {
        conexion.close();
        //System.out.println("Se a cerrado la conexión satisfactoriamente");
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    
}
