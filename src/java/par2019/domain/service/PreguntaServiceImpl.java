/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Pregunta;
import par2019.domain.repository.PreguntaRepository;


/**
 *
 * @author Matias
 */
public class PreguntaServiceImpl extends BaseService<Pregunta, Integer> implements PreguntaService {
     PreguntaRepository<Pregunta, Integer> PreguntaRepository;

    public PreguntaServiceImpl(PreguntaRepository<Pregunta, Integer> PreguntaRepository) {
        super(PreguntaRepository);
        this.PreguntaRepository = PreguntaRepository;
    }

    @Override
    public void insert(Pregunta pregunta) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0;
        if (PreguntaRepository.ContainsIdCuestionario(pregunta.getIdCuestionario()) == false){
            bandera = 1;
            throw new Exception(String.format("No existe el identificador del cuestionario ", pregunta.getIdCuestionario()));
        }
       
        if (pregunta.getPregunta() == null || "".equals(pregunta.getPregunta())) {
            bandera = 1;
            throw new Exception("La Pregunta no puede ser nulo o cadena vacia.");
        }
               
        if(bandera == 0){
            PreguntaRepository.add(pregunta);
        }      
    }

    @Override
    public void update(Pregunta pregunta) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0;
        if (PreguntaRepository.ContainsId(pregunta.getIdPregunta()) == false){
            bandera = 1;
            throw new Exception(String.format("No existe el identificador de la pregunta ", pregunta.getIdPregunta()));
        }
        if(bandera == 0){
            PreguntaRepository.update(pregunta);
        }
    }
    
    @Override
    public void updateImagen(Pregunta pregunta) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0;
        if (PreguntaRepository.ContainsId(pregunta.getIdPregunta()) == false){
            bandera = 1;
            throw new Exception(String.format("No existe el identificador de la pregunta ", pregunta.getIdPregunta()));
        }
        if(bandera == 0){
            PreguntaRepository.UpdatePreguntaImagen(pregunta);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0;
        if (PreguntaRepository.ContainsId(id) == false){
            bandera = 1;
            throw new Exception(String.format("No existe el identificador de la pregunta ", id));
        }
        if(bandera == 0){
            PreguntaRepository.remove(id);
        }
    }

    @Override
    public Collection<Pregunta> findByIdCuestionario(int idCuestionario) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (PreguntaRepository.ContainsIdCuestionario(idCuestionario) == false){
            throw new Exception(String.format("No existe el identificador del cuestionario ", idCuestionario));
        }
        return PreguntaRepository.findByIdCuestionario(idCuestionario);
    }

    @Override
    public Pregunta getPregunta(int idPregunta) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (PreguntaRepository.ContainsId(idPregunta) == false){
            throw new Exception(String.format("No existe el identificador de la Pregunta ", idPregunta));
        }
        return PreguntaRepository.getPregunta(idPregunta);
    }
     
}
