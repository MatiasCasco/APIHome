package par2019.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import par2019.domain.model.entity.Entity;
import par2019.domain.model.entity.User;

/**
 *
 * @author Sourabh Sharma
 */
public interface UserService {

    /**
     *
     * @param user
     * @throws Exception
     */
    public void add(User user) throws Exception;

    /**
     *
     * @param user
     * @throws Exception
     */
    public void update(User user) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Entity findById(Integer id) throws Exception;

    /**
     *
     * @param nombre
     * @param apellido
     * @return
     * @throws Exception
     */
    public Collection<User> findByNombreApellido(String nombre, String apellido) throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
