/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author icedo
 */
public class PreferenciaColor {
    private List<String> colores;

    public PreferenciaColor(String colorLinea2, String colorLinea3, String colorLinea4) {
        colores = new ArrayList<>();
        colores.add(colorLinea2);
        colores.add(colorLinea3);
        colores.add(colorLinea4);
    }

    public List<String> getColores() {
        return colores;
    }

    public void setColores(List<String> colores) {
        this.colores = colores;
    }
}
