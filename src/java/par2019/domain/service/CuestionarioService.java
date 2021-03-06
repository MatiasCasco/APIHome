/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Cuestionario;
import par2019.domain.model.entity.Grafica;

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
     *  
     * @param nameMateria
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByNameMateria(String nameMateria) throws Exception;
 
    /**
     * Buscar el curso por su nombre
     * @param idCuestionario
     * @return 
     * @throws Exception
     */
    public Cuestionario getCuestionario(int idCuestionario) throws Exception;
    
      /**
     *  Criteria representa encontrar los cuestionarios de un curso
     * @param nameCurso
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByNameCurso(String nameCurso) throws Exception;
    
   
      /**
     *  Criteria representa encontrar los cuestionarios de un curso
     * @param nameCurso
     * @param nameMateria
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByNameCursoAndMateria(String nameCurso, String nameMateria) throws Exception;
    
     /**
     *  Criteria representa encontrar los cuestionarios de un curso
     * @param Curso
     * @param Materia
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByNameCursoAndMateriaApp(String Curso, String Materia) throws Exception;

   
    /**
     *
     * @param idAlumno
     * @param idMateria
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByAlumnoAndMateria(int idAlumno, int idMateria) throws Exception;
     /**
     *  Criteria representa encontrar los cuestionarios de un curso
     * @param idMateria
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByIdMateria(int idMateria) throws Exception;
     /**
     *  Criteria representa encontrar los cuestionarios de un curso
     * @param idProfesor
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByIdProfesor(int idProfesor) throws Exception;
    
     /**
     * @param idCuestionario
     * @return
     * @throws Exception
     */
    public Collection<Grafica> resumenEvaluacion(int idCuestionario) throws Exception;

    /**
     *
     * @param idCuestionario
     * @return
     * @throws Exception
     */
    public Collection<Grafica> cuestionarioAlumnos(int idCuestionario) throws Exception;
}
