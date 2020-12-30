/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.model.entity;

/**
 *
 * @author Matias
 */
public class Cuestionario {
    private int idCuestionario;
    private int idMateria;
    private String fechaCierre;
    private String fechaInicio;
    private int puntos;
    private String tiempoLimite;

    public Cuestionario() {
    }

    public Cuestionario(int idCuestionario, int idMateria, String fechaCierre, String fechaInicio, int puntos, String tiempoLimite) {
        this.idCuestionario = idCuestionario;
        this.idMateria = idMateria;
        this.fechaCierre = fechaCierre;
        this.fechaInicio = fechaInicio;
        this.puntos = puntos;
        this.tiempoLimite = tiempoLimite;
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }


    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getTiempoLimite() {
        return tiempoLimite;
    }

    public void setTiempoLimite(String tiempoLimite) {
        this.tiempoLimite = tiempoLimite;
    }

    

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        //return "Cuestionario{" + "id=" + id + ", materia=" + materia + ", fechaFin=" + fechaFin + ", fechaInicio=" + fechaInicio + ", puntos=" + puntos + ", limite=" + limite + '}';
        return new StringBuilder("{idcuestionario: ").append(idCuestionario)
                .append(", idmateria: ").append(idMateria)
                .append(", fechacierre: ").append(fechaCierre)
                .append(", fechainicio: ").append(fechaInicio)
                .append(", puntos: ").append(puntos)
                .append(", tiempolimite:").append(tiempoLimite)
                .append("}").toString();                
    }
   
} 
