/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.repository;
import java.util.Collection;
import par2019.domain.model.entity.Entity;
import par2019.domain.model.entity.resumenSemestre;

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
     * 
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
    
    /**
     * Buscar el curso por su nombre
     * @param idProfesor
     * @return 
     * @throws Exception
     */
    public Collection<Curso> findByIdProfesor(int idProfesor) throws Exception;
    
    /**
     *
     * @param curso
     * @return 
     * @throws Exception
     */
    public int cantAlumnos(String curso) throws Exception;
    
     /**
     * @param idCurso
     * @param mesApertura
     * @param mesCierre
     * @return 
     * @throws Exception
     */
    public Collection<resumenSemestre> resumenSemestre(int idCurso, int mesApertura, int mesCierre) throws Exception;
}
