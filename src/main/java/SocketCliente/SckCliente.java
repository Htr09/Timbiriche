/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketCliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import modelo.Cuadro;
import modelo.FiguraJuego;
import modelo.IndicadorTurno;
import modelo.Jugador;
import modelo.Linea;
import modelo.Posicion;
import modelodto.CuadroDTO;
import modelodto.IndicadorTurnoDTO;
import modelodto.JugadorDTO;
import modelodto.LineaDTO;
import modelodto.MovimientoDTO;
import modelodto.RespuestaDTO;
import vista.IActualizable;

/**
 *
 * @author PC
 */
public class SckCliente implements Runnable {

    private Jugador jugador;
    private Socket socket;
    private ObjectInputStream clientInput;
    private ObjectOutputStream clientOutput;
    private IActualizable actualizable;
    private Object objeto;
    private static SckCliente instance;
    
    private SckCliente(Jugador jugador, IActualizable actualizable) {
        this.jugador = jugador;
        this.actualizable = actualizable;
    }
    
    public static SckCliente getInstance(Jugador jugador, IActualizable actualizable) {
        if (instance == null) {
            instance = new SckCliente(jugador, actualizable);
        } else {
            instance.actualizable = actualizable;
        }
        return instance;
    }
    
    public synchronized void conectarAlServidor(String address, int port) throws IOException {
        socket = new Socket(address, port);
        clientOutput = new ObjectOutputStream(socket.getOutputStream());
        clientOutput.flush();
        clientInput = new ObjectInputStream(socket.getInputStream());
    }
    
    public synchronized void enviarAlServidor(Object mensaje) throws IOException {
        clientOutput.writeObject(mensaje);
        clientOutput.flush();
    }

    public synchronized void escucharAlServidor() throws IOException, ClassNotFoundException {
        Thread t = new Thread(this);
        t.start();
    }
    
    
    @Override
    public void run() {
        while (true) {
            try {
                objeto = clientInput.readObject();

                if (objeto instanceof List) {
                    List<JugadorDTO> jugadoresDTO = (List<JugadorDTO>) objeto;
                    List<Jugador> jugadores = new ArrayList<>();

                    for (JugadorDTO jugador : jugadoresDTO) {
                        jugadores.add(new Jugador(jugador.getNombreJugador(), jugador.getAvatar()));
                    }

                    objeto = jugadores;
                } else if (objeto instanceof String) {
                    String string = (String) objeto;
                    objeto = string;
                } else if (objeto instanceof IndicadorTurnoDTO) {
                    IndicadorTurnoDTO indicadorTurnoDTO = (IndicadorTurnoDTO) objeto;
                    List<JugadorDTO> jugadoresDTO = indicadorTurnoDTO.getJugadores();
                    List<Jugador> jugadores = new ArrayList<>();

                    for (JugadorDTO jugador : jugadoresDTO) {
                        jugadores.add(new Jugador(jugador.getNombreJugador(), jugador.getAvatar(), jugador.getPuntaje()));
                    }

                    IndicadorTurno indicadorTurno = new IndicadorTurno(jugadores);

                    objeto = indicadorTurno;
                } else if (objeto instanceof RespuestaDTO) {
                    MovimientoDTO movimiento = ((RespuestaDTO) objeto).getMovimiento();
                    List<FiguraJuego> figuras = new ArrayList<>();

                    if (movimiento.getLinea() != null) {
                        LineaDTO lineaDTO = movimiento.getLinea();

                        Linea linea = new Linea(
                                Posicion.valueOf(lineaDTO.getPosicion()),
                                new Jugador(
                                        lineaDTO.getJugador().getNombreJugador(),
                                        lineaDTO.getJugador().getAvatar(),
                                        lineaDTO.getJugador().getPuntaje()),
                                lineaDTO.getIndice());

                        figuras.add(linea);
                    }

                    for (CuadroDTO cuadroDTO : movimiento.getCuadros()) {
                        Cuadro cuadro = new Cuadro(
                                new Jugador(
                                        cuadroDTO.getJugador().getNombreJugador(),
                                        cuadroDTO.getJugador().getAvatar(),
                                        cuadroDTO.getJugador().getPuntaje()),
                                cuadroDTO.getIndice());

                        figuras.add(cuadro);
                    }

                    IndicadorTurnoDTO indicadorTurnoDTO = ((RespuestaDTO) objeto).getIndicadorTurno();
                    System.out.println("IndicadorTurnoDTO " + indicadorTurnoDTO);
                    List<JugadorDTO> jugadoresDTO = indicadorTurnoDTO.getJugadores();
                    List<Jugador> jugadores = new ArrayList<>();

                    for (JugadorDTO jugador : jugadoresDTO) {
                        jugadores.add(new Jugador(jugador.getNombreJugador(), jugador.getAvatar(), jugador.getPuntaje()));
                    }

                    IndicadorTurno indicadorTurno = new IndicadorTurno(jugadores);
                    System.out.println(indicadorTurno);
                    objeto = indicadorTurno;

                    //Solo para enviar el marcador
                    mostrarCambios();

                    objeto = figuras;
                }

                mostrarCambios();

                System.out.println(objeto);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(SckCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private synchronized void mostrarCambios() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                actualizable.actualizaDeSocket(objeto);
            }
        });
    }
    
}
