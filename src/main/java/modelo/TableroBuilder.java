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
public class TableroBuilder {

    private int dimension;
    private List<Linea> lineasHorizontales = new ArrayList<>();
    private List<Linea> lineasVerticales = new ArrayList<>();
    private List<Cuadro> cuadros = new ArrayList<>();
    private List<Punto> puntos = new ArrayList<>();

    public TableroBuilder(int dimension) {
        this.dimension = dimension;
    }

    public TableroBuilder setLineasHorizontales(List<Linea> lineasHorizontales) {
        this.lineasHorizontales = lineasHorizontales;
        return this;
    }

    public TableroBuilder setLineasVerticales(List<Linea> lineasVerticales) {
        this.lineasVerticales = lineasVerticales;
        return this;
    }

    public TableroBuilder setCuadros(List<Cuadro> cuadros) {
        this.cuadros = cuadros;
        return this;
    }

    public TableroBuilder setPuntos(List<Punto> puntos) {
        this.puntos = puntos;
        return this;
    }

    public Tablero build() {
        return new Tablero(dimension, lineasHorizontales, lineasVerticales, cuadros, puntos);
    }
}