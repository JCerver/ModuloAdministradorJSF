/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Orden {

    private int id;
    private int fk_sesionServicio;
    private String estadoOrden;
    private int status;

    public Orden() {
    }

    public Orden(int id, int fk_sesionServicio, String estadoOrden, int status) {
        this.id = id;
        this.fk_sesionServicio = fk_sesionServicio;
        this.estadoOrden = estadoOrden;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_sesionServicio() {
        return fk_sesionServicio;
    }

    public void setFk_sesionServicio(int fk_sesionServicio) {
        this.fk_sesionServicio = fk_sesionServicio;
    }

    public String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(String estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
