/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.*;
/**
 *
 * @author icedo
 */
public class JuegoControlador {
    private volatile static JuegoControlador instance;
    private volatile Sala sala;
    private volatile Linea ultimaLinea;
    private volatile Cuadro ultimoCuadro;

    private JuegoControlador() {
        sala = new Sala();
    }

    public static synchronized JuegoControlador getInstance() {
        if (instance == null) {
            instance = new JuegoControlador();
        }
        return instance;
    }

    public synchronized void asignarIndicadorTurno(IndicadorTurno indicadorTurno) {
        this.sala.setIndicadorTurno(indicadorTurno);
    }

    public synchronized void asignarTablero(Tablero tablero) {
        this.sala.setTablero(tablero);
    }

    public synchronized void asignarLinea(Linea linea) {
        if(linea.getPosicion() == Posicion.HORIZONTAL){
            this.sala.getTablero().getLineasHorizontales().get(linea.getIndice()).setJugador(linea.getJugador());
            this.ultimaLinea = this.sala.getTablero().getLineasHorizontales().get(linea.getIndice());
        }else if(linea.getPosicion() == Posicion.VERTICAL){
            this.sala.getTablero().getLineasVerticales().get(linea.getIndice()).setJugador(linea.getJugador());
            this.ultimaLinea = this.sala.getTablero().getLineasVerticales().get(linea.getIndice());
        }
    }

    public synchronized void asignarCuadro(Cuadro cuadro) {
        for (Jugador jugador : this.sala.getIndicadorTurno().getJugadores()) {
            if(jugador.equals(cuadro.getJugador())){
                this.sala.getTablero().getCuadros().get(cuadro.getIndice()).setJugador(jugador);
                jugador.setPuntaje(jugador.getPuntaje()+1);
                this.ultimoCuadro = this.sala.getTablero().getCuadros().get(cuadro.getIndice());
            }
        }
    }

    public synchronized void retirarJugador(Jugador jugador) {
        for (Jugador jugObj : this.sala.getIndicadorTurno().getJugadores()) {
            if(jugObj.equals(jugador)){
                this.sala.getIndicadorTurno().getJugadores().remove(jugObj);
                
                for (Linea vertical : this.sala.getTablero().getLineasVerticales()) {
                    if(vertical.getJugador().equals(jugObj)){
                        vertical.setJugador(null);
                    }
                }
                
                for (Linea horizontal : this.sala.getTablero().getLineasHorizontales()) {
                    if(horizontal.getJugador().equals(jugObj)){
                        horizontal.setJugador(null);
                    }
                }
                
                for (Cuadro cuadro : this.sala.getTablero().getCuadros()) {
                    if(cuadro.getJugador().equals(jugObj)){
                        cuadro.setJugador(null);
                    }
                }
            }
        }
    }

    public synchronized Sala getSala() {
        return sala;
    }
    
    public synchronized IndicadorTurno obtenerIndicadorTurno(){
        return this.sala.getIndicadorTurno();
    }
    
    public synchronized Linea obtenerUltimaLinea(){
        return this.ultimaLinea;
    }
    
    public synchronized Cuadro obtenerUltimoCuadro(){
        return this.ultimoCuadro;
    }
    
    public synchronized int obtenerTurnoSiguiente(){
        return this.sala.getIndicadorTurno().getSiguiente();
    }
}
