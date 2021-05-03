/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.rest;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import par2019.domain.repository.JdbcImageRepository;
import par2019.domain.service.ImageServiceImpl;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/imageApi")
public class ImageRestService {
     private final ImageServiceImpl ImageService = new ImageServiceImpl(new JdbcImageRepository());
    
    @GET
    @Path("/image/{id}")
    @Produces("image/jpeg")
    public InputStream getImagen(@PathParam("id") int id) throws Exception {
        InputStream targetStream = new ByteArrayInputStream(ImageService.image(id));
        return targetStream;
    }
}
