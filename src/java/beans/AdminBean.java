package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Administrador;

@ManagedBean(name="adminBean")
@SessionScoped
public class AdminBean{
    private Administrador admin;
    public AdminBean(){
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
    
}
