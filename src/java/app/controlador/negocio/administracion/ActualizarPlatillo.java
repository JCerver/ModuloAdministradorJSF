/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controlador.negocio.administracion;

import app.dao.GestorPlatilloBD;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Platillo;

/**
 *
 * @author jcerver
 */
@ManagedBean
@SessionScoped
public class ActualizarPlatillo {
    
     private int id;
    private InputStream imagen;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    GestorPlatilloBD gestorPlatillo;
    Platillo platillo;
    

    public ActualizarPlatillo() throws SQLException, ClassNotFoundException {
        gestorPlatillo= new GestorPlatilloBD();
    }
    
    
    public void redirigirPlantilla(String destino,int idPlatillo){
        id=idPlatillo;
        this.redirigir("modificarPlatillo.xhtml");
       platillo = gestorPlatillo.getPlatillo(id);
        
       
    }
    
      public void actualizarPlatillo() throws SQLException{
     
          Platillo p = new Platillo(id, platillo.getNombre(), platillo.getDescripcion(), platillo.getPrecio(), platillo.getCategoria());
                    gestorPlatillo.updatePlatilloNoImage(platillo);
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

    public Platillo getPlatillo() {
        return platillo;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
    }
    
    
    
    
    
}
