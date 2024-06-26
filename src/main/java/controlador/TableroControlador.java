/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import modelo.Cuadro;
import modelo.Jugador;
import modelo.Linea;
import modelo.Posicion;
import modelo.Punto;
import vista.Forma;

/**
 *
 * @author xxbry
 */
public class TableroControlador {
    
    public static List<Punto> creaPuntos(double height, double width,
            double tamanio, double espaciado) {
        List<Punto> puntos = new ArrayList<>();
        // Crea los puntos del tablero y los guarda en el arrayList
        // Empieza 3% del tamano total a la derecha y abajo, 
        // en la esquina superior izquierda y termina dejando un margen de 3%
        // Doble for, primero para vertical y luego para horizontal
        for (double i = (height * .03); i <= (height * .97); i += espaciado) {
            for (double j = (width * .03); j <= (width * .97); j += espaciado) {
                //Crea y guarda el punto
                puntos.add(new Punto((int) tamanio, (int) tamanio, (int) i, (int) j));
            }
        }

        return puntos;
    }
    
    public static List<Linea> creaLineasHorizontales(double height, double width,
            double tamanio, double espaciado, int qtyPuntos) {
        List<Linea> lineasHorizontales = new ArrayList<>();
        //Se crean n matrices de n - 1 lineas
        //Donde n es igual al ancho/alto del tablero
        //El cual se determina segun el numero de jugadores (size)

        double altura = (height * .03);
        for (int i = 0; i < qtyPuntos; i++) {
            for (double j = (width * .03); j < ((width * .97) - espaciado);
                    j += espaciado) {
                lineasHorizontales.add(
                        new Linea(Posicion.HORIZONTAL, 
                                lineasHorizontales.size(), 
                                (int) (espaciado - tamanio),
                                (int) tamanio, 
                                (int) (j + tamanio), 
                                (int) altura));
            }
            altura += espaciado;
        }

        return lineasHorizontales;
    }
    
    public static List<Linea> creaLineasVerticales(double height,
            double width, double tamanio, double espaciado, int qtyPuntos) {
        List<Linea> lineasVerticales = new ArrayList<>();
        double anchura = (width * .03);
        for (int i = 0; i < qtyPuntos; i++) {
            for (double j = (height * .03); j < ((height * .97) - espaciado);
                    j += espaciado) {
                lineasVerticales.add(
                        new Linea(Posicion.VERTICAL, 
                                lineasVerticales.size(), 
                                (int) tamanio,
                                (int) (espaciado - tamanio), 
                                (int) anchura, 
                                (int) (j + tamanio)));
            }
            anchura += espaciado;
        }
        return lineasVerticales;
    }
    
    public static List<Cuadro> creaCuadros(List<Linea> lineasHorizontales, 
            List<Linea> lineasVerticales, double tamanio, double espaciado, int cantidad){
        List<Cuadro> cuadros = new ArrayList<>();
        
        for (int i = 0; i < cantidad - 1; i++) {
            int indicador = i;
            for (int j = i*(cantidad - 1); j < i*(cantidad - 1) + (cantidad - 1); j++) {  
                cuadros.add(new Cuadro(lineasHorizontales.get(j),
                    lineasHorizontales.get(j + (cantidad-1)), 
                    lineasVerticales.get(indicador), 
                    lineasVerticales.get(indicador + (cantidad - 1)), 
                        null, cuadros.size(),
                    (int)(espaciado - tamanio), (int)(espaciado-tamanio), 
                    (int)(lineasHorizontales.get(j).getX()), 
                    (int)(lineasHorizontales.get(j).getY() + tamanio)));
                
                indicador += (cantidad - 1);
                
            }
        }
        
        return cuadros;
    }
    
    public static double obtenerEspaciadoDePuntos(int cantidadDePuntos,
            double height) {
        // Para saber el espaciadoDePuntos entre los puntos a dibujar
        // En un tablero de 10x10, es el 92% de la altura dividido entre 9
        // En un tablero de 20x20, es el 92% dividido entre 19
        // En un tablero de 40x40, es el 92% dividido entre 39

        switch (cantidadDePuntos) {
            case 10:
                return (height * .92) / 9;
            case 20:
                return (height * .92) / 19;
            case 40:
                return (height * .92) / 39;
            default:
                return 0;
        }
    }
    
    public static Linea buscarLinea(int locateX, int locateY,
            List<Linea> lineasHorizontales, List<Linea> lineasVerticales,
            Jugador jugador, double espaciado, double tamanio) {
        for (int i = 0; i < (lineasHorizontales.size()
                + lineasVerticales.size()) / 2; i++) {

            Linea lineaVertical = lineasVerticales.get(i);
            Linea lineaHorizontal = lineasHorizontales.get(i);

            Color color = Color.decode(jugador.getPreferenciaColor()
                    .getColores().get(0));

            Forma formaVer = new Forma(lineaVertical, color);
            Forma formaHor = new Forma(lineaHorizontal, color);

            if (estaDentroPoligono(formaHor, locateX, locateY,
                    espaciado, tamanio)) {
                return lineaHorizontal;
            }

            if (estaDentroPoligono(formaVer, locateX, locateY,
                    espaciado, tamanio)) {
                return lineaVertical;
            }
        }

        return null;
    }
    
    private static boolean estaDentroPoligono(Forma forma, int locateX,
            int locateY, double espaciado, double tamanio) {
        int x = forma.getFigura().getX();
        int y = forma.getFigura().getY();

        int x2 = 0;
        int y2 = 0;
        
        if (forma.getFigura().getAltura()< forma.getFigura().getAncho()) {
            x2 = forma.getFigura().getX() + (int) espaciado;
            y2 = forma.getFigura().getY() + (int) tamanio;
        } else {
            x2 = forma.getFigura().getX() + (int) tamanio;
            y2 = forma.getFigura().getY() + (int) espaciado;
        }

        return ((locateX > x && locateX < x2) && (locateY > y && locateY < y2));
    }
    
    public static List<Cuadro> verificarMovimiento(List<Cuadro> cuadros, Linea linea, Jugador jugador) {
        List<Cuadro> cuadrosTemporal = new ArrayList<>();

        for (Cuadro cuadro : cuadros) {
            if (cuadro.tieneLinea(linea)) {
                if(cuadro.getJugador() == null){
                    cuadrosTemporal.add(cuadro);
                }
            }
        }

        for (Cuadro cuadro : cuadrosTemporal) {
            if (cuadro.esCuadrado()) {
                cuadro.setJugador(jugador);
            }
        }
        
        return cuadrosTemporal;
    }
    
}
