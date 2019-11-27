package app.controlador.negocio.administracion;

import app.dao.GestorAdministradorBD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Administrador;

@ManagedBean (name="listarAdministradores")
@SessionScoped
public class ListarAdministradores {
    private GestorAdministradorBD gestor;
    private List<Administrador> administradores;
    private String search;
    private int indexAdministrador;
    public ListarAdministradores() throws SQLException, ClassNotFoundException {
        gestor = new GestorAdministradorBD();
        
    }
    public void listar(){
        if (administradores == null || search==null || search.equals("")){
            administradores = gestor.getAdministradores();
        }else{
            administradores = gestor.getFiltroAdministrador(search);
        }
    }
    
    public void buscar(){
        administradores = gestor.getFiltroAdministrador(search);
        this.redirigir("");
    }
    public void eliminar(int id){
        gestor.deleteAdministrador(id);
        administradores = null;
        this.redirigir("");
    }
    public void editar(int index){
        this.indexAdministrador = index;
        this.redirigir("modificarAdministrador.xhtml");
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
    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getIndexAdministrador() {
        return indexAdministrador;
    }

    public void setIndexAdministrador(int indexAdministrador) {
        this.indexAdministrador = indexAdministrador;
    }
    
}
