/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controlador.negocio.administracion;

import app.dao.GestorCategoriaPlatilloBD;
import app.dao.GestorPlatilloBD;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.CategoriaPlatillo;
import modelo.Platillo;

/**
 *
 * @author jcerver
 */
@ManagedBean
@SessionScoped
public class RegistrarPlatillo {

     private int id;
    private InputStream imagen;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private double puntuacionTotal;
    private int numeroPuntuaciones;
    private int status;
    private GestorPlatilloBD gestor;
    private GestorCategoriaPlatilloBD gestorCategoriasPlatilloBD;
    private List<CategoriaPlatillo> listaCategorias;
    
    public RegistrarPlatillo() throws SQLException, ClassNotFoundException{
        gestor = new GestorPlatilloBD();
        gestorCategoriasPlatilloBD = new GestorCategoriaPlatilloBD();
        
        listaCategorias = gestorCategoriasPlatilloBD.getCategoriasPlatillos();
    }
    
    public void agregarPlatillo(){
        Platillo platillo = new Platillo(0, imagen, nombre, descripcion, precio, categoria, 5, 1, 1);
        gestor.addPlatillo(platillo);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(double puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public int getNumeroPuntuaciones() {
        return numeroPuntuaciones;
    }

    public void setNumeroPuntuaciones(int numeroPuntuaciones) {
        this.numeroPuntuaciones = numeroPuntuaciones;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public GestorPlatilloBD getGestor() {
        return gestor;
    }

    public void setGestor(GestorPlatilloBD gestor) {
        this.gestor = gestor;
    }

    public List<CategoriaPlatillo> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CategoriaPlatillo> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    
    
    
    
    
}
