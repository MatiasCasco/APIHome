/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.repository;

import java.util.Collection;
import par2019.domain.model.entity.EstadisticaPregunta;

/**
 *
 * @author Matias
 */
public interface PreguntaRepository<Pregunta, Integer> extends Repository<Pregunta, Integer>{
    /**
     *
     * @param idPregunta
     * @return
     * @throws Exception
     */
    public boolean ContainsId(int idPregunta) throws Exception;
    
    /**
     * TActualiza todos los datos de una pregunta
     * @param pre
     * 
     */
    public void UpdatePreguntaImagen(Pregunta pre) throws Exception;
    
    /**
     * Trae una materia por su idMateria
     * @param idPregunta
     * @return
     */
    public Pregunta  getPregunta(int idPregunta) throws Exception;
    
    /**
     *
     * @param idCuestionario
     * @return
     * @throws Exception
     */
    public boolean ContainsIdCuestionario(int idCuestionario) throws Exception;
    
    /**
     * Todas las preguntas de un cuestionario
     * @param idCuestionario
     * @return
     * @throws Exception
     */
    public Collection<Pregunta> findByIdCuestionario(int idCuestionario) throws Exception;
    
    /**
     * 
     * @param idCuestionario
     * @return
     * @throws Exception
     */
    public Collection<EstadisticaPregunta> getEstadisticas(int idCuestionario) throws Exception;
}
