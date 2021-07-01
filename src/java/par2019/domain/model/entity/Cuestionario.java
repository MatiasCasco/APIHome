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
    private String descripcion;
    private int idMateria;
    private String nombreMateria;
    private String fechaCierre;
    private String fechaInicio;
    private int puntos;
    private String tiempoLimite;
    private int idCurso;
    private String nombreCurso;
    public Cuestionario() {
    }

    public Cuestionario(int idCuestionario, int idMateria, String nombreMateria, String fechaCierre, String fechaInicio, int puntos, String tiempoLimite, int idCurso, String nombreCurso) {
        this.idCuestionario = idCuestionario;
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.fechaCierre = fechaCierre;
        this.fechaInicio = fechaInicio;
        this.puntos = puntos;
        this.tiempoLimite = tiempoLimite;
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
    }
    
    public Cuestionario(int idCuestionario, String descripcion, int idMateria, String nombreMateria, String fechaCierre, String fechaInicio, int puntos, String tiempoLimite, int idCurso, String nombreCurso) {
        this.idCuestionario = idCuestionario;
        this.descripcion = descripcion;
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.fechaCierre = fechaCierre;
        this.fechaInicio = fechaInicio;
        this.puntos = puntos;
        this.tiempoLimite = tiempoLimite;
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
    }

//    public Cuestionario(int idCuestionario, int idMateria, String fechaCierre, String fechaInicio, int puntos, String tiempoLimite, int idCurso) {
//        this.idCuestionario = idCuestionario;
//        this.idMateria = idMateria;
//        this.fechaCierre = fechaCierre;
//        this.fechaInicio = fechaInicio;
//        this.puntos = puntos;
//        this.tiempoLimite = tiempoLimite;
//        this.idCurso = idCurso;
//    } 
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }  
      
    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
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

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
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
                .append(", descripcion: ").append(descripcion)
                .append(", idmateria: ").append(idMateria)
                .append(", nombremateria: ").append(nombreMateria)
                .append(", fechacierre: ").append(fechaCierre)
                .append(", fechainicio: ").append(fechaInicio)
                .append(", puntos: ").append(puntos)
                .append(", tiempolimite:").append(tiempoLimite)
                .append(", idcurso:").append(idCurso)
                .append(", nombrecurso:").append(nombreCurso)
                .append("}").toString();                
    }   
} 
