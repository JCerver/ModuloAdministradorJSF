package app.controlador.negocio.administracion;

import app.dao.GestorClienteBD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Cliente;

@ManagedBean (name="listarClientes")
@SessionScoped
public class ListarClientes {
    private GestorClienteBD gestor;
    private List<Cliente> clientes;
    private String search;
    private int indexCliente;
    public ListarClientes() throws SQLException, ClassNotFoundException {
        gestor = new GestorClienteBD();
        
    }
    public void listar(){
        if (clientes == null || search==null || search.equals("")){
            clientes = gestor.getClientes();
        }else{
            clientes = gestor.getFiltroCliente(search);
        }
    }
    
    public void buscar(){
        clientes = gestor.getFiltroCliente(search);
        this.redirigir("");
    }
    public void eliminar(int id){
        gestor.DeleteCliente(id);
        clientes = null;
        this.redirigir("");
    }
    public void editar(int index){
        this.indexCliente = index;
        this.redirigir("modificarCliente.xhtml");
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
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getIndexCliente() {
        return indexCliente;
    }

    public void setIndexCliente(int indexCliente) {
        this.indexCliente = indexCliente;
    }

   
}
