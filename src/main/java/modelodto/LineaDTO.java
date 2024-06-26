/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelodto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author icedo
 */
public class LineaDTO implements Serializable {
    private String posicion;
    private int indice;
    private JugadorDTO jugador;

    public LineaDTO(String posicion, int indice) {
        this.posicion = posicion;
        this.indice = indice;
    }

    public LineaDTO(String posicion, int indice, JugadorDTO jugador) {
        this.posicion = posicion;
        this.indice = indice;
        this.jugador = jugador;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public JugadorDTO getJugador() {
        return jugador;
    }

    public void setJugador(JugadorDTO jugador) {
        this.jugador = jugador;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.posicion);
        hash = 79 * hash + this.indice;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LineaDTO other = (LineaDTO) obj;
        if (this.indice != other.indice) {
            return false;
        }
        if (!Objects.equals(this.posicion, other.posicion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LineaDTO{" + posicion + ", " + indice + ", " + jugador + ")";
    }
}
