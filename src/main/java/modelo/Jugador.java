/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author icedo Hector, Garcia Brayan
 */
public class Jugador {
    private String nombre;
    private int puntaje;
    private String color;
    private String avatar;
    private PreferenciaColor preferenciaColor;

    public Jugador() {
    }

    public Jugador(String nombre, int puntaje, String color, String avatar, PreferenciaColor preferenciaColor) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.color = color;
        this.avatar = avatar;
        this.preferenciaColor = preferenciaColor;
    }
    
    public Jugador(String nombre, String avatar) {
        this.nombre = nombre;
        this.avatar = avatar;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public PreferenciaColor getPreferenciaColor() {
        return preferenciaColor;
    }

    public void setPreferenciaColor(PreferenciaColor preferenciaColor) {
        this.preferenciaColor = preferenciaColor;
    }

  
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.nombre);
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
        final Jugador other = (Jugador) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return this.nombre + " - " + this.getAvatar();
    }
    
}
