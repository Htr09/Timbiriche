/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;



/**
 *
 * @author icedo
 */
public class Sala {
    
    private Tablero tablero;
    private IndicadorTurno indicadorturno;
    private int tamanio;

    public Sala() {}

    /**
     * Constructor de Sala
     * @param indicadorturno
     * @param tablero
     * @param tamanio 
     */
    public Sala(IndicadorTurno indicadorturno, Tablero tablero, int tamanio) {
        this.tablero = tablero;
        this.indicadorturno = indicadorturno;
        this.tamanio = tamanio;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public IndicadorTurno getIndicadorTurno() {
        return indicadorturno;
    }

    public void setIndicadorTurno(IndicadorTurno indicadorturno) {
        this.indicadorturno = indicadorturno;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
    
    public void agregarJugador(Jugador jugador){
        if(this.indicadorturno.getJugadores().size() < this.tamanio){
            this.indicadorturno.getJugadores().add(jugador);
        }
    }
    
    public void eliminarJugador(Jugador jugador){
        this.indicadorturno.getJugadores().remove(jugador);
    }
}
