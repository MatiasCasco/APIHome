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
import par2019.domain.model.entity.Pregunta;
import par2019.domain.repository.JdbcPreguntaRepository;
import par2019.domain.service.PreguntaServiceImpl;

/**
 * REST Web Service
 *
 * @author Matias
 */
@Path("/preguntaApi")
public class PreguntaRestService {
private final PreguntaServiceImpl preguntaService = new PreguntaServiceImpl(new JdbcPreguntaRepository());
    
    @GET
    @Path("/preguntas")
    @Produces("application/json")
    public ArrayList<Pregunta> getPreguntas() {
        ArrayList<Pregunta> preguntas = (ArrayList<Pregunta>) preguntaService.getAll();
        return preguntas;
    }
    
    @GET
    @Path("/preguntas/{id}")
    @Produces("application/json")
    public Pregunta getPregunta(@PathParam("id") int id) throws Exception {
        Pregunta pregunta =  preguntaService.getPregunta(id);
        return pregunta;
    }
   
    @GET
    @Path("/preguntasCuestionario/{id}")
    @Produces("application/json")
    public ArrayList<Pregunta> getPreguntasCuestionario(@PathParam("id") int id){
       ArrayList<Pregunta> preguntas = new ArrayList();
        try {
            preguntas = (ArrayList<Pregunta>) preguntaService.findByIdCuestionario(id);
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preguntas;
    }
    
    @POST
    @Path("/pregunta")
    @Consumes("application/json")
    @Produces("application/json")
    public Pregunta addPregunta(Pregunta entity) throws Exception{
      preguntaService.add(entity);
      return entity;
    }
    
    @PUT
    @Path("/pregunta")
    @Consumes("application/json")
    public void updatePregunta(Pregunta entity) {
        try {
            preguntaService.update(entity);
        } catch (Exception ex) {
            Logger.getLogger(PreguntaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PUT
    @Path("/preguntaImagen")
    @Consumes("application/json")
    public void updatePreguntaImagen(Pregunta entity) {
        try {
            preguntaService.updateImagen(entity);
        } catch (Exception ex) {
            Logger.getLogger(PreguntaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/pregunta/{id}")
    public void removePregunta(@PathParam("id") Integer id) {
        try {
            preguntaService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(PreguntaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
