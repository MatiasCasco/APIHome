/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Cuestionario;

/**
 *
 * @author Matias
 */
public interface CuestionarioService {
     /**
     *
     * @param cuestionario
     * @throws Exception
     */
    public void insert(Cuestionario cuestionario) throws Exception;

    /**
     *
     * @param cuestionario
     * @throws Exception
     */
    public void update(Cuestionario cuestionario) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;
    
    /**
     *  Criteria representa encontrar curso del profesor
     * @param NameMateria
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByNameMateria(String NameMateria) throws Exception;
 
    /**
     * Buscar el curso por su nombre
     * @param idCuestionario
     * @return 
     * @throws Exception
     */
    public Cuestionario getCuestionario(int idCuestionario) throws Exception;

}
