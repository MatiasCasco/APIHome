package par2019.domain.model.entity;

/**
 *
 * @author Sourabh Sharma
 * @param <T>
 */
public abstract class BaseEntity<T> extends Entity<T> {

    private boolean isModified;

    /**
     *
     */
    public String nombre;

    public BaseEntity() {
    }

    /**
     *
     * @param id
     * @param nombre
     */
    public BaseEntity(T id, String nombre) {
        super.id = id;
        this.nombre = nombre;
        isModified = false;
    }

    /**
     *
     * @return
     */
    public boolean isIsModified() {
        return isModified;
    }
    
    
    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
