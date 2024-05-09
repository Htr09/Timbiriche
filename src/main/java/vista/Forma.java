/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import modelo.Figura;

/**
 *
 * @author xxbry
 */
public class Forma {
    private Figura figura;
    private Color color;
    private Polygon shape;
    
    public Forma(Figura figura, Color color){
        this.color = color;
        this.figura = figura;
        this.shape = new Polygon();
        
        this.shape.addPoint((int) -figura.getAncho()/2, (int) -figura.getAltura()/2);
        this.shape.addPoint((int) -figura.getAncho()/2, (int) figura.getAltura()/2);
        this.shape.addPoint((int) figura.getAncho()/2, (int) figura.getAltura()/2);
        this.shape.addPoint((int) figura.getAncho()/2, (int) -figura.getAltura()/2);
    }
    
    public void renderizar(Graphics g){
        g.setColor(color);
        
        Polygon rendered = new Polygon();
        for (int i = 0; i < shape.npoints; i++) {
            int renderedx = (int) (shape.xpoints[i] + figura.getX() + figura.getAncho()/ 2);
            int renderedy = (int) (shape.ypoints[i] + figura.getY() + figura.getAltura()/ 2);
            rendered.addPoint(renderedx, renderedy);
        }
        
        g.fillPolygon(rendered);
    }
    
    public boolean tienePuntos(int x, int y){
        return shape.contains(x - this.figura.getX() - this.figura.getAncho()/ 2,
                y - this.figura.getY() - this.figura.getAltura()/ 2);
    }

    public Figura getFigura() {
        return figura;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Polygon getShape() {
        return shape;
    }
}
