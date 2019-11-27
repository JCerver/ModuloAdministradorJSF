package app.controlador.negocio.administracion;

import app.dao.GestorMeseroBD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Mesero;

@ManagedBean (name="listarMeseros")
@SessionScoped
public class ListarMeseros {
    private GestorMeseroBD gestor;
    private List<Mesero> meseros;
    private String search;
    private int indexMesero;
    public ListarMeseros() throws SQLException, ClassNotFoundException {
        gestor = new GestorMeseroBD();
        
    }
    public void listar(){
        if (meseros == null || search==null || search.equals("")){
            meseros = gestor.getMeseros();
            
        }else{
            meseros = gestor.getFiltroMesero(search);
        }
    }
    public int estrellasGrises(Mesero mes){
        int estrellas = 0;
        if(mes.getPuntuacionTotal() % 1 !=0){
            estrellas = 5 - ((int)mes.getPuntuacionTotal()) - 1; 
        }else{
            estrellas = 5 - ((int)mes.getPuntuacionTotal());
        }
        return estrellas;
    }
    public void buscar(){
        meseros = gestor.getFiltroMesero(search);
        this.redirigir("");
    }
    public void eliminar(int id){
        gestor.deleteMesero(id);
        meseros = null;
        this.redirigir("");
    }
    public void editar(int index){
        this.indexMesero = index;
        this.redirigir("modificarMesero.xhtml");
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
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<Mesero> getMeseros() {
        return meseros;
    }

    public void setMeseros(List<Mesero> meseros) {
        this.meseros = meseros;
    }

    public int getIndexMesero() {
        return indexMesero;
    }

    public void setIndexMesero(int indexMesero) {
        this.indexMesero = indexMesero;
    }


   
}
