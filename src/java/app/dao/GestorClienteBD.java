package app.dao;

import app.dao.ConectaBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import modelo.Cliente;

public class GestorClienteBD {

    private ConectaBD conexionSingleton;
    private Connection conexion;
    private ResultSet rs;
    private Statement st;

    public GestorClienteBD() throws SQLException, ClassNotFoundException {
        conexionSingleton = ConectaBD.getInstance();
    }

    public List<Cliente> getClientes() {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Cliente> listaCliente = new ArrayList<>();

        try {
            st = conexion.createStatement();
            rs = st.executeQuery("call getClientes();");
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setClientId(rs.getInt(1));
                cliente.setName(rs.getString(2));
                cliente.setLastname(rs.getString(3));
                cliente.setEmail(rs.getString(4));
                cliente.setUsername(rs.getString(5));
                cliente.setPassword(rs.getString(6));
                cliente.setType(rs.getString(7));
                cliente.setStatus(rs.getInt(8));
                listaCliente.add(cliente);

            }
            rs.close();
            st.close();
            //conexion.close();
            return listaCliente;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public void addCliente(Cliente cliente) {
        /*Almacena un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement("call insertarCliente (?, ?, ?, ?, ?, ?);");//
            st.setString(1, cliente.getName());
            st.setString(2, cliente.getLastname());
            st.setString(3, cliente.getEmail());
            st.setString(4, cliente.getUsername());
            st.setString(5, cliente.getPassword());
            st.setString(6, cliente.getType());
            st.execute();
            st.close();
            //conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cliente getCliente(int id) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        Cliente cliente = new Cliente();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getCliente(?);");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            cliente.setClientId(rs.getInt(1));
            cliente.setName(rs.getString(2));
            cliente.setLastname(rs.getString(3));
            cliente.setEmail(rs.getString(4));
            cliente.setUsername(rs.getString(5));
            cliente.setPassword(rs.getString(6));
            cliente.setType(rs.getString(7));
            cliente.setStatus(rs.getInt(8));
            rs.close();
            ps.close();
            //conexion.close();
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();

            return cliente;
        }
    }

    public Cliente getClienteLogin(String username, String password) {
        /*Devuelve un objeto de tipo Cliente de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        Cliente cliente = new Cliente();
        try {
            PreparedStatement ps = conexion.prepareStatement("call getClienteLogin(?,?);");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            rs.next();
            cliente.setClientId(rs.getInt(1));
            cliente.setName(rs.getString(2));
            cliente.setLastname(rs.getString(3));
            cliente.setEmail(rs.getString(4));
            cliente.setUsername(rs.getString(5));
            cliente.setPassword(rs.getString(6));
            cliente.setType(rs.getString(7));
            cliente.setStatus(rs.getInt(8));

            rs.close();
            ps.close();
            //conexion.close();
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();

            return cliente;
        }
    }

    public void updateCliente(Cliente cliente) {
        /*Modifica un objeto en la base de datos, 
         cada atributo se utiliza en la posición que le corresponde 
         de la instrucción SQL */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call updateCliente(?,?,?,?,?,?);");
            st.setInt(1, cliente.getClientId());
            st.setString(2, cliente.getName());
            st.setString(3, cliente.getLastname());
            st.setString(4, cliente.getEmail());
            st.setString(5, cliente.getUsername());
            st.setString(6, cliente.getPassword());
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void DeleteCliente(int id) {
        /*Elimina un registro en la base de datos de acuerdo a su llave primaria */
        conexion = conexionSingleton.getConexion();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "call deleteCliente(?);");
            st.setInt(1, id);
            st.execute();
            st.close();
            //conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public int getClienteNextId() {
        /*Devuelve el siguiente número de ID a utilizar*/
        conexion = conexionSingleton.getConexion();
        int nextId = 0;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("CALL getClienteNextId();");
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

    /*
    public List<Cliente> getFiltroCliente(Cliente cliente) {
        

        List<Cliente> listaCliente = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getFiltroCliente(?,?,?,?,?,?,?);");
            
            prest.setString(1, String.valueOf(cliente.getClientId()));
            prest.setString(2, cliente.getName());
            prest.setString(3, cliente.getLastname());
            prest.setString(4, cliente.getEmail());
            prest.setString(5, cliente.getUsername());
            prest.setString(6, cliente.getPassword());
            prest.setString(7 ,cliente.getType());
            
              rs = prest.executeQuery();
            
            
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setClientId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setLastname(rs.getString(3));
                c.setEmail(rs.getString(4));
                c.setUsername(rs.getString(5));
                c.setPassword(rs.getString(6));
                c.setType(rs.getString(7));
                c.setStatus(rs.getInt(8));
                listaCliente.add(c);

            }
            rs.close();
            prest.close();
            return listaCliente;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
     */
    public List<Cliente> getFiltroCliente(String filtro) {
        /*Devuelve una lista con todos los usuarios 
         leidos de la base de datos*/
        conexion = conexionSingleton.getConexion();
        List<Cliente> listaCliente = new ArrayList<>();

        try {
            PreparedStatement prest = conexion.prepareStatement("call getFiltroCliente(?);");

            prest.setString(1, filtro);

            rs = prest.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setClientId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setLastname(rs.getString(3));
                c.setEmail(rs.getString(4));
                c.setUsername(rs.getString(5));
                c.setPassword(rs.getString(6));
                c.setType(rs.getString(7));
                c.setStatus(rs.getInt(8));
                listaCliente.add(c);

            }
            rs.close();
            prest.close();
            //conexion.close();
            return listaCliente;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
