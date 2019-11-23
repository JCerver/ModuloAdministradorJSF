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
import modelo.Administrador;

public class GestorAdministradorBD {

    private ConectaBD conexionSingleton;
    private Connection conexion;
    private ResultSet rs;
    private Statement st;

    public GestorAdministradorBD() throws SQLException, ClassNotFoundException  {
        conexionSingleton = ConectaBD.getInstance();
    }

    public Administrador getAdministradorLogin(String username, String password) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion =conexionSingleton.getConexion();
        Administrador administrador = new Administrador();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getAdministradorLogin(?,?);");
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                administrador.setId(rs.getInt(1));
                administrador.setNombre(rs.getString(2));
                administrador.setCorreo(rs.getString(3));
                administrador.setUsuario(rs.getString(4));
                administrador.setPassword(rs.getString(5));
                administrador.setStatus(rs.getInt(6));
            }
            rs.close();
            ps.close();
            //conexion.close();
            return administrador;
        } catch (Exception e) {
            e.printStackTrace();

            return administrador;
        }
    }

    public List<Administrador> getAdministradores() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Administrador> listaAdministrador = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getAdministradores();");
            while (rs.next()) {
                Administrador administrador = new Administrador();
                administrador.setId(rs.getInt(1));
                administrador.setNombre(rs.getString(2));
                administrador.setCorreo(rs.getString(3));
                administrador.setUsuario(rs.getString(4));
                administrador.setPassword(rs.getString(5));
                administrador.setStatus(rs.getInt(6));

                listaAdministrador.add(administrador);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaAdministrador;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public Administrador getAdministrador(int id) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        Administrador administrador = new Administrador();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getAdministrador(?);");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            administrador.setId(rs.getInt(1));
            administrador.setNombre(rs.getString(2));
            administrador.setCorreo(rs.getString(3));
            administrador.setUsuario(rs.getString(4));
            administrador.setPassword(rs.getString(5));
            administrador.setStatus(rs.getInt(6));

            rs.close();
            ps.close();
            //conexion.close();
            return administrador;
        } catch (Exception e) {
            e.printStackTrace();

            return administrador;
        }
    }

    public int getAdministradorNextId() {
        /*Devuelve el siguiente número de ID a utilizar*/
        conexion=conexionSingleton.getConexion();
        int nextId = 0;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("CALL getAdministradorNextId();");
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

    public void addAdministrador(Administrador administrador) {
        /*Almacena un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion= conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement("call insertarAdministrador(?, ?, ?, ?);");
            st.setString(1, administrador.getNombre());
            st.setString(2, administrador.getCorreo());
            st.setString(3, administrador.getUsuario());
            st.setString(4, administrador.getPassword());

            st.execute();
            st.close();
            //conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateAdministrador(Administrador administrador) {
        /*Modifica un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion= conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call updateAdministrador(?,?,?,?,?);");
            st.setInt(1, administrador.getId());
            st.setString(2, administrador.getNombre());
            st.setString(3, administrador.getCorreo());
            st.setString(4, administrador.getUsuario());
            st.setString(5, administrador.getPassword());
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void deleteAdministrador(int id) {
        /*Elimina un registro en la base de datos de acuerdo a su llave primaria */
        conexion= conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call deleteAdministrador(?);");
            st.setInt(1, id);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<Administrador> getFiltroAdministrador(String filtro) {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion= conexionSingleton.getConexion();
        List<Administrador> listaAdministrador = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getFiltroAdministrador(?);");

            prest.setString(1, filtro);

            rs = prest.executeQuery();

            while (rs.next()) {
                Administrador administrador = new Administrador();
                administrador.setId(rs.getInt(1));
                administrador.setNombre(rs.getString(2));
                administrador.setCorreo(rs.getString(3));
                administrador.setUsuario(rs.getString(4));
                administrador.setPassword(rs.getString(5));
                administrador.setStatus(rs.getInt(6));

                listaAdministrador.add(administrador);

            }
            rs.close();
            prest.close();
            //conexion.close();
            return listaAdministrador;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
