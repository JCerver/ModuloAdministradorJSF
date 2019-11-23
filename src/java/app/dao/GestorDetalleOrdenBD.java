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
import modelo.DetalleOrden;

public class GestorDetalleOrdenBD {

    private ConectaBD conexionSingleton;
    private Connection conexion;
    private ResultSet rs;
    private Statement st;

    public GestorDetalleOrdenBD() throws SQLException, ClassNotFoundException {
        conexionSingleton = ConectaBD.getInstance();
    }

    public List<DetalleOrden> getDetallesOrdenes() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<DetalleOrden> listaDetallesOrdenes = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getDetallesOrdenes();");
            while (rs.next()) {
                DetalleOrden detalleOrden = new DetalleOrden();
                detalleOrden.setId(rs.getInt(1));
                detalleOrden.setFk_orden(rs.getInt(2));
                detalleOrden.setFk_platillo(rs.getInt(3));
                detalleOrden.setPuntajePlatillo(rs.getDouble(4));
                detalleOrden.setStatus(rs.getInt(5));

                listaDetallesOrdenes.add(detalleOrden);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaDetallesOrdenes;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<DetalleOrden> getDetallesDeUnaOrden(int idOrden) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        List<DetalleOrden> listaDetallesDeUnaOrden = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getDetallesDeUnaOrden(?);");
            ps.setInt(1, idOrden);
            rs = ps.executeQuery();

            while (rs.next()) {
                DetalleOrden detalleOrden = new DetalleOrden();
                detalleOrden.setId(rs.getInt(1));
                detalleOrden.setFk_orden(rs.getInt(2));
                detalleOrden.setFk_platillo(rs.getInt(3));
                detalleOrden.setCantidad(rs.getInt(4));
                detalleOrden.setPuntajePlatillo(rs.getDouble(5));
                detalleOrden.setStatus(rs.getInt(6));
                listaDetallesDeUnaOrden.add(detalleOrden);

            }
            rs.close();
            ps.close();
            //conexion.close();
            return listaDetallesDeUnaOrden;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

    public List<DetalleOrden> getDetallesDeUnaSesion(int idSesion) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        List<DetalleOrden> listaDetallesDeUnaOrden = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getDetallesDeUnaSesion(?);");
            ps.setInt(1, idSesion);
            rs = ps.executeQuery();

            while (rs.next()) {
                DetalleOrden detalleOrden = new DetalleOrden();
                detalleOrden.setId(rs.getInt(1));
                detalleOrden.setFk_orden(rs.getInt(2));
                detalleOrden.setFk_platillo(rs.getInt(3));
                detalleOrden.setCantidad(rs.getInt(4));
                detalleOrden.setPuntajePlatillo(rs.getDouble(5));
                detalleOrden.setStatus(rs.getInt(6));
                listaDetallesDeUnaOrden.add(detalleOrden);

            }
            rs.close();
            ps.close();
            //conexion.close();
            return listaDetallesDeUnaOrden;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

    public int getDetOrdenNextId() {
        /*Devuelve el siguiente número de ID a utilizar*/
        conexion = conexionSingleton.getConexion();
        int nextId = 0;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("CALL getDetOrdenNextId();");
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

    public void addDetalleOrden(DetalleOrden detalleOrden) {
        /*Almacena un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();

        try {
            PreparedStatement st = conexion.prepareStatement("call insertarNuevoDetOrden (?, ? , ?);");
            st.setInt(1, detalleOrden.getFk_orden());
            st.setInt(2, detalleOrden.getFk_platillo());
            st.setInt(3, detalleOrden.getCantidad());

            st.execute();
            st.close();
            //conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updatePuntajePlatilloEnDetOrden(DetalleOrden detalleOrden) {//Agregar y actualizar puntaje
//        Modifica un objeto en la base de datos, 
//         cada atributo se utiliza en la posición que le corresponde 
//         de la instrucción SQL 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call updatePuntajePlatilloEnDetOrden(?,?);");
            st.setInt(1, detalleOrden.getId());
            st.setDouble(2, detalleOrden.getPuntajePlatillo());
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void deleteDetOrden(int id) {
//        Elimina un registro en la base de datos de acuerdo a su llave primaria 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call deleteDetOrden(?);");
            st.setInt(1, id);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
