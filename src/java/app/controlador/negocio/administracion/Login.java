/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controlador.negocio.administracion;

import app.dao.GestorAdministradorBD;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Administrador;

/**
 *
 * @author Isaac Perez
 */
@ManagedBean
@RequestScoped
public class Login {

    private String usuario;
    private String password;
    private String error;
    private GestorAdministradorBD gestorAdmin;
    
    public Login(){
    }

    public String login() {

        try {
            gestorAdmin = new GestorAdministradorBD();
            Administrador administrador = gestorAdmin.getAdministradorLogin(usuario, password);
            if (administrador.getId() > 0) {
               return "listarPlatillos";
            } else {
                error = "El usuario y/o contraseña no coinciden";
                return "login";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "login";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
   
}
