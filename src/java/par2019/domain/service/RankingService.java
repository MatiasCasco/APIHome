/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Ranking;

/**
 *
 * @author User
 */
public interface RankingService {
    /**
     *
     * @param idCuestionario
     * @return
     */
    public Collection<Ranking> listaPuntajes(int idCuestionario) throws Exception;
    
     /**
     * @param nameCurso
     * @param nameMateria 
     * @return
     */
    public Collection<Ranking> rankingGlobal(String nameCurso, String nameMateria) throws Exception;
     /**
     * @param idCurso
     * @param idMateria 
     * @return
     */
    public Collection<Ranking> rankingGlobalById(int idCurso,int idMateria) throws Exception;
    
     /**
     * @param idCuestionario
     * @return Lista de resupestas de un cuestionario
     */
    public Collection<Ranking> listaPuntajesC(int idCuestionario) throws Exception;;
}
