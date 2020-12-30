package par2019.domain.model.entity;

/**
 *
 * @author Sourabh Sharma
 * @param <T>
 */
public abstract class BaseEntitytd<T> extends Entity<T> {

    private boolean isModified;

    /**
     *
     */
    public int item;

    /**
     *
     * @param id
     * @param nombre
     */
    public BaseEntitytd(T id, int item) {
        super.id = id;
        this.item = item;
        isModified = false;
    }

    /**
     *
     * @return
     */
    public boolean isIsModified() {
        return isModified;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }
    
    
  

}
