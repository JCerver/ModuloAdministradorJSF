/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;


public class Mesero {

    private int id;
    private String nombre;
    private String apellidos;
    private String correo;
    private String usuario;
    private String password;
    private double puntuacionTotal; 
     private int numPuntuaciones;
     private int status;
     
     private int cantidadMesasAsignadas;

    public Mesero(int id, String nombre, String apellidos, String correo, String usuario, String password, double puntuacionTotal, int numPuntuaciones, int status) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
        this.puntuacionTotal = puntuacionTotal;
        this.numPuntuaciones = numPuntuaciones;
        this.status = status;
    }

    public Mesero() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(double puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public int getNumPuntuaciones() {
        return numPuntuaciones;
    }

    public void setNumPuntuaciones(int numPuntuaciones) {
        this.numPuntuaciones = numPuntuaciones;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCantidadMesasAsignadas() {
        return cantidadMesasAsignadas;
    }

    public void setCantidadMesasAsignadas(int cantidadMesasAsignadas) {
        this.cantidadMesasAsignadas = cantidadMesasAsignadas;
    }
     
}
