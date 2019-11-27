/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.dao.GestorCocinaBD;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Cocina;

/**
 *
 * @author usuario
 */
@ManagedBean
@RequestScoped
public class CocinaBean extends Cocina {

    private GestorCocinaBD gestorCocinaBD;

    public CocinaBean() throws SQLException, ClassNotFoundException {
        gestorCocinaBD = new GestorCocinaBD();
    }

    public void CambiarClaveCocina() {

        gestorCocinaBD.updateClaveAcceso(getClaveAcceso());

    }
}
