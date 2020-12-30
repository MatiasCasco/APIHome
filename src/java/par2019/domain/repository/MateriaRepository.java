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
 * @param <Materia>
 * @param <Integer>
 */
public interface MateriaRepository<Materia, Integer> extends Repository<Materia, Integer>{
     /**
     * Trae todas las materias que posee un curso
     * @param idCurso
     * @return
     * @throws Exception
     */
    public Materia findMateriaInCurso(String NameCurso, String NameMateria) throws Exception;
    
    /**
     * Buscan todos los cursos que posea una asignatura especifco
     * @param NameCurso
     * @return
     * @throws Exception
     */
    public Collection<Materia> findByMateriaCurso(String NameCurso) throws Exception;
    
    /**
     * Trae una materia por su idMateria
     * @param idMateria
     * @return
     */
    public Materia  getMateria(int idMateria);
    
    /**
     *
     * @param idCurso
     * @return
     * @throws Exception
     */
    public boolean ContainsIdCurso(int idCurso) throws Exception;

    /**
     * Comprobar si ya existe el nombre de la materia en un curso
     * @param idCurso
     * @param materia
     * @return
     * @throws Exception
     */
    public boolean ContainsNameMateria(int idCurso, String materia) throws Exception;
    
    /**
     *
     * @param NameMateria
     * @return
     * @throws Exception
     */
    public Collection<Materia> findByNameMateria(String NameMateria) throws Exception;
    
}
