package app.controlador.negocio.administracion;

import app.dao.GestorCategoriaPlatilloBD;
import app.dao.GestorPlatilloBD;
import app.dao.GestorPlatilloDelDiaBD;
import beans.AdminBean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.CategoriaPlatillo;
import modelo.Platillo;
import modelo.PlatilloDelDia;

@ManagedBean (name="listarPlatillos")
@RequestScoped
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
    private String filter = null;
    private String filterName = null;
    private String seccion;
    
    public ListarPlatillos() {
        
    }

    public void listar() {
        try {
            System.out.println("Entreeeee");
            gestorPlatilloBD = new GestorPlatilloBD();
            gestorCategoriaPlatilloBD = new GestorCategoriaPlatilloBD();
            gestorPlatilloDelDiaBD = new GestorPlatilloDelDiaBD();
            
            platillos = gestorPlatilloBD.getPlatillos();
            if (platillos != null) {
                listaPlatillosDelDia = gestorPlatilloDelDiaBD.getPlatillosDelDia();
                categorias = gestorCategoriaPlatilloBD.getCategoriasPlatillos();
                listaCategorias = gestorCategoriaPlatilloBD.getCategoriasPlatillos();
                seccion = "platillos";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListarPlatillos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListarPlatillos.class.getName()).log(Level.SEVERE, null, ex);
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
