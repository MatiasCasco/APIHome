/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Respuesta;

/**
 *
 * @author Matias
 */
public interface RespuestaService {
      /**
     *
     * @param rta
     * @throws Exception
     */
    public void insert(Respuesta rta) throws Exception;

    /**
     *
     * @param rta
     * @throws Exception
     */
    public void update(Respuesta rta) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;
    
    /**
     *  Encontrar todos los identificadores de las preguntas y sus respectivas rtas posibles de un cuestionario especifico
     * @param idCuestionario
     * @return
     * @throws Exception
     */
    public Collection<Respuesta> findByIdCuestionario(int idCuestionario) throws Exception;
 
    /**
     * Buscar el curso por su nombre
     * @param idPregunta
     * @return 
     * @throws Exception
     */
    public Collection<Respuesta> findByIdPregunta(int idPregunta) throws Exception;
    
     /**
     * Buscar el curso por su nombre
     * @param idRespuesta
     * @return 
     * @throws Exception
     */
    public Respuesta getRespuesta(int idRespuesta) throws Exception;
 
}
