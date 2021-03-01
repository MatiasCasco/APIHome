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
public class Respuesta {
    private int idCuestionario;
    private int idRta;
    private int idPregunta;
    private String pregunta;
    private String respuesta;
    private Boolean evaluacion;

    public Respuesta() {
    }

    public Respuesta(int idCuestionario, int idRta, int idPregunta, String pregunta,String respuesta, Boolean evaluacion) {
        this.idCuestionario = idCuestionario;
        this.idRta = idRta;
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.evaluacion = evaluacion;
    }
    
    public String getPregunta() {
        return pregunta;
    }

//    public Respuesta(int idCuestionario, int idPregunta, String respuesta) {
//        this.idCuestionario = idCuestionario;
//        this.idPregunta = idPregunta;
//        this.respuesta = respuesta;
//    }
// 
//    public Respuesta(int idRta, int idPregunta, String respuesta, Boolean evaluacion) {
//        this.idRta = idRta;
//        this.idPregunta = idPregunta;
//        this.respuesta = respuesta;
//        this.evaluacion = evaluacion;
//    }
//
    public void setPregunta(String pregunta) {    
        this.pregunta = pregunta;
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public int getIdRta() {
        return idRta;
    }

    public void setIdRta(int idRta) {
        this.idRta = idRta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Boolean getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Boolean evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        // return "Respuesta{" + "idRta=" + idRta + ", idPregunta=" + idPregunta + ", rtas=" + rtas + ", evaluacion=" + evaluacion + '}';
        return new StringBuilder("{idRta: ").append(idRta)
                .append(", idpregunta: ").append(idPregunta)
                .append(", pregunta: ").append(pregunta)
                .append(", respuesta: ").append(respuesta)
                .append(", evaluacion: ").append(evaluacion)
                .append("}").toString();
    }
    
    
}
