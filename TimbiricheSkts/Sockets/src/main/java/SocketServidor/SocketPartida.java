/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketServidor;

import controlador.IDTOControlador;
import controlador.ISala;
import java.util.List;
import modelodto.JugadorDTO;
import modelodto.MensajeSockets;

/**
 *
 * @author PC
 */
public class SocketPartida {
    
    private ISala iSala;
    private IDTOControlador dtoControl;    
    
    public Object procesarEntrada(Object mensajeEntrante){
        
        if(mensajeEntrante instanceof JugadorDTO){
            return MensajeSockets.JUGADOR_NUEVO;
        }else if(mensajeEntrante == MensajeSockets.VOTO){
            return MensajeSockets.VOTO;
        }
        
        return null;
    }
    
    public Object empezarParida(List<JugadorDTO> jugadoresDTO){
        iSala.crearSala(jugadoresDTO);
        return dtoControl.obtenerIndicadorTurno();
    }
}
