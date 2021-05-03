/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.rest;

import java.awt.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import par2019.domain.model.entity.Reto;
import par2019.domain.repository.JdbcRetoRepository;
import par2019.domain.service.RetoServiceImpl;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("retoApi")
public class RetoRestService {
  private final RetoServiceImpl RetoService = new RetoServiceImpl(new JdbcRetoRepository());
  
   @GET
    @Path("/reto/{list}/opciones/{size}/materia/{name}/curso/{curso}")
    @Produces("application/json")
    public ArrayList<Reto> getReto(@PathParam("list") int list,@PathParam("size") int size, @PathParam("name") String name, @PathParam("curso") String curso){
       ArrayList<Reto> reto = new ArrayList();
        try {
          reto = (ArrayList<Reto>) RetoService.reto(list, size, name, curso);
        } catch (Exception ex) {
            Logger.getLogger(RetoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reto;
    }
  
//  @GET
//    @Path("/reto/{list}/opciones/{size}/materia/{name}")
//    @Produces("application/json")
//    public ArrayList getReto(@PathParam("list") int list,@PathParam("size") int size, @PathParam("name") String name){
//       ArrayList reto = new ArrayList();
//        try {
//          reto =  (ArrayList) RetoService.reto(list, size, name);
//        } catch (Exception ex) {
//            Logger.getLogger(RetoRestService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return reto;
//    }
}
