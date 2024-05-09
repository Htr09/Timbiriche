/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import java.util.List;
import modelo.FiguraJuego;

/**
 *
 * @author xxbry
 */
public interface PanelObservador {
    void actualiza(List<FiguraJuego> movimiento);
}
