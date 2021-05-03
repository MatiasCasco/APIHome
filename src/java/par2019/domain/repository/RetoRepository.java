/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.repository;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author User
 */
public interface RetoRepository<Reto, Integer> extends Repository<Reto, Integer> {
     /**
     *
     * @param nameCurso
     * @return
     * @throws Exception
     */
    public Collection<Reto> reto(int list, int sizeOptions, String materia, String curso) throws Exception;
//    public List reto(int list, int sizeOptions, String materia) throws Exception;
}
