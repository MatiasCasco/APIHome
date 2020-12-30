package par2019.domain.model.entity;

/**
 *
 * @author Sourabh Sharma
 * @param <T>
 */
public abstract class Entity<T> {

    T id;

    
 

    /**
     *
     * @return
     */
    public T getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(T id) {
        this.id = id;
    }


}
