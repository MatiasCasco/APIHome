/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Curso;
import par2019.domain.model.entity.Entity;

/**
 *
 * @author Matias
 */
public interface CursoService {
     /**
     *
     * @param curso
     * @throws Exception
     */
    public void insert(Curso curso) throws Exception;

    /**
     *
     * @param curso
     * @throws Exception
     */
    public void update(Curso curso) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;
    
    /**
     *  Criteria representa encontrar curso del profesor
     * @param Criteria
     * @return
     * @throws Exception
     */
    public Collection<Curso> findByCriteria(String Criteria) throws Exception;
 
    /**
     * Buscar el curso por su nombre
     * @param Name
     * @return 
     * @throws Exception
     */
    public Collection<Curso> findByNameCurso(String Name) throws Exception;

    /**
     * Buscar el curso por su nombre
     * @param Id
     * @return 
     * @throws Exception
     */
    public Curso findByIdCurso(int Id) throws Exception;
     /**
     * Buscar el curso por su nombre
     * @param Name
     * @return 
     * @throws Exception
     */
    public Collection<Curso> findByIdProfesor(int idProfesor) throws Exception;
}
