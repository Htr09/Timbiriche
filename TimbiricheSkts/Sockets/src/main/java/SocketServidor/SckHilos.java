/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelodto.IndicadorTurnoDTO;
import modelodto.JugadorDTO;
import modelodto.MensajeSockets;
import modelodto.RespuestaDTO;

/**
 *
 * @author PC
 */
public class SckHilos implements Runnable {
    
    private volatile JugadorDTO jugadorDTO;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile List<SckHilos> hilos;
    private SocketPartida sp;
    private Socket sck;
    private boolean votado;
    private int MAX;
    
    public SckHilos(Socket sck, List<SckHilos> hilos, int MAX) throws IOException{
        this.sck = sck;
        this.hilos = hilos;
        this.votado = false;
        this.sp = new SocketPartida();
        this.output = new ObjectOutputStream(sck.getOutputStream());
        this.output.flush();
        this.input = new ObjectInputStream(sck.getInputStream());
        this.MAX = MAX;
    }   
    
    @Override
    public void run() {
        Object mensajeEntrante;
        
        while(true){
            try{
                
                mensajeEntrante = input.readObject();
                
                Object mensajeSaliente = sp.procesarEntrada(mensajeEntrante);
                
                //Si el jugador es nuevo
                if(mensajeSaliente == MensajeSockets.JUGADOR_NUEVO){
                    System.out.println("Entró Jugador: " + mensajeEntrante);
                    this.jugadorDTO = (JugadorDTO) mensajeEntrante;
                    
                    //Creamos una lista con los jugadores
                    List<JugadorDTO> jugadores = new ArrayList<>();
                    for(SckHilos hilo : hilos){
                        jugadores.add(hilo.getJugadorDTO());
                    }
                    
                    //Actualiza a los demas jugadores
                    transmitirATodos(jugadores);
                    
                    if(hilos.size() == MAX){
                        Object empezarPartida = sp.empezarParida(jugadores);
                        transmitirATodos(empezarPartida);
                    }   
                    
                    //Si es un voto
                }else if(mensajeSaliente == MensajeSockets.VOTO){
                    //Si no votó
                    if (this.votado == false) {
                        this.votado = true;
                        mensajeSaliente = this.jugadorDTO.getNombreJugador() + " ha votado";
                        //Si ya habia votado
                    } else {
                        this.votado = false;
                        mensajeSaliente = this.jugadorDTO.getNombreJugador() + " ha cancelado el voto";
                    }
                    
                    transmitirATodos(mensajeSaliente);
                } else if(mensajeSaliente instanceof IndicadorTurnoDTO){
                    transmitirATodos(mensajeSaliente);
                }else if (mensajeSaliente instanceof RespuestaDTO) {
                    transmitirATodos(mensajeSaliente);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(SckHilos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SckHilos.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }   
    } 
    
    public JugadorDTO getJugadorDTO() {
        return jugadorDTO;
    }
    
    public void setJugadorDTO(JugadorDTO jugadorDTO) {
        this.jugadorDTO = jugadorDTO;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public boolean isVotado() {
        return votado;
    }

    public void setVotado(boolean votado) {
        this.votado = votado;
    }
    
    public synchronized void transmitirASiMismo(Object mensaje) {
        try {
            this.output.writeObject(mensaje);
            this.output.flush();
        } catch (IOException ex) {
            Logger.getLogger(SckHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void transmitirATodos(Object mensaje) {
        for (SckHilos thread : hilos) {
            try {
                thread.output.writeObject(mensaje);
                thread.output.flush();
            } catch (IOException ex) {
                Logger.getLogger(SckHilos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
