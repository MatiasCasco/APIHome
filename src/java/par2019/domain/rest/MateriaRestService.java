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
    public Materia getMateriaCurso(@PathParam("cursoN") String cursoN, @PathParam("materiaN") String materiaN) throws Exception {
        Materia materia =  materiaService.findMateriaInCurso(cursoN, materiaN);
        return materia;
    }
   
    @GET
    @Path("/materias/{NameMateria}")
    @Produces("application/json")
    public ArrayList<Materia> getMateriaInCursos(@PathParam("NameMateria") String NameMateria){
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
    
     @GET
    @Path("/retos/{NameCurso}")
    @Produces("application/json")
    public ArrayList<Materia> getMateriasReto(@PathParam("NameCurso") String NameCurso){
       ArrayList<Materia> materias = new ArrayList();
        try {
            materias = (ArrayList<Materia>) materiaService.findByMateriasCursoDisponible(NameCurso);
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }
    
     @GET
    @Path("/test/{NameCurso}")
    @Produces("application/json")
    public ArrayList<Materia> getMateriasTest(@PathParam("NameCurso") String NameCurso){
       ArrayList<Materia> materias = new ArrayList();
        try {
            materias = (ArrayList<Materia>) materiaService.findByMateriasCursoTestDisponible(NameCurso);
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }
    
    @GET
    @Path("/materiasProf/{idProfesor}")
    @Produces("application/json")
    public ArrayList<Materia> getMateriasProfesor(@PathParam("idProfesor") int idProfesor){
       ArrayList<Materia> materias = new ArrayList();
        try {
            materias = (ArrayList<Materia>) materiaService.findByProfesor(idProfesor);
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }
     @GET
    @Path("/materiasForCurso/{idCurso}")
    @Produces("application/json")
    public ArrayList<Materia> getMateriasForIdCurso(@PathParam("idCurso") int idCurso){
       ArrayList<Materia> materias = new ArrayList();
        try {
            materias = (ArrayList<Materia>) materiaService.findByCurso(idCurso);
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }
      
    @GET
    @Path("/materia/{idMateria}")
    @Produces("application/json")
    public Materia getMateria(@PathParam("idMateria") int idMateria){
       Materia materia = null;
        try {
            materia = (Materia) materiaService.findByIdMateria(idMateria);
        } catch (Exception ex) {
            Logger.getLogger(CursoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materia;
    }
    
    @POST
    @Path("/materia")
    @Consumes("application/json")
    @Produces("application/json")
    public Materia addMateria(Materia entity) throws Exception{
      materiaService.add(entity);
      return entity;
    }
    
    @PUT
    @Path("/materia")
    @Consumes("application/json")
    public void updateMateria(Materia entity) {
        try {
            materiaService.update(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/materia/{id}")
    public void removeMateria(@PathParam("id") Integer id) {
        try {
            materiaService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
