/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Juego;
import vista.JuegoVista;

/**
 *
 * @author icedo
 */
public class JuegoControlador {
    JuegoVista vista;
    Juego modelo;
    
    
    public void ActualizarVista(){
    vista.ActualizarJuego(); 
    }
    
    
}
