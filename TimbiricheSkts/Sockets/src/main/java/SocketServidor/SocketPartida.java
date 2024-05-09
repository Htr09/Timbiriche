/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketServidor;

import controlador.IDTOControlador;
import controlador.ISala;
import java.util.List;
import modelodto.CuadroDTO;
import modelodto.JugadorDTO;
import modelodto.MensajeSockets;
import modelodto.MovimientoDTO;
import modelodto.RespuestaDTO;

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
        }else if(mensajeEntrante instanceof MovimientoDTO){
            MovimientoDTO movimientoDTO = (MovimientoDTO) mensajeEntrante;
            
            if(movimientoDTO.getLinea() != null){
                iSala.asignarLinea(movimientoDTO.getLinea());
            }else if(movimientoDTO.getCuadros() != null){
                for(CuadroDTO cuadradoDto : movimientoDTO.getCuadros()){
                    iSala.asignarCuadro(cuadradoDto);
                }
            }
            RespuestaDTO respuestaDto = new RespuestaDTO(movimientoDTO, dtoControl.obtenerIndicadorTurno());
            return respuestaDto;
        }else if(mensajeEntrante == MensajeSockets.VOTO){
            return MensajeSockets.VOTO;
        }else if(mensajeEntrante == MensajeSockets.TURNO_TERMINADO){
            return dtoControl.obtenerTurnoSiguiente();
        } else if(mensajeEntrante == MensajeSockets.MARCADOR){
            return dtoControl.obtenerIndicadorTurno();
        }
        
        return null;
    }
    
    public Object empezarParida(List<JugadorDTO> jugadoresDTO){
        iSala.crearSala(jugadoresDTO);
        return dtoControl.obtenerIndicadorTurno();
    }
}
