package app.controlador.negocio.administracion;

import app.dao.GestorAdministradorBD;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Administrador;

@ManagedBean
@RequestScoped
public class RegistrarAdministrador {
    private String nombre;
    private String apellidos;
    private String correo;
    private String usuario;
    private String password;
    private GestorAdministradorBD gestor;
    public RegistrarAdministrador() throws SQLException, ClassNotFoundException {
        gestor = new GestorAdministradorBD();
    }
    public void registrar(){
        Administrador admin = new Administrador(0, nombre, correo, usuario, password, 1);
        gestor.addAdministrador(admin);
        this.redirigir("");
    }
    public void redirigir(String destino){
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        try{
            if(destino.equals("")){
                context.redirect(((HttpServletRequest) 
                    context.getRequest()).getRequestURI());
            }else{
               context.redirect(destino); 
            }
        }catch(IOException ex){
            System.out.println(ex.getCause());
        }
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
    
}
