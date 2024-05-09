/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketCliente;

import java.io.IOException;
import java.util.List;
import modelo.Cuadro;
import modelo.FiguraJuego;
import modelo.Jugador;
import modelo.Linea;
import modelodto.CuadroDTO;
import modelodto.JugadorDTO;
import modelodto.LineaDTO;
import modelodto.MensajeSockets;
import modelodto.MovimientoDTO;
import vista.IActualizable;

/**
 *
 * @author PC
 */


public class Cliente implements ICliente {

    private static Cliente instance;
    private SckCliente sckCliente;
    
    public Cliente(Jugador jugador, IActualizable actualizable) {
        this.sckCliente = SckCliente.getInstance(jugador, actualizable);
    }
    
    
    @Override
    public boolean conectarAlServidor(String address, int port) {
        try {
            sckCliente.conectarAlServidor(address, port);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean enviarAlServidor(Object mensaje) {
        try {
            if (mensaje instanceof Jugador) {
                Jugador jugador = (Jugador) mensaje;
                JugadorDTO mensajeNuevo = new JugadorDTO(jugador.getNombre(), jugador.getAvatar(), jugador.getPuntaje());
                sckCliente.enviarAlServidor(mensajeNuevo);
                return true;
            } else if (mensaje instanceof List) {
                MovimientoDTO movimiento = new MovimientoDTO();
                List<FiguraJuego> formas = (List<FiguraJuego>) mensaje;

                for (int i = 0; i < formas.size(); i++) {
                    if (i == 0) {
                        Linea linea = (Linea) formas.get(i);
                        LineaDTO formaNueva
                                = new LineaDTO(
                                        linea.getPosicion().toString(),
                                        linea.getIndice(),
                                        new JugadorDTO(
                                                linea.getJugador().getNombre(),
                                                linea.getJugador().getAvatar(),
                                                linea.getJugador().getPuntaje()));
                        movimiento.setLinea(formaNueva);
                    } else {
                        Cuadro cuadro = (Cuadro) formas.get(i);
                        CuadroDTO formaNueva
                                = new CuadroDTO(
                                        cuadro.getIndice(),
                                        new JugadorDTO(
                                                cuadro.getJugador().getNombre(),
                                                cuadro.getJugador().getAvatar(),
                                                cuadro.getJugador().getPuntaje()));
                        movimiento.setCuadro(formaNueva);
                    }
                }

                sckCliente.enviarAlServidor(movimiento);
                return true;
            } else if (mensaje instanceof String) {
                sckCliente.enviarAlServidor(mensaje);
                return true;
            } else if (mensaje instanceof MensajeSockets) {
                sckCliente.enviarAlServidor(mensaje);
                return true;
            }
            return false;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public void escucharAlServidor() {
        try {
            sckCliente.escucharAlServidor();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Problemas al recibir la respuesta del servidor");
        }
    }

   
}
