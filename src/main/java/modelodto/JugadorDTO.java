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
public class JugadorDTO implements Serializable {
    private String nombreJugador;
    private String avatar;
    private int puntaje;
    
    public JugadorDTO(String nombreJugador, String avatar, int puntaje) {
        this.nombreJugador = nombreJugador;
        this.avatar = avatar;
        this.puntaje = puntaje;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nombreJugador);
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
        final JugadorDTO other = (JugadorDTO) obj;
        if (!Objects.equals(this.nombreJugador, other.nombreJugador)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JugadorDTO{" + "nombreJugador=" + nombreJugador + ", avatar=" + avatar + ", puntaje=" + puntaje + '}';
    }
    
}
