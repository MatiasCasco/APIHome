/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.repository;

import java.util.Collection;

/**
 *
 * @author Matias
 * @param <Cuestionario>
 * @param <Integer>
 */
public interface CuestionarioRepository<Cuestionario, Integer> extends Repository<Cuestionario, Integer> {
    
    /**
     * Trae una materia por su idMateria
     * @param idCuestionario
     * @return
     */
    public Cuestionario  getCuestionario(int idCuestionario);
    
    /**
     *
     * @param idCuestionario
     * @return
     * @throws Exception
     */
    public boolean ContainsIdCuestionario(int idCuestionario) throws Exception;
    
    /**
     *
     * @param idMateria
     * @return
     * @throws Exception
     */
    public boolean ContainsMateria(int idMateria) throws Exception;
    
    /**
     *
     * @param NameMateria
     * @return
     * @throws Exception
     */
    public boolean ContainsNameMateria(String NameMateria) throws Exception;

    
     /**
     *
     * @param NameMateria
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByNameMateria(String NameMateria) throws Exception;
}
