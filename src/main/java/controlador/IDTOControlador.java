/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador;

import modelodto.CuadroDTO;
import modelodto.IndicadorTurnoDTO;
import modelodto.LineaDTO;

/**
 *
 * @author PC
 */
public interface IDTOControlador {
    IndicadorTurnoDTO obtenerIndicadorTurno();
    LineaDTO obtenerUltimaLinea();
    CuadroDTO obtenerUltimoCuadro();
    int obtenerTurnoSiguiente();
}
