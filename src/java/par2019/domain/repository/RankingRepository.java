/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.repository;

import java.util.Collection;

/**
 *
 * @author User
 */
public interface RankingRepository<Ranking, Integer> extends Repository<Ranking, Integer> {
     /**
     *
     * @param idCuestionario
     * @return
     */
    public boolean containsIdCuestionario(int idCuestionario);
    
    /**
     *
     * @param idCuestionario
     * @return
     */
    public Collection<Ranking> listaPuntajes(int idCuestionario);
     /**
     *
     * @param idAlumno
     * @return
     */
    public boolean containsIdAlumno(int idAlumno);
    
    /**
     * @param nameCurso
     * @param nameMateria 
     * @return
     */
    public Collection<Ranking> rankingGlobal(String nameCurso, String nameMateria);
      /**
     * @param idCurso
     * @param idMateria 
     * @return
     */
    public Collection<Ranking> rankingGlobalbyId(int idCurso, int idMateria);
    /**
     *
     * @param idCuestionario
     * @return
     */
    public Collection<Ranking> listaPuntajesC(int idCuestionario);
}
