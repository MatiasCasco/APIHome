package par2019.domain.valueobject;

/**
 *
 * @author Mauricio Machuca
 */
public class UserVO {

    private String nombre;
    private String id;
    private String apellido;
    private String email;
    private String loginName;
    private String passwd;
    private int tipoCliente;

    /**
     * Custom Constructor
     *
     * @param nombre
     * @param id
     * @param apellido
     * @param email
     * @param loginName
     * @param passwd
     * @param tipoCliente
     */
    public UserVO(String id, String nombre, String apellido, String email, String loginName, String passwd, int tipoCliente) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.loginName = loginName;
        this.passwd = passwd;
        this.tipoCliente = tipoCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    
    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
       return new StringBuilder("{id: ").append(id).append(", nombre: ")
                .append(nombre).append(", apellido: ").append(apellido)
                .append(", email: ").append(email)
                .append(", loginName: ").append(loginName)
                .append(", passwd: ").append(passwd)
                .append(", tipoCliente: ").append(tipoCliente).append("}").toString();
    }

    /**
     * Default Constructor
     */
    public UserVO() {
    }
}
