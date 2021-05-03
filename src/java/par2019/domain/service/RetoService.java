/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import java.util.List;
import par2019.domain.model.entity.Reto;

/**
 *
 * @author User
 */
public interface RetoService {
    
     /**
     *  Retorna la estructura del reto
     * @param list
     * @param sizeOptions
     * @param materia

     * @return
     * @throws Exception
     */
    public Collection<Reto> reto(int list, int sizeOptions, String materia, String curso) throws Exception;
//    public List reto(int list, int sizeOptions, String materia) throws Exception;
}
