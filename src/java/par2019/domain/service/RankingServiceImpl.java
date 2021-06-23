/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Puntuacion;
import par2019.domain.model.entity.Ranking;
import par2019.domain.repository.RankingRepository;

/**
 *
 * @author User
 */
public class RankingServiceImpl extends BaseService<Ranking, Integer>  implements RankingService {
    RankingRepository<Ranking, Integer> RankingRepository;

    public RankingServiceImpl(RankingRepository<Ranking, Integer> RankingRepository) {
        super(RankingRepository);
        this.RankingRepository = RankingRepository;
    }

    @Override
    public Collection<Ranking> listaPuntajes(int idCuestionario) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!RankingRepository.containsIdCuestionario(idCuestionario)){
            throw new Exception(String.format("No se encontro el identificador %d del cuestionario", idCuestionario)); 
        }
        return RankingRepository.listaPuntajes(idCuestionario);
    }

    @Override
    public Collection<Ranking> rankingGlobal(String nameCurso, String nameMateria) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return RankingRepository.rankingGlobal(nameCurso, nameMateria);
    }

    @Override
    public Collection<Ranking> rankingGlobalById(int idCurso, int idMateria) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         return RankingRepository.rankingGlobalbyId(idCurso, idMateria);
    }

    @Override
    public Collection<Ranking> listaPuntajesC(int idCuestionario) throws Exception {
       //To change body of generated methods, choose Tools | Templates
          //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!RankingRepository.containsIdCuestionario(idCuestionario)){
            throw new Exception(String.format("No se encontro el identificador %d del cuestionario", idCuestionario)); 
        }
        return RankingRepository.listaPuntajesC(idCuestionario);
    }
}   
