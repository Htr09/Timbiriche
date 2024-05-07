/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.Cuadro;
import modelo.IndicadorTurno;
import modelo.Jugador;
import modelo.Linea;
import modelodto.CuadroDTO;
import modelodto.IndicadorTurnoDTO;
import modelodto.JugadorDTO;
import modelodto.LineaDTO;

/**
 *
 * @author icedo
 */
public class DTOControlador {
    
    private JuegoControlador jc = JuegoControlador.getInstance();
    
    
    public IndicadorTurnoDTO obtenerIndicadorTurno() {
        IndicadorTurno indicadorTurno = jc.obtenerIndicadorTurno();

        List<JugadorDTO> jugadoresDTO = new ArrayList<>();
        for (Jugador jugador : indicadorTurno.getJugadores()) {
            jugadoresDTO.add(new JugadorDTO(jugador.getNombre(), jugador.getAvatar(), jugador.getPuntaje()));
        }
        IndicadorTurnoDTO indicadorTurnoDTO = new IndicadorTurnoDTO(jugadoresDTO);

        return indicadorTurnoDTO;
    }

    
    public LineaDTO obtenerUltimaLinea() {
        Linea linea = jc.obtenerUltimaLinea();
        if (linea != null) {
            LineaDTO lineaDTO
                    = new LineaDTO(
                            linea.getPosicion().toString(),
                            linea.getIndice(),
                            new JugadorDTO(
                                    linea.getJugador().getNombre(),
                                    linea.getJugador().getAvatar(),
                                    linea.getJugador().getPuntaje()));
            return lineaDTO;
        }else{
            return null;
        }
    }

   
    public CuadroDTO obtenerUltimoCuadro() {
        Cuadro cuadro = jc.obtenerUltimoCuadro();
        CuadroDTO cuadroDTO
                = new CuadroDTO(cuadro.getIndice(),
                        new JugadorDTO(cuadro.getJugador().getNombre(),
                                cuadro.getJugador().getAvatar(),
                                cuadro.getJugador().getPuntaje()));

        return cuadroDTO;
    }

    
    public int obtenerTurnoSiguiente() {
        return jc.obtenerTurnoSiguiente();
    }
}
