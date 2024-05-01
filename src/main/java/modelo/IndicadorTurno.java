/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author icedo
 */
public class IndicadorTurno {
    
    private Jugador lider;
    private int siguiente;
    private List<Jugador> jugadores;

    public IndicadorTurno(Jugador lider) {
        this.jugadores = new ArrayList<>();
        this.lider = lider;
        this.siguiente = 0;
    }

    public IndicadorTurno(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        if (this.jugadores.get(0) != null) {
            this.lider = this.jugadores.get(0);
            this.siguiente = 0;
        }
    }

    public Jugador getLider() {
        return lider;
    }

    public void setLider(Jugador lider) {
        this.lider = lider;
    }

    public int getSiguiente() {
        int siguienteTemporal = this.siguiente;
        this.siguiente++;
        if (this.siguiente == this.jugadores.size()) {
            this.siguiente = 0;
        }
        return siguienteTemporal;
    }

    public void setSiguiente(int siguiente) {
        this.siguiente = siguiente;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void turnar() {
        Collections.shuffle(this.jugadores);
    }

    @Override
    public String toString() {
        return "IndicadorTurno{" + "lider=" + lider + ", siguiente=" + siguiente + ", jugadores=" + jugadores + '}';
    }
    
}
