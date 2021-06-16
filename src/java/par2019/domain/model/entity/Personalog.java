package par2019.domain.model.entity;

/**
 *
 * @author Alexis Delgado
 */
public class Personalog extends BaseEntity<Integer> {
    private String nombre;
    private String  apellido;
    private String email;
    private int rol;
    private String descripcion;
    private String password;
    private Integer idCurso;
    private String loginName;
    private String NombreCurso;

    /**
     *
     */
   
     public Personalog(Integer id, String nombre) {
        super(id, nombre);
    }

    public Personalog() {
         super(1, "nombre");
    }

    public Personalog(String nombre, String apellido, String email, int rol, String descripcion, String password, Integer id, String loginName) {
        super(id, loginName);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.rol = rol;
        this.descripcion = descripcion;
        this.password = password;
    }
    //loginName o email...
    public Personalog(  Integer id, String email, String password, int rol) {
        super(id, "");
        this.email=email;
        this.password = password;
        this.rol = rol;     
    }
    public Personalog(  Integer id, String email, String password, int rol,String NombreCurso) {
        super(id, "");
        this.email=email;
        this.password = password;
        this.rol = rol;
        this.NombreCurso=NombreCurso;
        
    }

    public String getNombreCurso() {
        return NombreCurso;
    }

    public void setNombreCurso(String NombreCurso) {
        this.NombreCurso = NombreCurso;
    }
 
    
    
    
    
    
    

    public Personalog(String nombre, String apellido, String email, int rol, String descripcion, String password, Integer id, String loginName,Integer idCurso) {
        super(id, loginName);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.rol = rol;
        this.descripcion = descripcion;
        this.password = password;
        this.idCurso = idCurso;
    }

  

   
            
 
      
   
   
//////Getters

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public int getRol() {
        return rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getIdCurso() {
        return idCurso;
    }


    
    
    
    
///setters

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

  
   
  

    
    
    

   
    

    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
       

        return new StringBuilder("{id: ").append(id)
                .append(", nombre: ").append(nombre)
                .append(", apellido: ").append(apellido)
                .append(", email: ").append(email)
                .append(", rol: ").append(rol)
                .append(", descripcion: ").append(descripcion)
                .append(", passwd: ").append(password)
                .append(", loginName: ").append(loginName)
                .append(",idCurso: ").append(idCurso).append("}").toString();
    }
}
