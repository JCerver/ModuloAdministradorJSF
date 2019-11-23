/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
 
public class SesionServicio {

    private int id;
    private int fk_cliente;
    private int fk_mesero;
    private int numMesa;
    private double puntajeMeseroServicio;
    private double totalVenta;
    private String tipoPago;
    private String fecha;
    private int estadoSesion;
    private int status;
    
    private int IdOrden;
    private String estadoOrden;
    private double totalPorOrdenCalculado;
    
    private double totalPorSesionCalculado;
    
    
    private String nombreConcatenadoMeseroAsignado;
    private String nombreConcatenadoCliente;

    public SesionServicio() {
    }


    public SesionServicio(int id, int fk_cliente, int fk_mesero, int numMesa, double puntajeMeseroServicio, double totalVenta, String tipoPago, String fecha, int estadoSesion, int status) {
        this.id = id;
        this.fk_cliente = fk_cliente;
        this.fk_mesero = fk_mesero;
        this.numMesa = numMesa;
        this.puntajeMeseroServicio = puntajeMeseroServicio;
        this.totalVenta = totalVenta;
        this.tipoPago = tipoPago;
        this.fecha = fecha;
        this.estadoSesion = estadoSesion;
        this.status = status;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_cliente() {
        return fk_cliente;
    }

    public void setFk_cliente(int fk_cliente) {
        this.fk_cliente = fk_cliente;
    }

    public int getFk_mesero() {
        return fk_mesero;
    }

    public void setFk_mesero(int fk_mesero) {
        this.fk_mesero = fk_mesero;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public double getPuntajeMeseroServicio() {
        return puntajeMeseroServicio;
    }

    public void setPuntajeMeseroServicio(double puntajeMeseroServicio) {
        this.puntajeMeseroServicio = puntajeMeseroServicio;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getEstadoSesion() {
        return estadoSesion;
    }

    public void setEstadoSesion(int estadoSesion) {
        this.estadoSesion = estadoSesion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdOrden() {
        return IdOrden;
    }

    public void setIdOrden(int IdOrden) {
        this.IdOrden = IdOrden;
    }

    public double getTotalPorOrdenCalculado() {
        return totalPorOrdenCalculado;
    }

    public void setTotalPorOrdenCalculado(double totalPorOrdenCalculado) {
        this.totalPorOrdenCalculado = totalPorOrdenCalculado;
    }

    public double getTotalPorSesionCalculado() {
        return totalPorSesionCalculado;
    }

    public void setTotalPorSesionCalculado(double totalPorSesionCalculado) {
        this.totalPorSesionCalculado = totalPorSesionCalculado;
    }

    public String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(String estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreConcatenadoMeseroAsignado() {
        return nombreConcatenadoMeseroAsignado;
    }

    public void setNombreConcatenadoMeseroAsignado(String nombreConcatenadoMeseroAsignado) {
        this.nombreConcatenadoMeseroAsignado = nombreConcatenadoMeseroAsignado;
    }

    public String getNombreConcatenadoCliente() {
        return nombreConcatenadoCliente;
    }

    public void setNombreConcatenadoCliente(String nombreConcatenadoCliente) {
        this.nombreConcatenadoCliente = nombreConcatenadoCliente;
    }

    
    
    
}
