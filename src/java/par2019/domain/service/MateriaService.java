/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Grafica;
import par2019.domain.model.entity.Materia;

/**
 *
 * @author Matias
 */
public interface MateriaService {
     /**
     *
     * @param materia
     * @throws Exception
     */
    public void insert(Materia materia) throws Exception;

    /**
     *
     * @param materia
     * @throws Exception
     */
    public void update(Materia materia) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;
    
    /**
     * Encontrar todas las materias de un curso
     * @param NameCurso
     * @param NameMateria
     * @return
     * @throws Exception
     */
    public Materia findMateriaInCurso(String NameCurso, String NameMateria) throws Exception;
    
    /**
     * Encontrar todas las materias de un curso por el nombre
     * @param NameCurso
     * @return
     * @throws Exception
     */
    public Collection<Materia> findByMateriasCurso(String NameCurso) throws Exception;
    /**
     * Encontrar todas las materias de un curso por el nombre
     * @param NameCurso
     * @return
     * @throws Exception
     */
    public Collection<Materia> findByCurso(int idCurso) throws Exception;
    
    /**
     *
     * @param NameMateria
     * @return
     * @throws Exception
     */
    public Collection<Materia> findByNameMateria(String NameMateria) throws Exception;
    
    /**
     * Buscar el curso por su nombre
     * @param idMateria
     * @return 
     * @throws Exception
     */
    public Materia findByIdMateria(int idMateria) throws Exception;
    
    
       /**
     *
     * @param NameMateria
     * @return
     * @throws Exception
     */
    public Collection<Materia> findByProfesor(int idProfesor) throws Exception;
    
     /**
     * Buscan todos los cursos que posea una asignatura especifco
     * @param NameCurso
     * @return
     * @throws Exception
     */
    public Collection<Materia> findByMateriasCursoDisponible(String NameCurso) throws Exception;

    /**
     * Buscan todos los cursos que posea una asignatura especifco
     * @param NameCurso
     * @return
     * @throws Exception
     */
    public Collection<Materia> findByMateriasCursoTestDisponible(String NameCurso) throws Exception;
    /**
     *
     * @param NameCurso
     * @return
     * @throws Exception
     */
    public Collection<Grafica> contenidoMaterias(String NameCurso) throws Exception;
    /**
     *
     * @param idCurso
     * @return
     * @throws Exception
     */
    public Collection<Grafica> contenidoXMaterias(int idCurso) throws Exception;
    
    
    
}
