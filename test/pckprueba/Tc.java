/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckprueba;
import java.util.Date;

/**
 *
 * @author Alexis
 */
public class Tc {
     private Date fecha;

        public Tc(Date fecha) {
            this.fecha = fecha;
        }

    @Override
    public String toString() {
        return "Tc{" + "fecha=" + fecha.toString() + '}';
    }
    
}
