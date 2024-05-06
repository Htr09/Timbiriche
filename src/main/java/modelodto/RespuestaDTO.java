/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelodto;

import java.io.Serializable;

/**
 *
 * @author icedo
 */
public class RespuestaDTO implements Serializable{
    private MovimientoDTO movimiento;
    private IndicadorTurnoDTO indicadorTurno;

    public RespuestaDTO(MovimientoDTO movimiento, IndicadorTurnoDTO indicadorTurno) {
        this.movimiento = movimiento;
        this.indicadorTurno = indicadorTurno;
    }

    public MovimientoDTO getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoDTO movimiento) {
        this.movimiento = movimiento;
    }

    public IndicadorTurnoDTO getIndicadorTurno() {
        return indicadorTurno;
    }

    public void setIndicadorTurno(IndicadorTurnoDTO indicadorTurno) {
        this.indicadorTurno = indicadorTurno;
    }

    
    
}
