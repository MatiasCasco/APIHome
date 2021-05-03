/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import java.util.List;
import par2019.domain.model.entity.Reto;
import par2019.domain.repository.RetoRepository;

/**
 *
 * @author User
 */
public class RetoServiceImpl extends BaseService<Reto, Integer> implements RetoService  {
      RetoRepository<Reto, Integer> RetoRepository;

    /**
     *
     * @param RetoRepository
     */
    public RetoServiceImpl(RetoRepository<Reto, Integer> RetoRepository) {
    super(RetoRepository);
    this.RetoRepository = RetoRepository;
    }

    @Override
    public Collection<Reto> reto(int list, int sizeOptions, String materia, String curso) throws Exception {
//    public List reto(int list, int sizeOptions, String materia) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return RetoRepository.reto(list, sizeOptions, materia, curso);
    }
    
}
