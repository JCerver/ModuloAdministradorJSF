/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import app.dao.ConectaBD;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import modelo.PlatilloDelDia;

public class GestorPlatilloDelDiaBD {

    private ConectaBD conexionSingleton;
    private Connection conexion;
    private ResultSet rs;
    private Statement st;

    public GestorPlatilloDelDiaBD() throws SQLException, ClassNotFoundException {
        conexionSingleton = ConectaBD.getInstance();
    }

    public List<PlatilloDelDia> getPlatillosDelDia() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<PlatilloDelDia> listaPlatillosDelDia = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getPlatillosDelDia();");
            while (rs.next()) {
                PlatilloDelDia platilloDelDia = new PlatilloDelDia();
                platilloDelDia.setIdRegistro(rs.getInt(1));
                platilloDelDia.setIdPlatillo(rs.getInt(2));
                listaPlatillosDelDia.add(platilloDelDia);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaPlatillosDelDia;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public void addPlatilloDelDia(int idPlatillo) {
        /*Almacena un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement("call insertarPlatilloDelDia (?);");
            st.setInt(1, idPlatillo);
            st.execute();
            st.close();
            //conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePlatillosDelDia(int idRegistro_QUE_NO_ES_DEL_PLATILLO) {
        /*Elimina un registro en la base de datos de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call deletePlatillosDelDia(?);");
            st.setInt(1, idRegistro_QUE_NO_ES_DEL_PLATILLO);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
