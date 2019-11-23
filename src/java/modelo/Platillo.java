/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.InputStream;

public class Platillo {

    private int id;
    private InputStream imagen;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private double puntuacionTotal;
    private int numeroPuntuaciones;
    private int status;
    
    private int idDetOrdenDePlatillo;/*Solo para saber en que detOrden esta el platillo*/
    private int cantidadPlatillos;
    private int cantidadDeVecesComsumido;
    private String fechaCantidadDeVecesComsumido;

    public Platillo(int id, InputStream imagen, String nombre, String descripcion, double precio, String categoria, double puntuacionTotal, int numeroPuntuaciones, int status) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.puntuacionTotal = puntuacionTotal;
        this.numeroPuntuaciones = numeroPuntuaciones;
        this.status = status;
    }

    public Platillo(int id, InputStream image, String nombre, String descripcion, double precio, String categoria) {
        this.id = id;
        this.imagen = image;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Platillo(int id, String nombre, String descripcion, double precio, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Platillo() {
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

    public int getIdDetOrdenDePlatillo() {
        return idDetOrdenDePlatillo;
    }

    public void setIdDetOrdenDePlatillo(int idDetOrdenDePlatillo) {
        this.idDetOrdenDePlatillo = idDetOrdenDePlatillo;
    }

    public int getCantidadPlatillos() {
        return cantidadPlatillos;
    }

    public void setCantidadPlatillos(int cantidadPlatillos) {
        this.cantidadPlatillos = cantidadPlatillos;
    }

    public int getCantidadDeVecesComsumido() {
        return cantidadDeVecesComsumido;
    }

    public void setCantidadDeVecesComsumido(int cantidadDeVecesComsumido) {
        this.cantidadDeVecesComsumido = cantidadDeVecesComsumido;
    }

    public String getFechaCantidadDeVecesComsumido() {
        return fechaCantidadDeVecesComsumido;
    }

    public void setFechaCantidadDeVecesComsumido(String fechaCantidadDeVecesComsumido) {
        this.fechaCantidadDeVecesComsumido = fechaCantidadDeVecesComsumido;
    }
    
    

}
