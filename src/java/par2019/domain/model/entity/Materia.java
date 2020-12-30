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
    private int idCurso;
    private String nombre;

    public Materia() {
    }

    public Materia(int idMateria, int idCurso, String nombre) {
        this.idMateria = idMateria;
        this.idCurso = idCurso;
        this.nombre = nombre;
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
                .append(", idcurso: ").append(idCurso)
                .append(", nombremateria: ").append(nombre).append("}").toString();
    }
    
    
}
