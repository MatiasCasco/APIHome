/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.repository;

import java.util.Collection;
import par2019.domain.model.entity.Grafica;

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
     * @param nameMateria
     * @return
     * @throws Exception
     */
    public boolean ContainsNameMateria(String nameMateria) throws Exception;
    
     /**
     *
     * @param idCurso
     * @return
     * @throws Exception
     */
    public boolean ContainsCurso(int idCurso) throws Exception;
    
    /**
     *
     * @param idAlumno
     * @return
     * @throws Exception
     */
    public boolean ContainsAlumno(int idAlumno) throws Exception;

    /**
     *
     * @param nameCurso
     * @return
     * @throws Exception
     */
    public boolean ContainsNameCurso(String nameCurso) throws Exception;

    
     /**
     *
     * @param nameMateria
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByNameMateria(String nameMateria) throws Exception;
    
     /**
     * @param nameCurso
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByNameCurso(String nameCurso) throws Exception;
    
    /**
     *
     * @param nameCurso
     * @param nameMateria
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByNameCursoAndMateria(String nameCurso, String nameMateria) throws Exception;
    
     /**
     *
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
     * @param idMateria
     * @return
     * @throws Exception
     */
    public Collection<Cuestionario> findByIdMateria(int idMateria) throws Exception;
       /**
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
