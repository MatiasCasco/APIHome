/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.repository;

import java.io.File;

/**
 *
 * @author User
 */
public interface ImageRepository<Image, Integer> extends Repository<Image, Integer> {
    /**
     * Buscar la imagen de la pregunta
     * @param id
     * @return 
     * @throws Exception
     */
    public byte[] image(int id) throws Exception;
}
