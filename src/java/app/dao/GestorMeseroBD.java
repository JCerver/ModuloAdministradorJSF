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
import modelo.Mesero;

public class GestorMeseroBD {

    private ConectaBD conexionSingleton;
    private Connection conexion;
    private ResultSet rs;
    private Statement st;

    public GestorMeseroBD() throws SQLException, ClassNotFoundException {
        conexionSingleton = ConectaBD.getInstance();
    }

    public Mesero getMeseroLogin(String username, String password) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        Mesero mesero = new Mesero();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getMeseroLogin(?,?);");
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            while (rs.next()) {

                mesero.setId(rs.getInt(1));
                mesero.setNombre(rs.getString(2));
                mesero.setApellidos(rs.getString(3));
                mesero.setCorreo(rs.getString(4));
                mesero.setUsuario(rs.getString(5));
                mesero.setPassword(rs.getString(6));
                mesero.setPuntuacionTotal(rs.getDouble(7));
                mesero.setNumPuntuaciones(rs.getInt(8));
                mesero.setStatus(rs.getInt(9));
            }
            rs.close();
            ps.close();
            //conexion.close();
            return mesero;
        } catch (Exception e) {
            e.printStackTrace();

            return mesero;
        }
    }

    public List<Mesero> getMeseros() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Mesero> listaMeseros = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getMeseros();");
            while (rs.next()) {
                Mesero mesero = new Mesero();
                mesero.setId(rs.getInt(1));
                mesero.setNombre(rs.getString(2));
                mesero.setApellidos(rs.getString(3));
                mesero.setCorreo(rs.getString(4));
                mesero.setUsuario(rs.getString(5));
                mesero.setPassword(rs.getString(6));
                mesero.setPuntuacionTotal(rs.getDouble(7));
                mesero.setNumPuntuaciones(rs.getInt(8));
                mesero.setStatus(rs.getInt(9));

                listaMeseros.add(mesero);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaMeseros;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Mesero> getMeserosYCantidadMesasAsignadas() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Mesero> listaMeseros = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getMeserosYCantidadMesasAsignadas();");
            while (rs.next()) {
                Mesero mesero = new Mesero();
                mesero.setId(rs.getInt(1));
                mesero.setCantidadMesasAsignadas(rs.getInt(2));

                listaMeseros.add(mesero);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaMeseros;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Mesero> getMeserosYCantidadMesasAsignadas2() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Mesero> listaMeseros = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getMeserosYCantidadMesasAsignadas2();");
            while (rs.next()) {
                Mesero mesero = new Mesero();
                mesero.setId(rs.getInt(1));
                mesero.setCantidadMesasAsignadas(rs.getInt(2));

                listaMeseros.add(mesero);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaMeseros;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Integer> getMesasMesero(int idMesero) {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Integer> listaMesas = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getMesasMesero(?);");

            prest.setInt(1, idMesero);

            rs = prest.executeQuery();

            while (rs.next()) {
                int numMesa = rs.getInt(1);
                listaMesas.add(numMesa);
            }
            rs.close();
            prest.close();
            //conexion.close();
            return listaMesas;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public Mesero getMesero(int id) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        Mesero mesero = new Mesero();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getMesero(?);");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            mesero.setId(rs.getInt(1));
            mesero.setNombre(rs.getString(2));
            mesero.setApellidos(rs.getString(3));
            mesero.setCorreo(rs.getString(4));
            mesero.setUsuario(rs.getString(5));
            mesero.setPassword(rs.getString(6));
            mesero.setPuntuacionTotal(rs.getDouble(7));
            mesero.setNumPuntuaciones(rs.getInt(8));
            mesero.setStatus(rs.getInt(9));

            rs.close();
            ps.close();
            //conexion.close();
            return mesero;
        } catch (Exception e) {
            e.printStackTrace();

            return mesero;
        }
    }

    public int getMeseroNextId() {
        /*Devuelve el siguiente número de ID a utilizar*/
        conexion = conexionSingleton.getConexion();
        int nextId = 0;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("CALL getMeseroNextId();");
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

    public void addMesero(Mesero mesero) {
        /*Almacena un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement("call insertarMesero (?, ?, ?, ?, ?);");
            st.setString(1, mesero.getNombre());
            st.setString(2, mesero.getApellidos());
            st.setString(3, mesero.getCorreo());
            st.setString(4, mesero.getUsuario());
            st.setString(5, mesero.getPassword());

            st.execute();
            st.close();
            //conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateMesero(Mesero mesero) {
        /*Modifica un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call updateMesero(?,?,?,?,?,?);");
            st.setInt(1, mesero.getId());
            st.setString(2, mesero.getNombre());
            st.setString(3, mesero.getApellidos());
            st.setString(4, mesero.getCorreo());
            st.setString(5, mesero.getUsuario());
            st.setString(6, mesero.getPassword());
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void deleteMesero(int id) {
        /*Elimina un registro en la base de datos de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call deleteMesero(?);");
            st.setInt(1, id);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<Mesero> getFiltroMesero(String filtro) {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Mesero> listaMeseros = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getFiltroMesero(?);");

            prest.setString(1, filtro);

            rs = prest.executeQuery();

            while (rs.next()) {
                Mesero mesero = new Mesero();
                mesero.setId(rs.getInt(1));
                mesero.setNombre(rs.getString(2));
                mesero.setApellidos(rs.getString(3));
                mesero.setCorreo(rs.getString(4));
                mesero.setUsuario(rs.getString(5));
                mesero.setPassword(rs.getString(6));
                mesero.setPuntuacionTotal(rs.getDouble(7));
                mesero.setNumPuntuaciones(rs.getInt(8));
                mesero.setStatus(rs.getInt(9));

                listaMeseros.add(mesero);

            }
            rs.close();
            prest.close();
            //conexion.close();
            return listaMeseros;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public List<Object> getPuntajeDeMesero(int idMesero) {/*Arreglo, en posicion [0]=puntajeTotal, [1]=numPuntuaciones*/
 /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Object> listaPuntaje = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getPuntajeMesero(?);");

            prest.setInt(1, idMesero);

            rs = prest.executeQuery();

            while (rs.next()) {
                double puntuacion = rs.getDouble(1);
                int numPuntuaciones = rs.getInt(2);
                listaPuntaje.add(puntuacion);
                listaPuntaje.add(numPuntuaciones);

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

    public void updatePuntaje(Mesero mesero) {
        /*Modifica un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call updatePuntajeMesero(?,?,?);");
            st.setInt(1, mesero.getId());
            st.setDouble(2, mesero.getPuntuacionTotal());
            st.setInt(3, mesero.getNumPuntuaciones());
            st.execute();
            st.close();
            //conexion.close();
            System.out.println("ENTRO AL TRY");

        } catch (SQLException e) {
            System.out.println("Valio versh");
            e.printStackTrace();

        }
    }

}
