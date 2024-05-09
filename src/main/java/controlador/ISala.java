/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador;

import java.util.List;
import modelodto.CuadroDTO;
import modelodto.JugadorDTO;
import modelodto.LineaDTO;

/**
 *
 * @author PC
 */
public interface ISala {
    void crearSala(List<JugadorDTO> jugadores);
    void asignarLinea(LineaDTO linea);
    void asignarCuadro(CuadroDTO cuadro);
    void retirarJugador(JugadorDTO jugador);
}
