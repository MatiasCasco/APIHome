/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

/**
 *
 * @author User
 */
public interface ImageService {
     /**
     *  Retorna la estructura del reto
     * @param id

     * @return
     * @throws Exception
     */
    public byte[] image(int id) throws Exception;
}
