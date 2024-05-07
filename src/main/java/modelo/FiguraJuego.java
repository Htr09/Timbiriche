/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author icedo
 */
public class FiguraJuego extends Figura{
    
    private Jugador jugador;
    private int indice;

    public FiguraJuego(int ancho, int altura, int x, int y) {
        super(ancho, altura, x, y);
    }

    public FiguraJuego(Jugador jugador, int ancho, int altura, int x, int y) {
        super(ancho, altura, x, y);
        this.jugador = jugador;
    }

    public FiguraJuego(Jugador jugador, int indice, int ancho, int altura, int x, int y) {
        super(ancho, altura, x, y);
        this.jugador = jugador;
        this.indice = indice;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    @Override
    public String toString() {
        return "FiguraJuego{" + "jugador=" + jugador + ", indice=" + indice + '}';
    }
    
}
