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
import modelo.CategoriaPlatillo;

public class GestorCategoriaPlatilloBD {

    private ConectaBD conexionSingleton;
    private Connection conexion;
    private ResultSet rs;
    private Statement st;

    public GestorCategoriaPlatilloBD() throws SQLException, ClassNotFoundException {
        conexionSingleton = ConectaBD.getInstance();
    }

    public List<CategoriaPlatillo> getCategoriasPlatillos() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<CategoriaPlatillo> listaCategoriasPlatillos = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getCategoriasPlatillos();");
            while (rs.next()) {
                CategoriaPlatillo categoriaPlatillo = new CategoriaPlatillo();
                categoriaPlatillo.setId(rs.getInt(1));
                categoriaPlatillo.setCategoria(rs.getString(2));
                categoriaPlatillo.setStatus(rs.getInt(3));
                listaCategoriasPlatillos.add(categoriaPlatillo);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaCategoriasPlatillos;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public CategoriaPlatillo getCategoriaPlatilloPorID(int id) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        CategoriaPlatillo categoriaPlatillo = new CategoriaPlatillo();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getCategoriaPlatilloPorId(?);");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            categoriaPlatillo.setId(rs.getInt(1));
            categoriaPlatillo.setCategoria(rs.getString(2));
            categoriaPlatillo.setStatus(rs.getInt(3));
            rs.close();
            ps.close();
            //conexion.close();
            return categoriaPlatillo;
        } catch (Exception e) {
            e.printStackTrace();

            return categoriaPlatillo;
        }
    }

    public int getCategoriaPlatilloNextId() {
        /*Devuelve el siguiente número de ID a utilizar*/
        conexion = conexionSingleton.getConexion();
        int nextId = 0;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("CALL getCategoriaPlatilloNextId();");
            rs.next();
            nextId = rs.getInt(1);
            rs.close();
            st.close();
            //conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nextId;
    }

    public void addCategoriaPlatillo(CategoriaPlatillo categoriaPlatillo) {
        /*Almacena un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement("call insertarCategoriaPlatillo(?);");

            st.setString(1, categoriaPlatillo.getCategoria());

            st.execute();
            st.close();
            //conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateCategoriaPlatillo(CategoriaPlatillo categoriaPlatillo) {
        /*Modifica un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call updateCategoriaPlatillo(?,?);");
            st.setInt(1, categoriaPlatillo.getId());
            st.setString(2, categoriaPlatillo.getCategoria());
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void deleteCategoriaPlatillo(int id) {
        /*Elimina un registro en la base de datos de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call deleteCategoriaPlatillo(?);");
            st.setInt(1, id);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
