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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import par2019.domain.model.entity.Curso;
import par2019.domain.model.entity.Entity;
import par2019.domain.repository.JdbcCursoRepository;
import par2019.domain.service.CursoServiceImpl;

/**
 * REST Web Service
 *
 * @author Matias
 */
@Path("/cursoapi")
public class CursoRestService {

    private final CursoServiceImpl cursoService = new CursoServiceImpl(new JdbcCursoRepository());

    @GET
    @Path("/cursos")
    @Produces("application/json")
    public ArrayList<Curso> getCursos() {
        ArrayList<Curso> cursos = (ArrayList<Curso>) cursoService.getAll();
        return cursos;
    }

    @GET
    @Path("/cursosXprof/{NameProfesor}")
    @Produces("application/json")
    public ArrayList<Curso> getCursosProf(@PathParam("NameProfesor") String NameProfesor) {
        try {
            ArrayList<Curso> cursos = (ArrayList<Curso>) cursoService.findByCriteria(NameProfesor); 
            return cursos;
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @GET
    @Path("/cursosIdprof/{idProfesor}")
    @Produces("application/json")
    public ArrayList<Curso> CursosfindIdProf(@PathParam("idProfesor") int idProfesor) {
        try {
            ArrayList<Curso> cursos = (ArrayList<Curso>) cursoService.findByIdProfesor(idProfesor); 
            return cursos;
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @GET
    @Path("/curso/{idCurso}")
    @Produces("application/json")
    public Curso getCurso(@PathParam("idCurso") int idCurso){
       Curso curso = null;
        try {
            curso = (Curso) cursoService.findByIdCurso(idCurso);
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curso;
    }
    
    @GET
    @Path("/cursoName/{NameCurso}")
    @Produces("application/json")
    public ArrayList<Curso> getCursoName(@PathParam("NameCurso") String NameCurso){
       try {
            ArrayList<Curso> cursos = (ArrayList<Curso>) cursoService.findByNameCurso(NameCurso); 
            return cursos;
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @POST
    @Path("/cursos")
    @Consumes("application/json")
    @Produces("application/json")
    public Curso addCurso(Curso entity) throws Exception{
      cursoService.insert(entity);
      return entity;
    }
    
    @PUT
    @Path("/cursos")
    @Consumes("application/json")
    public void updateCurso(Curso entity) {
        try {
            cursoService.update(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/cursos/{id}")
    public void removeCurso(@PathParam("id") Integer id) {
        try {
            cursoService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
