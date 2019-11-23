package modelo;

public class DetalleOrden {

    private int id;
    private int fk_orden;
    private int fk_platillo;
    private int cantidad;
    private double puntajePlatillo;
    private int status;

    public DetalleOrden() {
    }

    public DetalleOrden(int id, int fk_orden, int fk_platillo, int cantidad, double puntajePlatillo, int status) {
        this.id = id;
        this.fk_orden = fk_orden;
        this.fk_platillo = fk_platillo;
        this.cantidad = cantidad;
        this.puntajePlatillo = puntajePlatillo;
        this.status = status;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_orden() {
        return fk_orden;
    }

    public void setFk_orden(int fk_orden) {
        this.fk_orden = fk_orden;
    }

    public int getFk_platillo() {
        return fk_platillo;
    }

    public void setFk_platillo(int fk_platillo) {
        this.fk_platillo = fk_platillo;
    }

    public double getPuntajePlatillo() {
        return puntajePlatillo;
    }

    public void setPuntajePlatillo(double puntajePlatillo) {
        this.puntajePlatillo = puntajePlatillo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
