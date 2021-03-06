/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.model.entity;

import java.util.List;

/**
 *
 * @author User
 */
public class Test {
    private int id;
    private List<Integer> answer;
    private int idquestion;
    private String question;
    private int assignedscore;
    private int puntoobtenido;
    private String image;
    private List<String> options;
    private List<Integer> listidR;
    private List<Integer> marcadas;
    
    public Test(int id, List<Integer> answer, int idquestion, String question, int assignedscore, String image, List<String> options, List<Integer> listidR) {
        this.id = id;
        this.answer = answer;
        this.idquestion = idquestion;
        this.question = question;
        this.assignedscore = assignedscore;
        this.image = image;
        this.options = options;
        this.listidR = listidR;
    }

    public Test(int id, List<Integer> answer, int idquestion, String question, int assignedscore, int puntoobtenido, String image, List<String> options, List<Integer> listidR, List<Integer> marcadas) {
        this.id = id;
        this.answer = answer;
        this.idquestion = idquestion;
        this.question = question;
        this.assignedscore = assignedscore;
        this.puntoobtenido = puntoobtenido;
        this.image = image;
        this.options = options;
        this.listidR = listidR;
        this.marcadas = marcadas;
    }

    public Test(int idquestion, int puntoobtenido, List<Integer> listidR) {
        this.idquestion = idquestion;
        this.puntoobtenido = puntoobtenido;
        this.listidR = listidR;
    }
    
    public int getPuntoobtenido() {
        return puntoobtenido;
    }

    public void setPuntoobtenido(int puntoobtenido) {
        this.puntoobtenido = puntoobtenido;
    }

    public List<Integer> getMarcadas() {
        return marcadas;
    }

    public void setMarcadas(List<Integer> marcadas) {
        this.marcadas = marcadas;
    }
    
    public int getIdquestion() {
        return idquestion;
    }

    public void setIdquestion(int idquestion) {
        this.idquestion = idquestion;
    }

    public List<Integer> getListidR() {
        return listidR;
    }

    public void setListidR(List<Integer> listidR) {
        this.listidR = listidR;
    }
    
    public int getAssignedscore() {
        return assignedscore;
    }

    public void setAssignedscore(int assignedscore) {
        this.assignedscore = assignedscore;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
    
    @Override
    public String toString() {
        //return "Curso{" + "idProfesr=" + idProfesr + ", claveProfesor=" + claveProfesor + ", claveAlumno=" + claveAlumno + '}';
        return new StringBuilder("{id: ").append(id)
                .append(", idquestion: ").append(idquestion)
                .append(", question: ").append(question)
                .append(", assignedscore: ").append(assignedscore)
                .append(", puntosobtenidos: ").append(puntoobtenido)
                .append(", image: ").append(image)
                .append(", options: ").append(options)
                .append(", listaidR: ").append(listidR)
                .append(", answer_index: ").append(answer)
                .append(", seleccionadas: ").append(marcadas)
                .append("}").toString();                
    }
}
