/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controlador.negocio.administracion;

import app.dao.GestorAdministradorBD;
import beans.AdminBean;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Isaac Perez
 */
@ManagedBean(name="login")
@RequestScoped
public class Login {
    @ManagedProperty(value="#{adminBean}")
    private AdminBean adminBean;
    private String usuario;
    private String password;
    private String error;
    private GestorAdministradorBD gestorAdmin;

    public Login(){
    }

    public String login() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            gestorAdmin = new GestorAdministradorBD();
            adminBean.setAdmin(gestorAdmin.getAdministradorLogin(usuario, password));
            
            if (adminBean.getAdmin().getId() > 0) {
               context.redirect("index.xhtml");
            } else {
                error = "El usuario y/o contraseña no coinciden";
                return "login";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
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

    public GestorAdministradorBD getGestorAdmin() {
        return gestorAdmin;
    }

    public void setGestorAdmin(GestorAdministradorBD gestorAdmin) {
        this.gestorAdmin = gestorAdmin;
    }

    public void setAdminBean(AdminBean adminBean) {
        this.adminBean = adminBean;
    }

    
}
