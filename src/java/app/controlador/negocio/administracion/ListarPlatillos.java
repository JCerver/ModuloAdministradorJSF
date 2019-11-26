package app.controlador.negocio.administracion;

import app.dao.GestorCategoriaPlatilloBD;
import app.dao.GestorPlatilloBD;
import app.dao.GestorPlatilloDelDiaBD;
import beans.AdminBean;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.CategoriaPlatillo;
import modelo.Platillo;
import modelo.PlatilloDelDia;

@ManagedBean (name="listarPlatillos")
@SessionScoped
public class ListarPlatillos {

    @ManagedProperty(value = "#{adminBean}")
    private AdminBean adminBean;
    private GestorPlatilloBD gestorPlatilloBD;
    private GestorCategoriaPlatilloBD gestorCategoriaPlatilloBD;
    private GestorPlatilloDelDiaBD gestorPlatilloDelDiaBD;

    private Collection<Platillo> platillos;
    private Collection<CategoriaPlatillo> categorias;
    private List<PlatilloDelDia> listaPlatillosDelDia;
    private List<CategoriaPlatillo> listaCategorias;
    
    // Para los filtros
    private String filter;
    private String filterName = null;
    private String seccion;
    
    public ListarPlatillos()throws SQLException, ClassNotFoundException {
        gestorPlatilloBD = new GestorPlatilloBD();
        gestorCategoriaPlatilloBD = new GestorCategoriaPlatilloBD();
        gestorPlatilloDelDiaBD = new GestorPlatilloDelDiaBD();
        filter = "0";
    }

    public void listar() {
        if (platillos == null) {
            platillos = gestorPlatilloBD.getPlatillos();
        }
        listaCategorias = gestorCategoriaPlatilloBD.getCategoriasPlatillos();
        listaPlatillosDelDia = gestorPlatilloDelDiaBD.getPlatillosDelDia();
        categorias = gestorCategoriaPlatilloBD.getCategoriasPlatillos();
        seccion = "platillos";
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
    }

    public void buscar() throws IOException {
        platillos = gestorPlatilloBD.getPlatillosPorNombre(filterName);
        if(platillos.isEmpty()){
            platillos = null;
        }
        categorias = gestorCategoriaPlatilloBD.getCategoriasPlatillos();
        this.redirigir("");
    }
    public void aplicarFiltro(String filtro){
        this.filter = filtro;
        platillos = null;
        if (!filtro.equals("0")) {
            platillos = new ArrayList();
            if (filtro.equals("platillosDelDia")) {
                System.out.println("Aplico filtrooo");
                listaPlatillosDelDia = gestorPlatilloDelDiaBD.getPlatillosDelDia();
                for (PlatilloDelDia platilloDelDia : listaPlatillosDelDia) {
                    Platillo platillo = gestorPlatilloBD.getPlatillo(platilloDelDia.getIdPlatillo());
                    platillos.add(platillo);
                }
            } else {
                platillos = gestorPlatilloBD.getPlatillosPorCategoria(filter);
            }
        }
        try {
            this.redirigir("");
        } catch (IOException ex) {
            Logger.getLogger(ListarPlatillos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void redirigir(String destino) throws IOException{
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        if(destino.equals("")){
            context.redirect(((HttpServletRequest) 
                context.getRequest()).getRequestURI());
        }else{
           context.redirect(destino); 
        }
        
    }
    public boolean esPlatilloDelDia(int id){
        for (PlatilloDelDia platilloDia : listaPlatillosDelDia) {
            if(platilloDia.getIdPlatillo() == id){
                return true;
            }
        }
        return false;
    }
    public int getIdPlatilloDelDia(int id){
        for (PlatilloDelDia platilloDia : listaPlatillosDelDia) {
            if(platilloDia.getIdPlatillo() == id){
                return id;
            }
        }
        return 0;
    }
    public Collection<Platillo> getPlatillos() {
        return platillos;
    }

    public void setPlatillos(Collection<Platillo> platillos) {
        this.platillos = platillos;
    }

    public Collection<CategoriaPlatillo> getCategorias() {
        return categorias;
    }

    public void setCategorias(Collection<CategoriaPlatillo> categorias) {
        this.categorias = categorias;
    }

    public List<PlatilloDelDia> getListaPlatillosDelDia() {
        return listaPlatillosDelDia;
    }

    public void setListaPlatillosDelDia(List<PlatilloDelDia> listaPlatillosDelDia) {
        this.listaPlatillosDelDia = listaPlatillosDelDia;
    }

    public List<CategoriaPlatillo> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CategoriaPlatillo> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public void setAdminBean(AdminBean adminBean) {
        this.adminBean = adminBean;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    
}
