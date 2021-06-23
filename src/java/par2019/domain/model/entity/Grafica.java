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
public class Grafica {
    private String name;
    private int y;
    private String drilldown;

    public Grafica(String name, int y, String drilldown) {
        this.name = name;
        this.y = y;
        this.drilldown = drilldown;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDrilldown() {
        return drilldown;
    }

    public void setDrilldown(String drilldown) {
        this.drilldown = drilldown;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
     @Override
    public String toString() {
        return new StringBuilder("{name: ").append(name)
                .append(", y: ").append(y)
                .append(", drilldown: ").append(drilldown)
                .append("}").toString();
    }   
}
