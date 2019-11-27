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

@ManagedBean (name="actualizarAdministrador")
@RequestScoped
public class ActualizarAdministrador {
    private Administrador admin;
    private GestorAdministradorBD gestor;
    
    public ActualizarAdministrador() throws SQLException, ClassNotFoundException {
        admin = new Administrador();
        gestor = new GestorAdministradorBD();
    }
    public void cargar(Administrador admin){
        this.admin = admin;
    }
    public void actualizar(){
        gestor.updateAdministrador(admin);
        this.redirigir("index.xhtml");
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
    
    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
    
}
