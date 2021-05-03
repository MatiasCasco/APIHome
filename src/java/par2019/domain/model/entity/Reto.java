package par2019.domain.model.entity;

import java.util.List;

public class Reto {
    private int id;
    private int answer;
    private String question;
    private String image;
    private List<String> options;


    
    public Reto(int id, int answer, String question, String image,List<String> options) {
        this.id = id;
        this.answer = answer;
        this.question = question;
        this.image = image;
        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
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

//    public String[] getOptions() {
//        return options;
//    }
//
//    public void setOptions(String[] options) {
//        this.options = options;
//    }
    
    @Override
    public String toString() {
        //return "Curso{" + "idProfesr=" + idProfesr + ", claveProfesor=" + claveProfesor + ", claveAlumno=" + claveAlumno + '}';
        return new StringBuilder("{id: ").append(id)
                .append(", question: ").append(question)
                .append(", image: ").append(image)
                .append(", options: ").append(options)
                .append(", answer_index: ").append(answer)
                .append("}").toString();                
    }
}
