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
public class Materia {
    private int idMateria;
    private String nombre;
    private int idCurso;
    private String nombreCurso;
    

    public Materia() {
    }

    public Materia(int idMateria,  String nombre, int idCurso, String nombreCurso) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    
    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return new StringBuilder("{idmateria: ").append(idMateria)
                .append(", nombremateria: ").append(nombre)
                .append(", idcurso: ").append(idCurso)
                .append(", nombrecurso: ").append(nombreCurso)
                .append("}").toString();
    }
    
    
}
