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
 */
public interface RespuestaRepository<Respuesta, Integer> extends Repository<Respuesta, Integer>{
    
     /**
     * Trae una repuesta especifica
     * @param idRespuesta
     * @return
     */
    public Respuesta  getRespuesta(int idRespuesta);
   
    
    /**
     *
     * @param idCuestionario
  
     * @return
     * @throws java.lang.Exception
     */  
    public Collection<Respuesta> findByIdCuestionario(int idCuestionario) throws Exception;
    
    /**
     *
     * @param Id
     * @return
     */
    public boolean containsId(int Id);
    
     /**
     *
     * @param IdPregunta
     * @return
     */
    public boolean containsIdPregunta(int IdPregunta);

     /**
     *
     * @param IdCuestionario
     * @return
     */
    public boolean containsIdCuestionario(int IdCuestionario);
    
    /**
     *
     * @param idPregunta
     * @return
     * @throws Exception
     */
    public Collection<Respuesta> findByIdPregunta(int idPregunta) throws Exception;
  
}
