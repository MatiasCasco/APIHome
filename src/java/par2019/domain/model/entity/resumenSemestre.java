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
public class resumenSemestre {
    private String materia;
    private int test; // test ya no uso
    private int promedio;
    private int mes;

    public resumenSemestre() {
    }
    
    public resumenSemestre(String materia, int test, int promedio, int mes) {
        this.materia = materia;
        this.test = test;
        this.promedio = promedio;
        this.mes = mes;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int getPromedio() {
        return promedio;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
    @Override
    public String toString() {
        return new StringBuilder("{materia: ").append(materia)
                .append(", test: ").append(test)
                .append(", promedio: ").append(promedio)
                .append(", mes: ").append(mes)
                .append("}").toString();
    } 
}
