package par2019.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import par2019.domain.model.entity.Entity;
import par2019.domain.model.entity.User;
import par2019.domain.repository.UserRepository;

/**
 *
 * @author Sourabh Sharma
 */
//@Service("userService")
public class UserServiceImpl extends BaseService<User, Integer>
        implements UserService {

    private UserRepository<User, Integer> userRepository;

    /**
     *
     * @param userRepository
     */
    //@Autowired
    public UserServiceImpl(UserRepository<User, Integer> userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) throws Exception {
        if (userRepository.containsNombreApellido(user.getNombre(), user.getApellido())) {
            throw new Exception(String.format("Ya existe un usuario con el nombre %s y el apellido %s", user.getNombre(), user.getApellido()));
        }
        
        if (userRepository.containsLoginName(user.getLoginName())) {
            throw new Exception(String.format("Ya existe un usuario con el login name %s", user.getLoginName()));
        }

        if (user.getNombre() == null || "".equals(user.getNombre())) {
            throw new Exception("El nombre del usuario no puede ser nulo o cadena vacia.");
        }
        
        if (user.getApellido() == null || "".equals(user.getApellido())) {
            throw new Exception("El apellido del usuario no puede ser nulo o cadena vacia.");
        }
        
        super.add(user);
    }

    /**
     *
     * @param nombre
     * @param apellido
     * @return
     * @throws Exception
     */
    @Override
    public Collection<User> findByNombreApellido(String nombre, String apellido) throws Exception {
        return userRepository.findByNombreApellido(nombre, apellido);
    }

    /**
     *
     * @param user
     * @throws Exception
     */
    @Override
    public void update(User user) throws Exception {
        userRepository.update(user);
    }

    /**
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(Integer id) throws Exception {
        userRepository.remove(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Entity findById(Integer id) throws Exception {
        return userRepository.get(id);
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
