/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.model.entity;

/**
 *
 * @author User
 */
public class reporte {
    private int n;
    private String nombre;
    private int TP;
    private int PL;

    public reporte() {
    }

    public reporte(int n, String nombre, int TP, int PL) {
        this.n = n;
        this.nombre = nombre;
        this.TP = TP;
        this.PL = PL;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTP() {
        return TP;
    }

    public void setTP(int TP) {
        this.TP = TP;
    }

    public int getPL() {
        return PL;
    }

    public void setPL(int PL) {
        this.PL = PL;
    }
    
    @Override
    public String toString() {
        return new StringBuilder("{Nro Alumno: ").append(n)
                .append(", Nombre y Apellido: ").append(nombre)
                .append(", Total de puntos: ").append(TP)
                .append(", Puntos logrados: ").append(PL)
                .append("}").toString();
    }
}
