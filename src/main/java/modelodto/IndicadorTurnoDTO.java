/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelodto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author icedo
 */
public class IndicadorTurnoDTO implements Serializable {
    private List<JugadorDTO> jugadores;
    private int siguiente;

    public IndicadorTurnoDTO(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
        this.siguiente = 0;
    }

    public IndicadorTurnoDTO(List<JugadorDTO> jugadores, int siguiente) {
        this.jugadores = jugadores;
        this.siguiente = siguiente;
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    public int getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(int siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "IndicadorTurnoDTO{" + "jugadores=" + jugadores + ", siguiente=" + siguiente + '}';
    }
}
