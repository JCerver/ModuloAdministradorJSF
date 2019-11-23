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
import modelo.SesionServicio;

public class GestorSesionServicioBD {

    private ConectaBD conexionSingleton;
    private Connection conexion;
    private ResultSet rs;
    private Statement st;

    public GestorSesionServicioBD() throws SQLException, ClassNotFoundException {
        conexionSingleton = ConectaBD.getInstance();
    }

    public List<SesionServicio> getSesiones() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();

        List<SesionServicio> listaSesiones = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getSesiones();");
            while (rs.next()) {
                SesionServicio sesionServicio = new SesionServicio();
                sesionServicio.setId(rs.getInt(1));
                sesionServicio.setFk_cliente(rs.getInt(2));
                sesionServicio.setFk_mesero(rs.getInt(3));
                sesionServicio.setNumMesa(rs.getInt(4));
                sesionServicio.setPuntajeMeseroServicio(rs.getDouble(5));
                sesionServicio.setTotalVenta(rs.getDouble(6));
                sesionServicio.setTipoPago(rs.getString(7));
                sesionServicio.setFecha(rs.getString(8));
                sesionServicio.setEstadoSesion(rs.getInt(9));
                sesionServicio.setStatus(rs.getInt(10));
                listaSesiones.add(sesionServicio);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaSesiones;
        } catch (Exception e) {
            e.printStackTrace();

            return null;

        }

    }

    public int getUltimoIdDeSesion() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        int id = 0;

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getUltimoIdDeSesion();");
            while (rs.next()) {

                id = rs.getInt(1);
            }
            rs.close();
            st.close();
            //conexion.close();
            return id;
        } catch (Exception e) {
            e.printStackTrace();

            return 0;
        }

    }

    public int getUltimoIdDeSesionDeUnCliente(int idCliente) {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        int id = 0;

        try {
            PreparedStatement ps = conexion.prepareStatement("call getUltimoIdDeSesionDeUnCliente(?);");
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {

                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
            //conexion.close();
            return id;
        } catch (Exception e) {
            e.printStackTrace();

            return 0;
        }
    }

    public List<SesionServicio> getMesasOcupadasEnSesionesActivas() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();;
        List<SesionServicio> listaSesiones = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getMesasOcupadasEnSesionesActivas();");
            while (rs.next()) {
                SesionServicio sesionServicio = new SesionServicio();
                sesionServicio.setId(rs.getInt(1));
                sesionServicio.setNumMesa(rs.getInt(2));
                listaSesiones.add(sesionServicio);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaSesiones;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public SesionServicio getSesionPorID(int id) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        SesionServicio sesionServicio = new SesionServicio();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getSesionPorId(?);");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                sesionServicio.setId(rs.getInt(1));
                sesionServicio.setFk_cliente(rs.getInt(2));
                sesionServicio.setFk_mesero(rs.getInt(3));
                sesionServicio.setNumMesa(rs.getInt(4));
                sesionServicio.setPuntajeMeseroServicio(rs.getDouble(5));
                sesionServicio.setTotalVenta(rs.getDouble(6));
                sesionServicio.setTipoPago(rs.getString(7));
                sesionServicio.setFecha(rs.getString(8));
                sesionServicio.setEstadoSesion(rs.getInt(9));
                sesionServicio.setStatus(rs.getInt(10));
            }

            rs.close();
            ps.close();
            //conexion.close();
            return sesionServicio;
        } catch (Exception e) {
            e.printStackTrace();

            return sesionServicio;
        }
    }

    public SesionServicio getIdSesionDeUnaMesa(int numMesa) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        SesionServicio sesionServicio = new SesionServicio();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getIdSesionDeUnaMesa(?);");
            ps.setInt(1, numMesa);
            rs = ps.executeQuery();
            while (rs.next()) {

                sesionServicio.setId(rs.getInt(1));
                sesionServicio.setFk_cliente(rs.getInt(2));
                sesionServicio.setFk_mesero(rs.getInt(3));
                sesionServicio.setNumMesa(rs.getInt(4));
                sesionServicio.setPuntajeMeseroServicio(rs.getDouble(5));
                sesionServicio.setTotalVenta(rs.getDouble(6));
                sesionServicio.setTipoPago(rs.getString(7));
                sesionServicio.setFecha(rs.getString(8));
                sesionServicio.setEstadoSesion(rs.getInt(9));
                sesionServicio.setStatus(rs.getInt(10));
            }

            rs.close();
            ps.close();
            //conexion.close();
            return sesionServicio;
        } catch (Exception e) {
            e.printStackTrace();

            return sesionServicio;
        }
    }

    public int getSesionNextId() {
        /*Devuelve el siguiente número de ID a utilizar*/
        conexion = conexionSingleton.getConexion();
        int nextId = 0;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("CALL getSesionNextId();");
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

    public void addSesionServicio(SesionServicio sesionServicio) {
        /*Almacena un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement("call insertarNuevaSesion (?, ?, ?);");
            st.setInt(1, sesionServicio.getFk_cliente());
            st.setInt(2, sesionServicio.getFk_mesero());
            st.setInt(3, sesionServicio.getNumMesa());

            st.execute();
            st.close();
            //conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updatePuntajeMeseroSesion(SesionServicio sesionServicio) {//Agregar y actualizar puntaje
//        Modifica un objeto en la base de datos, 
//         cada atributo se utiliza en la posición que le corresponde 
//         de la instrucción SQL 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call updatePuntajeMeseroSesion(?,?);");
            st.setInt(1, sesionServicio.getId());
            st.setDouble(2, sesionServicio.getPuntajeMeseroServicio());
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void colocarSesionComoInactiva(SesionServicio sesionServicio) {//Agregar y actualizar puntaje
//        Modifica un objeto en la base de datos, 
//         cada atributo se utiliza en la posición que le corresponde 
//         de la instrucción SQL 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call colocarSesionComoInactiva(?);");
            st.setInt(1, sesionServicio.getId());
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void updateTotalVentaYTipoPagoSesion(SesionServicio sesionServicio) {
//        Modifica un objeto en la base de datos, 
//         cada atributo se utiliza en la posición que le corresponde 
//         de la instrucción SQL 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call updateTotalVentaYTipoPagoSesion(?,?,?);");
            st.setInt(1, sesionServicio.getId());
            st.setDouble(2, sesionServicio.getTotalVenta());
            st.setString(3, sesionServicio.getTipoPago());
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void colocarFechaActualPagoSesionServicio(int idSesion) {
//        Modifica un objeto en la base de datos, 
//         cada atributo se utiliza en la posición que le corresponde 
//         de la instrucción SQL 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call colocarFechaActualPagoSesionServicio(?);");
            st.setInt(1, idSesion);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void deleteSesion(int id) {
//        Elimina un registro en la base de datos de acuerdo a su llave primaria 
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call deleteSesion(?);");
            st.setInt(1, id);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<SesionServicio> calcularTotalPorOrdenEnVentaDeUnaSesion(int id) {
        ///Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria 
        conexion = conexionSingleton.getConexion();
        List<SesionServicio> listaSesionesTotalPorOrden = new ArrayList<>();

        try {
            PreparedStatement ps = conexion.prepareStatement("call calcularTotalPorOrdenEnVentaDeUnaSesion(?);");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                SesionServicio sesionServicio = new SesionServicio();
                sesionServicio.setId(rs.getInt(1));
                sesionServicio.setFk_cliente(rs.getInt(2));
                sesionServicio.setFk_mesero(rs.getInt(3));
                sesionServicio.setNumMesa(rs.getInt(4));
                sesionServicio.setPuntajeMeseroServicio(rs.getDouble(5));
                sesionServicio.setTotalVenta(rs.getDouble(6));
                sesionServicio.setTipoPago(rs.getString(7));
                sesionServicio.setEstadoSesion(rs.getInt(8));
                sesionServicio.setIdOrden(rs.getInt(9));
                sesionServicio.setEstadoOrden(rs.getString(10));
                sesionServicio.setTotalPorOrdenCalculado(rs.getDouble(11));
                listaSesionesTotalPorOrden.add(sesionServicio);

            }
            rs.close();
            ps.close();
            //conexion.close();
            return listaSesionesTotalPorOrden;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<SesionServicio> calcularTotalGeneralEnVentaDeUnaSesion(int id) {
        ///Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria 
        conexion = conexionSingleton.getConexion();
        List<SesionServicio> listaSesionesTotalPorOrden = new ArrayList<>();

        try {
            PreparedStatement ps = conexion.prepareStatement("call calcularTotalGeneralEnVentaDeUnaSesion(?);");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                SesionServicio sesionServicio = new SesionServicio();
                sesionServicio.setId(rs.getInt(1));
                sesionServicio.setFk_cliente(rs.getInt(2));
                sesionServicio.setFk_mesero(rs.getInt(3));
                sesionServicio.setNumMesa(rs.getInt(4));
                sesionServicio.setPuntajeMeseroServicio(rs.getDouble(5));
                sesionServicio.setTotalVenta(rs.getDouble(6));
                sesionServicio.setTipoPago(rs.getString(7));
                sesionServicio.setEstadoSesion(rs.getInt(8));
                sesionServicio.setTotalPorSesionCalculado(rs.getDouble(9));
                listaSesionesTotalPorOrden.add(sesionServicio);

            }
            rs.close();
            ps.close();
            //conexion.close();
            return listaSesionesTotalPorOrden;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<SesionServicio> getFiltroReporteVenta(String fechaInicial, String fechaFinal, String idMeseroAsignado, String idCliente) {
        ///Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria 
        conexion = conexionSingleton.getConexion();
        List<SesionServicio> listaSesionesTotalPorOrden = new ArrayList<>();

        try {
            PreparedStatement ps = conexion.prepareStatement("call getFiltroReporteVenta(?,?,?,?);");
            ps.setString(1, fechaInicial);
            ps.setString(2, fechaFinal);
            ps.setString(3, idMeseroAsignado);
            ps.setString(4, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                SesionServicio sesionServicio = new SesionServicio();
                sesionServicio.setId(rs.getInt(1));
                sesionServicio.setFk_cliente(rs.getInt(2));
                sesionServicio.setFk_mesero(rs.getInt(3));
                sesionServicio.setNumMesa(rs.getInt(4));
                sesionServicio.setPuntajeMeseroServicio(rs.getDouble(5));
                sesionServicio.setTotalVenta(rs.getDouble(6));
                sesionServicio.setTipoPago(rs.getString(7));
                sesionServicio.setFecha(rs.getString(8));
                sesionServicio.setEstadoSesion(rs.getInt(9));
                sesionServicio.setStatus(rs.getInt(10));
                listaSesionesTotalPorOrden.add(sesionServicio);

            }
            rs.close();
            ps.close();
            //conexion.close();
            return listaSesionesTotalPorOrden;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<SesionServicio> getFiltroReporteVentaConNombres(String fechaInicial, String fechaFinal, String idMeseroAsignado, String idCliente) {
        ///Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria 
        conexion = conexionSingleton.getConexion();
        List<SesionServicio> listaSesionesTotalPorOrden = new ArrayList<>();

        try {
            PreparedStatement ps = conexion.prepareStatement("call getFiltroReporteVentaConNombres(?,?,?,?);");
            ps.setString(1, fechaInicial);
            ps.setString(2, fechaFinal);
            ps.setString(3, idMeseroAsignado);
            ps.setString(4, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                SesionServicio sesionServicio = new SesionServicio();
                sesionServicio.setId(rs.getInt(1));
                sesionServicio.setFk_cliente(rs.getInt(2));
                sesionServicio.setNombreConcatenadoCliente(rs.getString(3));
                sesionServicio.setFk_mesero(rs.getInt(4));
                sesionServicio.setNombreConcatenadoMeseroAsignado(rs.getString(5));
                sesionServicio.setNumMesa(rs.getInt(6));
                sesionServicio.setPuntajeMeseroServicio(rs.getDouble(7));
                sesionServicio.setTotalVenta(rs.getDouble(8));
                sesionServicio.setTipoPago(rs.getString(9));
                sesionServicio.setFecha(rs.getString(10));
                sesionServicio.setEstadoSesion(rs.getInt(11));
                sesionServicio.setStatus(rs.getInt(12));
                listaSesionesTotalPorOrden.add(sesionServicio);
            }
            rs.close();
            ps.close();
            //conexion.close();
            return listaSesionesTotalPorOrden;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

}
