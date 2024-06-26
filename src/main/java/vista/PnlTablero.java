/**
 * PnlTablero.java.
 */
package vista;

import controlador.TableroControlador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cuadro;
import modelo.FiguraJuego;
import modelo.Jugador;
import modelo.Linea;
import modelo.Posicion;
import modelo.Punto;
import modelo.Tablero;

/**
 *
 * @author xxbry
 */
public class PnlTablero extends javax.swing.JPanel implements PanelObservable, MouseListener, MouseMotionListener {

    private Tablero tablero;

    private Jugador jugador;

    private Linea lineaTemp = null;

    private List<PanelObservador> observadores;

    private boolean turno;

    /**
     * Crea el pnlTablero
     *
     * @param tablero
     * @param jugador
     */
    public PnlTablero(Tablero tablero, Jugador jugador) {
        initComponents();
        addMouseListener(this);
        addMouseMotionListener(this);
        this.tablero = tablero;
        this.jugador = jugador;
        this.observadores = new ArrayList<>();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setBackground(Color.BLACK);

        //Itera en todos los puntos del tablero y los dibuja
        for (Punto punto : this.tablero.getPuntos()) {
            Forma puntoPl = new Forma(punto, Color.BLACK);
            puntoPl.renderizar(g);
        }

        //itera en todas las lineas horizontales del tablero y las dibuja
        for (Linea lineaHorizontal : this.tablero.getLineasHorizontales()) {
            if (lineaHorizontal.getJugador() != null) {
                Forma puntoPl = new Forma(lineaHorizontal,
                        Color.decode(lineaHorizontal.getJugador().getColor()));
                puntoPl.renderizar(g);
            }
        }

        //itera en todas las lineas verticales del tablero y las dibuja
        for (Linea lineaVertical : this.tablero.getLineasVerticales()) {
            if (lineaVertical.getJugador() != null) {
                Forma puntoPl = new Forma(lineaVertical,
                        Color.decode(lineaVertical.getJugador().getColor()));
                puntoPl.renderizar(g);
            }
        }

        int tamanio = 200 / this.tablero.getDimension();
        //itera en todos los cuadrados del tablero y los dibuja
        for (Cuadro cuadro : this.tablero.getCuadros()) {
            if (cuadro.getJugador() != null) {
                Forma cuadroPl = new Forma(cuadro,
                        Color.decode(cuadro.getJugador().getColor()));
                cuadroPl.renderizar(g);

                g2.setFont(new Font("Arial", Font.BOLD, tamanio));
                g2.setColor(Color.black);
                g2.drawString(
                        String.valueOf(cuadro.obtenerInicial()).toUpperCase(),
                        cuadro.getX() + cuadro.getAncho() / 2,
                        cuadro.getY() + cuadro.getAltura() / 2);

            }
        }

        // FormaTemporal sirve para renderizar la vista previa de la linea
        // que el jugador puede colocar.
        if (lineaTemp != null) {
            Forma lineaTempPl = new Forma(lineaTemp,
                    Color.decode(this.jugador.getColor()));
            lineaTempPl.renderizar(g);
        }
    }

    /**
     * Metodo que establece las propiedades del tablero y sus Forma
     */
    public void estableceTablero() {
        int cantidad = this.tablero.getDimension();
        int tamanio = 200 / this.tablero.getDimension();

        double espaciado = TableroControlador.obtenerEspaciadoDePuntos(cantidad, this.getHeight());

        if (this.tablero.getPuntos().isEmpty()) {
            //Crea los puntos del tablero utilizando la altura, el ancho,
            //el espaciado que deben tener los puntos del tablero,
            //el tamanio seleccionado y la cantidad de puntos necesaria.
            this.tablero.setPuntos(
                    TableroControlador.creaPuntos(this.getHeight(), this.getWidth(),
                            tamanio, espaciado));

            //Crea las lineas horizontales del tablero.
            this.tablero.setLineasHorizontales(
                    TableroControlador.creaLineasHorizontales(this.getHeight(),
                            this.getWidth(), tamanio, espaciado, cantidad));

            //Crea las lineas verticales del tablero.
            this.tablero.setLineasVerticales(
                    TableroControlador.creaLineasVerticales(this.getHeight(),
                            this.getWidth(), tamanio, espaciado, cantidad));

            //Crea los cuadrados del tablero.
            this.tablero.setCuadros(
                    TableroControlador.creaCuadros(
                            tablero.getLineasHorizontales(),
                            tablero.getLineasVerticales(),
                            tamanio, espaciado, cantidad));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void mouseClicked(MouseEvent me) {
        int clickX = me.getX();
        int clickY = me.getY();

        System.out.println(clickX + "/" + clickY);

        int cantidad = this.tablero.getDimension();
        int tamanio = 200 / this.tablero.getDimension();
        double espaciado = TableroControlador.obtenerEspaciadoDePuntos(cantidad, this.getHeight());

        Linea lineaEncontrada = TableroControlador.buscarLinea(clickX, clickY,
                this.tablero.getLineasHorizontales(),
                this.tablero.getLineasVerticales(),
                jugador, espaciado, tamanio);

        if (lineaEncontrada != null) {
            if (lineaEncontrada.getJugador() != null) {
                JOptionPane.showMessageDialog(this, "Escoge una linea que no haya sido marcada anteriormente", "Error de juego", JOptionPane.INFORMATION_MESSAGE);
            } else {
                List<FiguraJuego> movimiento = new ArrayList<>();
                lineaEncontrada.setJugador(jugador);
                movimiento.add(lineaEncontrada);

                List<Cuadro> cuadros = TableroControlador.verificarMovimiento(this.tablero.getCuadros(), lineaEncontrada, jugador);

                for (Cuadro cuadro : cuadros) {
                    if (cuadro.getJugador() != null) {
                        movimiento.add(cuadro);
                    }
                }
                
                notificaMovimiento(movimiento);
            }
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        ////throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        mouseMoved(me);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        int mouseX = me.getX();
        int mouseY = me.getY();

        System.out.println(mouseX + "/" + mouseY);

        int cantidad = this.tablero.getDimension();
        int tamanio = 200 / this.tablero.getDimension();

        double espaciado = TableroControlador.obtenerEspaciadoDePuntos(cantidad, this.getHeight());

        lineaTemp = TableroControlador.buscarLinea(mouseX, mouseY,
                this.tablero.getLineasHorizontales(),
                this.tablero.getLineasVerticales(),
                jugador, espaciado, tamanio);

        repaint();
    }

    @Override
    public void agrega(PanelObservador observador) {
        this.observadores.add(observador);
    }

    public void actualizaLineaTablero(Linea linea) {
        if (linea.getPosicion() == Posicion.HORIZONTAL) {
            this.tablero.getLineasHorizontales().get(linea.getIndice()).setJugador(linea.getJugador());
        } else if (linea.getPosicion() == Posicion.VERTICAL) {
            this.tablero.getLineasVerticales().get(linea.getIndice()).setJugador(linea.getJugador());
        }
    }

    public void actualizaCuadroTablero(Cuadro cuadro) {
        System.out.println(cuadro);
        this.tablero.getCuadros().get(cuadro.getIndice()).setJugador(cuadro.getJugador());
    }

    public void actualizaTurno(boolean turno) {
        this.turno = turno;
        this.setVisible(turno);
    }

    @Override
    public void notificaMovimiento(List<FiguraJuego> movimiento) {
        for (PanelObservador observador : observadores) {
            observador.actualiza(movimiento);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
