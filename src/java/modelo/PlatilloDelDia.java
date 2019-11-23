/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.InputStream;

public class PlatilloDelDia {
    private int idRegistro;
    private int idPlatillo;

    public PlatilloDelDia(int idRegistro, int idPlatillo) {
        this.idRegistro = idRegistro;
        this.idPlatillo = idPlatillo;
    }

    public PlatilloDelDia() {
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }
}
