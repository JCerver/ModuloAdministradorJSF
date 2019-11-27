package app.controlador.negocio.administracion;

import app.dao.GestorMeseroBD;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Mesero;

@ManagedBean (name="actualizarMesero")
@RequestScoped
public class ActualizarMesero {
    private Mesero mesero;
    private GestorMeseroBD gestor;
    
    public ActualizarMesero() throws SQLException, ClassNotFoundException {
        mesero = new Mesero();
        gestor = new GestorMeseroBD();
    }
    public void cargar(Mesero mes){
        this.mesero = mes;
    }
    public void actualizar(){
        gestor.updateMesero(mesero);
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

    public Mesero getMesero() {
        return mesero;
    }

    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
    }

    
    
    
}
