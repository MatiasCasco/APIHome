/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.model.entity;

/**
 *
 * @author HP
 */
public class EstadisticaPregunta {
    private int idPregunta;
    private String pregunta;
    private int prom;
    private int correcta;
    private int incorrecta;

    public EstadisticaPregunta() {
    }

    public EstadisticaPregunta(int idPregunta, String pregunta, int prom, int correcta, int incorrecta) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.prom = prom;
        this.correcta = correcta;
        this.incorrecta = incorrecta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getProm() {
        return prom;
    }

    public void setProm(int prom) {
        this.prom = prom;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }

    public int getIncorrecta() {
        return incorrecta;
    }

    public void setIncorrecta(int incorrecta) {
        this.incorrecta = incorrecta;
    }

    @Override
    public String toString() {
       // puntoAsignado=" + puntoAsignado + ", pregunta=" + pregunta + '}';
       return new StringBuilder("{idpregunta: ").append(idPregunta)
                .append(", pregunta: ").append(pregunta)
                .append(", promedio: ").append(prom)
                .append(", correcta:").append(correcta)
                .append(", incorrecta: ").append(incorrecta)
                .append("}").toString();
    }
}