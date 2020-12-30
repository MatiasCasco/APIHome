/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.rest;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import par2019.domain.model.entity.Respuesta;
import par2019.domain.repository.JdbcRespuestaRepository;
import par2019.domain.service.RespuestaServiceImpl;

/**
 * REST Web Service
 *
 * @author Matias
 */
@Path("/rtaApi")
public class RespuestasRestService {
    private final RespuestaServiceImpl RespuestaService = new RespuestaServiceImpl(new JdbcRespuestaRepository());
    
    @GET
    @Path("/respuestas")
    @Produces("application/json")
    public ArrayList<Respuesta> getRespuestas() {
        ArrayList<Respuesta> respuestas = (ArrayList<Respuesta>) RespuestaService.getAll();
        return respuestas;
    }
    
    @GET
    @Path("/respuestas/{id}")
    @Produces("application/json")
    public Respuesta getRespuesta(@PathParam("id") int id) throws Exception {
        Respuesta respuesta =  RespuestaService.getRespuesta(id);
        return respuesta;
    }
    
    @GET
    @Path("/respuestasCuestionario/{id}")
    @Produces("application/json")
    public ArrayList<Respuesta> getRespuestaCuestionario(@PathParam("id") int id){
       ArrayList<Respuesta> respuestas = new ArrayList();
        try {
            respuestas = (ArrayList<Respuesta>) RespuestaService.findByIdCuestionario(id);
        } catch (Exception ex) {
            Logger.getLogger(RespuestasRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuestas;
    }
    
    @GET
    @Path("/respuestasPregunta/{id}")
    @Produces("application/json")
    public ArrayList<Respuesta> getRespuestaPregunta(@PathParam("id") int id){
       ArrayList<Respuesta> respuestas = new ArrayList();
        try {
            respuestas = (ArrayList<Respuesta>) RespuestaService.findByIdPregunta(id);
        } catch (Exception ex) {
            Logger.getLogger(RespuestasRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuestas;
    }
    
    @POST
    @Path("/respuesta")
    @Consumes("application/json")
    @Produces("application/json")
    public Respuesta addRespuesta(Respuesta entity) throws Exception{
      RespuestaService.add(entity);
      return entity;
    }
    
    @PUT
    @Path("/respuesta")
    @Consumes("application/json")
    public void updateRespuesta(Respuesta entity) {
        try {
            RespuestaService.update(entity);
        } catch (Exception ex) {
            Logger.getLogger(PreguntaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/respuesta/{id}")
    public void removeRespuesta(@PathParam("id") Integer id) {
        try {
            RespuestaService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(PreguntaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
