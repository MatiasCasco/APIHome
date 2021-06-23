/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.EstadisticaPregunta;
import par2019.domain.model.entity.Pregunta;

/**
 *
 * @author Matias
 */
public interface PreguntaService {
     /**
     *
     * @param pregunta
     * @throws Exception
     */
    public void insert(Pregunta pregunta) throws Exception;

    /**
     *
     * @param pregunta
     * @throws Exception
     */
    public void update(Pregunta pregunta) throws Exception;
    
    /**
     *
     * @param pregunta
     * @throws Exception
     */
    public void updateImagen(Pregunta pregunta) throws Exception;
//    

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;
    
     /**
     *  Criteria representa encontrar curso del profesor
     * @param idCuestionario
     * @return
     * @throws Exception
     */
    public Collection<Pregunta> findByIdCuestionario(int idCuestionario) throws Exception;
 
    /**
     * Buscar el curso por su nombre
     * @param idPregunta
     * @return 
     * @throws Exception
     */
    public Pregunta getPregunta(int idPregunta) throws Exception;
     
    /**
     * 
     * @param idCuestionario
     * @return
     * @throws Exception
     */
    public Collection<EstadisticaPregunta> getEstadisticas(int idCuestionario) throws Exception;
}
