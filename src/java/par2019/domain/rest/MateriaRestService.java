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
import par2019.domain.model.entity.Curso;
import par2019.domain.model.entity.Materia;
import par2019.domain.repository.JdbcMateriaRepository;
import par2019.domain.service.MateriaServiceImpl;

/**
 * REST Web Service
 *
 * @author Matias
 */
@Path("/materiaApi")
public class MateriaRestService {
   private final MateriaServiceImpl materiaService = new MateriaServiceImpl(new JdbcMateriaRepository());
    
    @GET
    @Path("/materias")
    @Produces("application/json")
    public ArrayList<Materia> getMaterias() {
        ArrayList<Materia> materias = (ArrayList<Materia>) materiaService.getAll();
        return materias;
    }
    
    @GET
    @Path("/cursoN/{cursoN}/materiaN/{materiaN}")
    @Produces("application/json")
    public Materia getMateria(@PathParam("cursoN") String cursoN, @PathParam("materiaN") String materiaN) throws Exception {
        Materia materia =  materiaService.findMateriaInCurso(cursoN, materiaN);
        return materia;
    }
   
    @GET
    @Path("/materias/{NameMateria}")
    @Produces("application/json")
    public ArrayList<Materia> getCursoName(@PathParam("NameMateria") String NameMateria){
       ArrayList<Materia> materias = new ArrayList();
        try {
            materias = (ArrayList<Materia>) materiaService.findByNameMateria(NameMateria);
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }
    
    @GET
    @Path("/materiasNameCurso/{NameCurso}")
    @Produces("application/json")
    public ArrayList<Materia> getMateriasCurso(@PathParam("NameCurso") String NameCurso){
       ArrayList<Materia> materias = new ArrayList();
        try {
            materias = (ArrayList<Materia>) materiaService.findByMateriasCurso(NameCurso);
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }
      
    
    @POST
    @Path("/materia")
    @Consumes("application/json")
    @Produces("application/json")
    public Materia addCurso(Materia entity) throws Exception{
      materiaService.add(entity);
      return entity;
    }
    
    @PUT
    @Path("/materia")
    @Consumes("application/json")
    public void updateCurso(Materia entity) {
        try {
            materiaService.update(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/materia/{id}")
    public void removeCurso(@PathParam("id") Integer id) {
        try {
            materiaService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
