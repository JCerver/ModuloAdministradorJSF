/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Todos
 */
public class ConexionSingleton {

    private static ConexionSingleton instancia;

    private static String bd = "restaurante";//Nombre de la base de datos
    private String urls = "jdbc:mysql://localhost/" + bd;
    private String usuarioDB = "root";
    private String passwordDB = "Mike123*.";
    //public static String passw = "Fredy97@";
    //private String passwordDB = "";
    private Connection conexion;

    private ConexionSingleton() throws SQLException, ClassNotFoundException {
        abrir();//para que se abra la conexion al inicio
    }

    public static ConexionSingleton getInstance() throws SQLException, ClassNotFoundException {
        if (instancia == null) {
            instancia = new ConexionSingleton();
        }
        return instancia;
    }

    public void abrir() throws SQLException, ClassNotFoundException {
        conexion = null;
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(urls, usuarioDB, passwordDB);
    }

    public void cerrar() throws SQLException {
        conexion.close();
        //System.out.println("Se a cerrado la conexión satisfactoriamente");
    }

}
