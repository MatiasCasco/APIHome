/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Respuesta;
import par2019.domain.repository.Repository;
import par2019.domain.repository.RespuestaRepository;



/**
 *
 * @author Matias
 */
public class RespuestaServiceImpl extends BaseService<Respuesta, Integer> implements RespuestaService {
    RespuestaRepository<Respuesta, Integer> RespuestaRepository;

    public RespuestaServiceImpl(RespuestaRepository<Respuesta, Integer> RespuestaRepository) {
        super(RespuestaRepository);
        this.RespuestaRepository = RespuestaRepository;
    }
    
    
    @Override
    public void insert(Respuesta rta) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         int bandera = 0;
        if (RespuestaRepository.containsIdPregunta(rta.getIdPregunta()) == false){
            bandera = 1;
            throw new Exception(String.format("No existe el identificador de la pregunta %d ", rta.getIdPregunta()));
        }   
        if(bandera == 0){
            RespuestaRepository.add(rta);
        } 
    }

    @Override
    public void update(Respuesta rta) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
        int bandera = 0; 
        if (!RespuestaRepository.containsId(rta.getIdRta())) {
            bandera = 1;
            throw new Exception(String.format("No existe la respuesta con el id %s ", rta.getIdRta()));
        }
        if(bandera == 0){RespuestaRepository.update(rta);}          
    }

    @Override
    public void delete(Integer id) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0; 
        if (!RespuestaRepository.containsId(id)) {
            bandera = 1;
            throw new Exception(String.format("No existe la respuesta con el id %s ", id));
        }
        if(bandera == 0){RespuestaRepository.remove(id);}  
    }

    @Override
    public Collection<Respuesta> findByIdCuestionario(int idCuestionario) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!RespuestaRepository.containsIdCuestionario(idCuestionario)){
            throw new Exception(String.format("No se encontro el identificador del cuestionario %d", idCuestionario)); 
        }
        return RespuestaRepository.findByIdCuestionario(idCuestionario);
    }

    @Override
    public Collection<Respuesta> findByIdPregunta(int idPregunta) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!RespuestaRepository.containsIdPregunta(idPregunta)){
            throw new Exception(String.format("No se encontro el identificador de la pregunta %d", idPregunta)); 
        }
        return RespuestaRepository.findByIdPregunta(idPregunta);
    }

    @Override
    public Respuesta getRespuesta(int idRespuesta) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (RespuestaRepository.containsId(idRespuesta) == false){
            throw new Exception(String.format("No existe el identificador de la Pregunta ", idRespuesta));
        }
        return RespuestaRepository.getRespuesta(idRespuesta);
    }
    
}
