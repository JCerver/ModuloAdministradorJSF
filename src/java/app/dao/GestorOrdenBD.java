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
import modelo.Orden;

public class GestorOrdenBD {

    private ConectaBD conexionSingleton;
    private Connection conexion;
    private ResultSet rs;
    private Statement st;

    public GestorOrdenBD() throws SQLException, ClassNotFoundException {
        conexionSingleton = ConectaBD.getInstance();
    }

    public List<Orden> getOrdenes() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Orden> listaOrdenes = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getOrdenes();");
            while (rs.next()) {
                Orden orden = new Orden();
                orden.setId(rs.getInt(1));
                orden.setFk_sesionServicio(rs.getInt(2));
                orden.setEstadoOrden(rs.getString(3));
                orden.setStatus(rs.getInt(4));

                listaOrdenes.add(orden);

            }
            rs.close();
            st.close();
            //conexion.close();

            return listaOrdenes;
        } catch (Exception e) {
            //e.printStackTrace();

            return null;
        }
    }

    public Orden getOrdenPorID(int id) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        Orden orden = new Orden();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getOrdenPorId(?);");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();

            orden.setId(rs.getInt(1));
            orden.setFk_sesionServicio(rs.getInt(2));
            orden.setEstadoOrden(rs.getString(3));
            orden.setStatus(rs.getInt(4));

            rs.close();
            ps.close();
            //conexion.close();
            return orden;
        } catch (Exception e) {
            //e.printStackTrace();

            return orden;
        }
    }

    public int getIdUltimaOrdenPorIdSesion(int idSesion) {
        conexion = conexionSingleton.getConexion();
        int idDeUltimaOrden = 0;
        try {
            PreparedStatement ps = conexion.prepareStatement("call getIdUltimaOrdenPorIdSesion(?);");
            ps.setInt(1, idSesion);
            rs = ps.executeQuery();
            while (rs.next()) {
                idDeUltimaOrden = rs.getInt(1);
            }

            rs.close();
            ps.close();
            //conexion.close();
            return idDeUltimaOrden;
        } catch (Exception e) {
            //e.printStackTrace();

            return 0;
        }
    }

    public int getOrdenNextId() {
        /*Devuelve el siguiente número de ID a utilizar*/
        conexion = conexionSingleton.getConexion();
        int nextId = 0;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("CALL getOrdenNextId();");
            rs.next();
            nextId = rs.getInt(1);
            rs.close();
            st.close();
            //conexion.close();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
        return nextId;
    }

    public void addNuevaOrden(Orden orden) {
        /*Almacena un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement("call insertarNuevaOrden(?);");

            st.setInt(1, orden.getFk_sesionServicio());

            st.execute();
            st.close();
            //conexion.close();

        } catch (Exception e) {
            //e.printStackTrace();
        }

    }

    public void indicarSolicitarOrden(int idOrdenASolicitar) {//Agregar y actualizar puntaje
//        Modifica un objeto en la base de datos, 
//         cada atributo se utiliza en la posición que le corresponde 
//         de la instrucción SQL 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call indicarSolicitarOrden(?);");
            st.setInt(1, idOrdenASolicitar);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            //e.printStackTrace();

        }
    }

    public void indicarOrdenEnPreparacion(int idOrdenASolicitar) {//Agregar y actualizar puntaje
//        Modifica un objeto en la base de datos, 
//         cada atributo se utiliza en la posición que le corresponde 
//         de la instrucción SQL 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call indicarOrdenEnPreparacion(?);");
            st.setInt(1, idOrdenASolicitar);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            //e.printStackTrace();

        }
    }

    public void indicarOrdenPreparada(int idOrdenPreparada) {//Agregar y actualizar puntaje
//        Modifica un objeto en la base de datos, 
//         cada atributo se utiliza en la posición que le corresponde 
//         de la instrucción SQL 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call indicarOrdenPreparada(?);");
            st.setInt(1, idOrdenPreparada);
            st.execute();
            st.close();
           // conexion.close();

        } catch (SQLException e) {
            //e.printStackTrace();

        }
    }

    public void indicarOrdenEntregada(int idOrdenEntregada) {//Agregar y actualizar puntaje
//        Modifica un objeto en la base de datos, 
//         cada atributo se utiliza en la posición que le corresponde 
//         de la instrucción SQL 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call indicarOrdenEntregada(?);");
            st.setInt(1, idOrdenEntregada);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            //e.printStackTrace();

        }
    }

    public void deleteOrden(int id) {
//        Elimina un registro en la base de datos de acuerdo a su llave primaria 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call deleteOrden(?);");
            st.setInt(1, id);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            //e.printStackTrace();

        }
    }

    public List<Orden> getOrdenesPorIdSesion(int idSesion) {
        ///Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria 
        conexion = conexionSingleton.getConexion();
        List<Orden> listaOrdenesDeUnaSesion = new ArrayList<>();

        try {
            PreparedStatement ps = conexion.prepareStatement("call getOrdenesPorIdSesion(?);");
            ps.setInt(1, idSesion);
            rs = ps.executeQuery();

            while (rs.next()) {
                Orden orden = new Orden();
                orden.setId(rs.getInt(1));
                orden.setFk_sesionServicio(rs.getInt(2));
                orden.setEstadoOrden(rs.getString(3));
                orden.setStatus(rs.getInt(4));
                listaOrdenesDeUnaSesion.add(orden);

            }
            rs.close();
            ps.close();
            //conexion.close();
            //st.close();
            return listaOrdenesDeUnaSesion;
        } catch (Exception e) {
            //e.printStackTrace();

            return null;
        }
    }

    public List<Orden> getOrdenesSolicitadasEnCocina() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Orden> listaOrdenes = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getOrdenesSolicitadasEnCocina();");
            while (rs.next()) {
                Orden orden = new Orden();
                orden.setId(rs.getInt(1));
                orden.setFk_sesionServicio(rs.getInt(2));
                orden.setEstadoOrden(rs.getString(3));
                orden.setStatus(rs.getInt(4));

                listaOrdenes.add(orden);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaOrdenes;
        } catch (Exception e) {
            //e.printStackTrace();

            return null;
        }
    }

    public List<Orden> getTopOrdenesSolicitadasEnCocina() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Orden> listaOrdenes = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getTopOrdenesSolicitadasEnCocina();");
            while (rs.next()) {
                Orden orden = new Orden();
                orden.setId(rs.getInt(1));
                orden.setFk_sesionServicio(rs.getInt(2));
                orden.setEstadoOrden(rs.getString(3));
                orden.setStatus(rs.getInt(4));

                listaOrdenes.add(orden);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaOrdenes;
        } catch (Exception e) {
            //e.printStackTrace();

            return null;
        }
    }

}
