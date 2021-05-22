/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Puntuacion;
import par2019.domain.repository.PuntuacionRepository;

/**
 *
 * @author User
 */
public class PuntuacionServiceImpl extends BaseService<Puntuacion, Integer>  implements PuntuacionService {
    PuntuacionRepository<Puntuacion, Integer> puntuacionRepository;

    public PuntuacionServiceImpl(PuntuacionRepository<Puntuacion, Integer> puntuacionRepository) {
        super(puntuacionRepository);
        this.puntuacionRepository = puntuacionRepository;
    }
    
    @Override
    public Collection<Puntuacion> listaCuestionariosResueltos(int idAlumno) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!puntuacionRepository.containsIdAlumno(idAlumno)){
            throw new Exception(String.format("No se encontro el identificador %d del alumno", idAlumno)); 
        }
        return puntuacionRepository.listaCuestionariosResueltos(idAlumno);
    }
    
}
