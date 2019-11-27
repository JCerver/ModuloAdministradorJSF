package app.controlador.negocio.administracion;

import app.dao.GestorClienteBD;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Cliente;

@ManagedBean (name="actualizarCliente")
@RequestScoped
public class ActualizarCliente {
    private Cliente cliente;
    private GestorClienteBD gestor;
    
    public ActualizarCliente() throws SQLException, ClassNotFoundException {
        cliente = new Cliente();
        gestor = new GestorClienteBD();
    }
    public void cargar(Cliente cliente){
        this.cliente = cliente;
    }
    public void actualizar(){
        gestor.updateCliente(cliente);
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
