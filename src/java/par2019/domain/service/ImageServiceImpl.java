/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Image;
import par2019.domain.model.entity.Reto;
import par2019.domain.repository.ImageRepository;

/**
 *
 * @author User
 */
public class ImageServiceImpl extends BaseService<Image, Integer> implements ImageService {
      ImageRepository<Image, Integer> ImageRepository;

    /**
     *
     * @param RetoRepository
     */
    public ImageServiceImpl(ImageRepository<Image, Integer> ImageRepository) {
    super(ImageRepository);
    this.ImageRepository = ImageRepository;
    }

    @Override
    public byte[] image(int id) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return ImageRepository.image(id);
    }
  
}
