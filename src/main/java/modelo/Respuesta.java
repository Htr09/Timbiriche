/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author icedo
 */
public class Respuesta {
    private Movimiento movimiento;
    private IndicadorTurno IndicadorTurno;

   

    public Respuesta(Movimiento movimiento, IndicadorTurno IndicadorTurno) {
        this.movimiento = movimiento;
        this.IndicadorTurno = IndicadorTurno;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public IndicadorTurno getIndicadorTurno() {
        return IndicadorTurno;
    }

    public void setIndicadorTurno(IndicadorTurno IndicadorTurno) {
        this.IndicadorTurno = IndicadorTurno;
    }

    
}
