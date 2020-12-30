/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.repository;
import java.util.Collection;
import par2019.domain.model.entity.Entity;

/**
 * @author Matias
 * @param <Curso>
 * @param <Integer>
 */
public interface CursoRepository<Curso, Integer> extends Repository<Curso, Integer>{
    
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
     * @param id
     * @return 
     * @throws Exception
     */
    public Curso findByIdCurso(int id) throws Exception;
    
    /**
     * Refiere a curso
     * @param Id
     * @return
     */
    public boolean containsId(int Id);
    
    /**
     *
     * @param Id
     * @return
     */
    public boolean containsNameProfesor(String Name);
    
    /**
     *
     * @param Curso
     * @return
     */
    public boolean containsNameCurso(String Curso);
    
}
