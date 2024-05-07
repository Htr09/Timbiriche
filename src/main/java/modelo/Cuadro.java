/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author icedo
 */
public class Cuadro extends FiguraJuego{
    
    private Linea arriba, abajo, izquierda, derecha;

    public Cuadro(int indice) {
        super(null, indice, 0, 0, 0, 0);
    }

    public Cuadro(Jugador jugador, int indice) {
        super(jugador, indice, 0, 0, 0, 0);
    }

    public Cuadro(Linea arriba, Linea abajo, Linea izquierda, Linea derecha, Jugador jugador, int indice) {
        super(jugador, indice, 0, 0, 0, 0);
        this.arriba = arriba;
        this.abajo = abajo;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public Cuadro(Linea arriba, Linea abajo, Linea izquierda, Linea derecha, Jugador jugador, int indice, int ancho, int altura, int x, int y) {
        super(jugador, indice, ancho, altura, x, y);
        this.arriba = arriba;
        this.abajo = abajo;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public char obtenerInicial() {
        return this.getJugador().getNombre().charAt(0);
    }

    public boolean tieneLinea(Linea lineaBuscada) {
        return arriba.equals(lineaBuscada) || abajo.equals(lineaBuscada)
                || izquierda.equals(lineaBuscada) || derecha.equals(lineaBuscada);
    }

    public boolean esCuadrado() {
        return arriba.getJugador() != null && abajo.getJugador() != null
                && izquierda.getJugador() != null && derecha.getJugador() != null;
    }

    @Override
    public String toString() {
        return "Cuadro{" + "jugador=" + super.getJugador() + "indice" + super.getIndice() + '}';
    }
}
