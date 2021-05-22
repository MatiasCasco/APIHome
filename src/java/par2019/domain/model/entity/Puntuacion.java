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
public class Puntuacion { 
    private int idAlumno;
    private int idCuestionario;
    private int puntuacion;

    public Puntuacion() {
    }

    public Puntuacion(int idAlumno, int idCuestionario, int puntuacion) {
        this.idAlumno = idAlumno;
        this.idCuestionario = idCuestionario;
        this.puntuacion = puntuacion;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    @Override
    public String toString() {
        //return "Cuestionario{" + "id=" + id + ", materia=" + materia + ", fechaFin=" + fechaFin + ", fechaInicio=" + fechaInicio + ", puntos=" + puntos + ", limite=" + limite + '}';
        return new StringBuilder("{id: ").append(idAlumno)
                .append(", idcuestionario: ").append(idCuestionario)
                .append(", puntaje: ").append(puntuacion)
                .append("}").toString();                
    }
}
