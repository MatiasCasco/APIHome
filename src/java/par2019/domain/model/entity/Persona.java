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
public class Persona extends BaseEntity<Integer> {
    private String apellido;
    private String email;
    private int rol;
    private String descripcion;
    private String password;
    private String loginName;
  

    public Persona(Integer id, String nombre) {
        super(0, "");
    }

    public Persona(String apellido, String email, int rol, String descripcion, String password, String loginName, Integer id, String nombre) {
        super(id, nombre);
        this.apellido = apellido;
        this.email = email;
        this.rol = rol;
        this.descripcion = descripcion;
        this.password = password;
        this.loginName = loginName;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return new StringBuilder("{id: ").append(id)
                .append(", nombre: ").append(nombre)
                .append(", apellido: ").append(apellido)
                .append(", email: ").append(email)
                .append(", rol: ").append(rol)
                .append(", descripcion: ").append(descripcion)
                .append(", passwd: ").append(password)
                .append(", loginName: ").append(loginName).append("}").toString();
                
    }
    
    
}
