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
import modelo.Platillo;

public class GestorPlatilloBD {

    private ConectaBD conexionSingleton;
    private Connection conexion;
    private ResultSet rs;
    private Statement st;

    public GestorPlatilloBD() throws SQLException, ClassNotFoundException {
        conexionSingleton = ConectaBD.getInstance();
    }

    public List<Platillo> getPlatillos() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Platillo> listaPlatillo = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getPlatillos();");
            while (rs.next()) {
                Platillo platillo = new Platillo();
                platillo.setId(rs.getInt(1));
                platillo.setImagen(rs.getBinaryStream(2));
                platillo.setNombre(rs.getString(3));
                platillo.setDescripcion(rs.getString(4));
                platillo.setPrecio(rs.getDouble(5));
                platillo.setCategoria(rs.getString(6));
                platillo.setPuntuacionTotal(rs.getDouble(7));
                platillo.setNumeroPuntuaciones(rs.getInt(8));
                platillo.setStatus(rs.getInt(9));
                listaPlatillo.add(platillo);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaPlatillo;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Platillo> getListaPlatillosPuntaje(String puntuacionAfiltrar, String categoria) {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Platillo> listaPlatillo = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getListaPlatillosPuntaje(?,?);");

            prest.setString(1, puntuacionAfiltrar);
            prest.setString(2, categoria);

            rs = prest.executeQuery();

            while (rs.next()) {
                Platillo platillo = new Platillo();
                platillo.setId(rs.getInt(1));
                platillo.setImagen(rs.getBinaryStream(2));
                platillo.setNombre(rs.getString(3));
                platillo.setDescripcion(rs.getString(4));
                platillo.setPrecio(rs.getDouble(5));
                platillo.setCategoria(rs.getString(6));
                platillo.setPuntuacionTotal(rs.getDouble(7));
                platillo.setNumeroPuntuaciones(rs.getInt(8));
                platillo.setStatus(rs.getInt(9));
                listaPlatillo.add(platillo);
            }
            rs.close();
            prest.close();
            //conexion.close();
            return listaPlatillo;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public void addPlatillo(Platillo platillo) {
        /*Almacena un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement("call insertarPlatillo (?, ?, ?, ?, ?);");
            st.setBlob(1, platillo.getImagen());
            st.setString(2, platillo.getNombre());
            st.setString(3, platillo.getDescripcion());
            st.setDouble(4, platillo.getPrecio());
            st.setString(5, platillo.getCategoria());

            st.execute();
            st.close();
            //conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Platillo getPlatillo(int id) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        Platillo platillo = new Platillo();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getPlatillo(?);");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            platillo.setId(rs.getInt(1));
            platillo.setImagen(rs.getBinaryStream(2));
            platillo.setNombre(rs.getString(3));
            platillo.setDescripcion(rs.getString(4));
            platillo.setPrecio(rs.getDouble(5));
            platillo.setCategoria(rs.getString(6));
            platillo.setPuntuacionTotal(rs.getDouble(7));
            platillo.setNumeroPuntuaciones(rs.getInt(8));
            platillo.setStatus(rs.getInt(9));
            rs.close();
            ps.close();
            //conexion.close();
            return platillo;
        } catch (Exception e) {
            e.printStackTrace();

            return platillo;
        }
    }

    public void updatePlatillo(Platillo platillo) {
        /*Modifica un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call updatePlatillo(?,?,?,?,?,?);");
            st.setInt(1, platillo.getId());
            st.setBlob(2, platillo.getImagen());
            st.setString(3, platillo.getNombre());
            st.setString(4, platillo.getDescripcion());
            st.setDouble(5, platillo.getPrecio());
            st.setString(6, platillo.getCategoria());
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void updatePlatilloNoImage(Platillo platillo) {
        /*Modifica un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call updatePlatilloNoImage(?,?,?,?,?);");
            st.setInt(1, platillo.getId());
            st.setString(2, platillo.getNombre());
            st.setString(3, platillo.getDescripcion());
            st.setDouble(4, platillo.getPrecio());
            st.setString(5, platillo.getCategoria());
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void DeletePlatillo(int id) {
        /*Elimina un registro en la base de datos de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call deletePlatillo(?);");
            st.setInt(1, id);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public int getPlatilloNextId() {
        /*Devuelve el siguiente número de ID a utilizar*/
        conexion = conexionSingleton.getConexion();
        int nextId = 0;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("CALL getPlatilloNextId();");
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

    public List<Platillo> getPlatillosPorNombre(String nombre) {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Platillo> listaPlatillo = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getPlatillosPorNombre(?);");

            prest.setString(1, nombre);

            rs = prest.executeQuery();
            while (rs.next()) {
                Platillo platillo = new Platillo();
                platillo.setId(rs.getInt(1));
                platillo.setImagen(rs.getBinaryStream(2));
                platillo.setNombre(rs.getString(3));
                platillo.setDescripcion(rs.getString(4));
                platillo.setPrecio(rs.getDouble(5));
                platillo.setCategoria(rs.getString(6));
                platillo.setPuntuacionTotal(rs.getDouble(7));
                platillo.setNumeroPuntuaciones(rs.getInt(8));
                platillo.setStatus(rs.getInt(9));
                listaPlatillo.add(platillo);

            }
            rs.close();
            prest.close();
            //conexion.close();
            return listaPlatillo;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Platillo> getPlatillosPorCategoria(String categoria) {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Platillo> listaPlatillo = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getPlatillosPorCategoria(?);");

            prest.setString(1, categoria);

            rs = prest.executeQuery();

            while (rs.next()) {
                Platillo platillo = new Platillo();
                platillo.setId(rs.getInt(1));
                platillo.setImagen(rs.getBinaryStream(2));
                platillo.setNombre(rs.getString(3));
                platillo.setDescripcion(rs.getString(4));
                platillo.setPrecio(rs.getDouble(5));
                platillo.setCategoria(rs.getString(6));
                platillo.setPuntuacionTotal(rs.getDouble(7));
                platillo.setNumeroPuntuaciones(rs.getInt(8));
                platillo.setStatus(rs.getInt(9));
                listaPlatillo.add(platillo);

            }
            rs.close();
            prest.close();
            //conexion.close();
            return listaPlatillo;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Object> getPuntajeDePlatillo(int idPlatillo) {/*Arreglo, en posicion [0]=puntajeTotal, [1]=numPuntuaciones*/
 /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Object> listaPuntaje = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getPuntajePlatillo(?);");

            prest.setInt(1, idPlatillo);

            rs = prest.executeQuery();

            while (rs.next()) {
                double puntuacion = rs.getDouble(1);
                int numPuntuacion = rs.getInt(2);
                listaPuntaje.add(puntuacion);
                listaPuntaje.add(numPuntuacion);

            }
            rs.close();
            prest.close();
            //conexion.close();
            return listaPuntaje;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public void updatePuntaje(Platillo platillo) {
        /*Modifica un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call updatePuntajePlatillo(?,?,?);");
            st.setInt(1, platillo.getId());
            st.setDouble(2, platillo.getPuntuacionTotal());
            st.setInt(3, platillo.getNumeroPuntuaciones());
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void listarImg(int id, HttpServletResponse response) {
        conexion = conexionSingleton.getConexion();
        String sql = "call getPlatillo(" + id + ");";
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("image/*");
        try {
            outputStream = response.getOutputStream();
            PreparedStatement st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                inputStream = rs.getBinaryStream(2);
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }

            rs.close();
            st.close();
            //conexion.close();
        } catch (Exception e) {
        }
    }

    public List<Platillo> getPlatillosDeUnaOrden(int idOrden) {
        ///Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria 
        conexion = conexionSingleton.getConexion();
        List<Platillo> listaPlatillosDeUnaOrden = new ArrayList<>();

        try {
            PreparedStatement ps = conexion.prepareStatement("call getPlatillosDeUnaOrden(?);");
            ps.setInt(1, idOrden);
            rs = ps.executeQuery();

            while (rs.next()) {
                Platillo platillo = new Platillo();
                platillo.setIdDetOrdenDePlatillo(rs.getInt(1));
                platillo.setCantidadPlatillos(rs.getInt(2));
                platillo.setId(rs.getInt(3));
                platillo.setImagen(rs.getBinaryStream(4));
                platillo.setNombre(rs.getString(5));
                platillo.setDescripcion(rs.getString(6));
                platillo.setPrecio(rs.getDouble(7));
                platillo.setCategoria(rs.getString(8));
                platillo.setPuntuacionTotal(rs.getDouble(9));
                platillo.setNumeroPuntuaciones(rs.getInt(10));
                platillo.setStatus(rs.getInt(11));
                listaPlatillosDeUnaOrden.add(platillo);

            }
            rs.close();
            ps.close();
            //conexion.close();
            return listaPlatillosDeUnaOrden;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Platillo> getPlatillosDeUnaSesion(int isSesion) {
        ///Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria 
        conexion = conexionSingleton.getConexion();
        List<Platillo> listaPlatillosDeUnaOrden = new ArrayList<>();

        try {
            PreparedStatement ps = conexion.prepareStatement("call getPlatillosDeUnaSesion(?);");
            ps.setInt(1, isSesion);
            rs = ps.executeQuery();

            while (rs.next()) {
                Platillo platillo = new Platillo();
                platillo.setId(rs.getInt(1));
                platillo.setImagen(rs.getBinaryStream(2));
                platillo.setNombre(rs.getString(3));
                platillo.setDescripcion(rs.getString(4));
                platillo.setPrecio(rs.getDouble(5));
                platillo.setCategoria(rs.getString(6));
                platillo.setPuntuacionTotal(rs.getDouble(7));
                platillo.setNumeroPuntuaciones(rs.getInt(8));
                platillo.setStatus(rs.getInt(9));
                listaPlatillosDeUnaOrden.add(platillo);

            }
            rs.close();
            ps.close();
            //conexion.close();
            return listaPlatillosDeUnaOrden;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Platillo> getTopPlatillosMasConsumidosEnGeneral() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Platillo> listaPlatillo = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getTopPlatillosMasConsumidosEnGeneral();");
            while (rs.next()) {
                Platillo platillo = new Platillo();
                platillo.setId(rs.getInt(1));
                platillo.setCantidadDeVecesComsumido(rs.getInt(2));
                listaPlatillo.add(platillo);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaPlatillo;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Platillo> getTopPlatillosMejorPuntuadosEnGeneral() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Platillo> listaPlatillo = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getTopPlatillosMejorPuntuadosEnGeneral();");
            while (rs.next()) {
                Platillo platillo = new Platillo();
                platillo.setId(rs.getInt(1));
                platillo.setImagen(rs.getBinaryStream(2));
                platillo.setNombre(rs.getString(3));
                platillo.setDescripcion(rs.getString(4));
                platillo.setPrecio(rs.getDouble(5));
                platillo.setCategoria(rs.getString(6));
                platillo.setPuntuacionTotal(rs.getDouble(7));
                platillo.setNumeroPuntuaciones(rs.getInt(8));
                platillo.setStatus(rs.getInt(9));
                listaPlatillo.add(platillo);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaPlatillo;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Platillo> getTopPlatillosMasConsumidosPorUsuario(int idUsuario) {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Platillo> listaPlatillo = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getTopPlatillosMasConsumidosPorUsuario(?);");

            prest.setInt(1, idUsuario);

            rs = prest.executeQuery();

            while (rs.next()) {
                Platillo platillo = new Platillo();
                platillo.setId(rs.getInt(1));
                platillo.setCantidadDeVecesComsumido(rs.getInt(2));
                listaPlatillo.add(platillo);

            }
            rs.close();
            prest.close();
            //conexion.close();
            return listaPlatillo;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Platillo> getFiltroReportePlatillosMasConsumidosEnGeneralEnRangoFechas(String fechaInicial, String fechaFinal, String categoria) {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Platillo> listaPlatillo = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getFiltroReportePlatillosMasConsumidosEnGeneralEnRangoFechas(?,?,?);");

            prest.setString(1, fechaInicial);
            prest.setString(2, fechaFinal);
            prest.setString(3, categoria);

            rs = prest.executeQuery();

            while (rs.next()) {
                Platillo platillo = new Platillo();
                platillo.setFechaCantidadDeVecesComsumido(rs.getString(1));
                platillo.setId(rs.getInt(2));
                platillo.setCantidadDeVecesComsumido(rs.getInt(3));
                platillo.setImagen(rs.getBinaryStream(4));
                platillo.setNombre(rs.getString(5));
                platillo.setDescripcion(rs.getString(6));
                platillo.setPrecio(rs.getDouble(7));
                platillo.setCategoria(rs.getString(8));
                platillo.setPuntuacionTotal(rs.getDouble(9));
                platillo.setNumeroPuntuaciones(rs.getInt(10));
                platillo.setStatus(rs.getInt(11));
                listaPlatillo.add(platillo);

            }
            rs.close();
            prest.close();
            //conexion.close();
            return listaPlatillo;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Platillo> getFiltroReporteElPlatillosMasConsumidosEnGeneralEnRangoFechas(String fechaInicial, String fechaFinal, String categoria) {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Platillo> listaPlatillo = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getFiltroReporteElPlatillosMasConsumidosEnGeneralEnRangoFechas(?,?,?);");

            prest.setString(1, fechaInicial);
            prest.setString(2, fechaFinal);
            prest.setString(3, categoria);

            rs = prest.executeQuery();

            while (rs.next()) {
                Platillo platillo = new Platillo();
                platillo.setId(rs.getInt(1));
                platillo.setCantidadDeVecesComsumido(rs.getInt(2));
                platillo.setImagen(rs.getBinaryStream(3));
                platillo.setNombre(rs.getString(4));
                platillo.setDescripcion(rs.getString(5));
                platillo.setPrecio(rs.getDouble(6));
                platillo.setCategoria(rs.getString(7));
                platillo.setPuntuacionTotal(rs.getDouble(8));
                platillo.setNumeroPuntuaciones(rs.getInt(9));
                platillo.setStatus(rs.getInt(10));
                listaPlatillo.add(platillo);

            }
            rs.close();
            prest.close();
            //conexion.close();
            return listaPlatillo;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
