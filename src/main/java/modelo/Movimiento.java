/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author icedo
 */
public class Movimiento {
    private Linea linea;
    private List<Cuadro> cuadros = new ArrayList<>();

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public List<Cuadro> getCuadros() {
        return cuadros;
    }

    public void setCuadros(List<Cuadro> cuadros) {
        this.cuadros = cuadros;
    }

    public void setCuadro(Cuadro cuadro) {
        if(cuadros.size() < 2){
            cuadros.add(cuadro);
        }else{
            cuadros.remove(0);
            cuadros.add(cuadro);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.linea);
        hash = 37 * hash + Objects.hashCode(this.cuadros);
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
        final Movimiento other = (Movimiento) obj;
        if (!Objects.equals(this.linea, other.linea)) {
            return false;
        }
        if (!Objects.equals(this.cuadros, other.cuadros)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Movimiento{" + "linea=" + linea + ", cuadros=" + cuadros + '}';
    }
}
