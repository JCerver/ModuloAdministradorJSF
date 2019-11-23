
package modelo;

public class Cocina {

    private int id;
    private String claveAcceso;

    public Cocina() {
    }

    public Cocina(int id, String claveAcceso) {
        this.id = id;
        this.claveAcceso = claveAcceso;
    }

    public Cocina(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }
    
    

}
