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
public class Ranking implements Comparable<Ranking>{
    private int id;
    private String nombre;
    private String apellido;
    private int puntos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int puntosMateria;
    private int puntosObtenido;
    
     public Ranking(int id,String nombre, String apellido, int puntos) {
        this.id=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntos = puntos;
    }

    public Ranking(String nombre, String apellido, int puntos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntos = puntos;
    }

    public Ranking(String nombre, String apellido, int puntosMateria, int puntosObtenido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntosMateria = puntosMateria;
        this.puntosObtenido = puntosObtenido;
    }

    public int getPuntosMateria() {
        return puntosMateria;
    }

    public void setPuntosMateria(int puntosMateria) {
        this.puntosMateria = puntosMateria;
    }

    public int getPuntosObtenido() {
        return puntosObtenido;
    }

    public void setPuntosObtenido(int puntosObtenido) {
        this.puntosObtenido = puntosObtenido;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
        return new StringBuilder("{nombre: ").append(nombre)
                .append(", apellido: ").append(apellido)
                .append(", puntaje: ").append(puntos)
                .append(", puntajeMateria: ").append(puntosMateria)
                .append(", puntajeObtenido: ").append(puntosObtenido)
                .append("}").toString();                
    }

    @Override
    public int compareTo(Ranking R) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(this.puntosObtenido < R.getPuntosObtenido()){
            return -1;
        }else {
            if (this.puntosObtenido > R.getPuntosObtenido()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
