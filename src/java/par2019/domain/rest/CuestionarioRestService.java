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
import par2019.domain.model.entity.Cuestionario;
import par2019.domain.model.entity.Grafica;
import par2019.domain.repository.JdbcCuestionarioRepository;
import par2019.domain.service.CuestionarioServiceImpl;

/**
 * REST Web Service
 *
 * @author Matias
 */
@Path("/cuestionarioApi")
public class CuestionarioRestService {
    private final CuestionarioServiceImpl cuestionarioService = new CuestionarioServiceImpl(new JdbcCuestionarioRepository());
    
    @GET
    @Path("/cuestionarios")
    @Produces("application/json")
    public ArrayList<Cuestionario> getCuestionarios() {
        ArrayList<Cuestionario> cuestionarios = (ArrayList<Cuestionario>) cuestionarioService.getAll();
        return cuestionarios;
    }
    
    @GET
    @Path("/cuestionariosOFmateria/{materia}")
    @Produces("application/json")
    public ArrayList<Cuestionario> getCuestionariosOFmateria(@PathParam("materia") String materia) throws Exception  {
        ArrayList<Cuestionario> cuestionarios = (ArrayList<Cuestionario>) cuestionarioService.findByNameMateria(materia);
        return cuestionarios;
       
    }

    @GET
    @Path("/cuestionario/{id}")
    @Produces("application/json")
    public Cuestionario getCuestionario(@PathParam("id") int id) {
        try {
            Cuestionario cuestionario = cuestionarioService.getCuestionario(id);
            return cuestionario;
        } catch (Exception ex) {
            Logger.getLogger(CuestionarioRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @GET
    @Path("/alumnos/{id}")
    @Produces("application/json")
    public ArrayList<Grafica>  getCuestionarioAlumnos(@PathParam("id") int id) throws Exception {
        ArrayList<Grafica> graficas = (ArrayList<Grafica>) cuestionarioService.cuestionarioAlumnos(id);
        return graficas;
    }
    @GET 
    @Path("/cuestionariosOFCurso/{curso}")
    @Produces("application/json")
    public ArrayList<Cuestionario> getCuestionariosOfCurso(@PathParam("curso") String curso) throws Exception {
        ArrayList<Cuestionario> cuestionarios = (ArrayList<Cuestionario>) cuestionarioService.findByNameCurso(curso);
        return cuestionarios;
    }
   
    
    @GET
    @Path("/cursoName/{cursoName}/materiaName/{materiaName}")
    @Produces("application/json")
    public ArrayList<Cuestionario> getCuestionariosOfCursoAndMateria(@PathParam("cursoName") String cursoName, @PathParam("materiaName") String materiaName) throws Exception {
          ArrayList<Cuestionario> cuestionarios = (ArrayList<Cuestionario>) cuestionarioService.findByNameCursoAndMateria(cursoName, materiaName);
        return cuestionarios;
    }
     @GET
    @Path("/curso/{curso}/materia/{materia}")
    @Produces("application/json")
    public ArrayList<Cuestionario> getCuestionariosOfCursoAndMateriaApp(@PathParam("curso") String curso, @PathParam("materia") String materia) throws Exception {
        ArrayList<Cuestionario> cuestionarios = (ArrayList<Cuestionario>) cuestionarioService.findByNameCursoAndMateriaApp(curso, materia);
        return cuestionarios;
    }
    
    @GET
    @Path("/alumno/{alumno}/materia/{materia}")
    @Produces("application/json")
    public ArrayList<Cuestionario> getBYAlumnoAndMateria(@PathParam("alumno") int alumno, @PathParam("materia") int materiaName) throws Exception {
          ArrayList<Cuestionario> cuestionarios = (ArrayList<Cuestionario>) cuestionarioService.findByAlumnoAndMateria(alumno, materiaName);
        return cuestionarios;
    }


    @GET
    @Path("/resumen/{idCuestionario}")
    @Produces("application/json")
    public ArrayList<Grafica> getResumenTest(@PathParam("idCuestionario") int idCuestionario) throws Exception {
        ArrayList<Grafica> resumen = (ArrayList<Grafica>) cuestionarioService.resumenEvaluacion(idCuestionario);
        return resumen;
    }
//    
    @POST
    @Path("/cuestionario")
    @Consumes("application/json")
    @Produces("application/json")
    public Cuestionario addCuestionario(Cuestionario entity) throws Exception{
        cuestionarioService.add(entity);
        return entity;
    }
    
    @PUT
    @Path("/cuestionario")
    @Consumes("application/json")
    public void updateCuestionario(Cuestionario entity) throws Exception{
        cuestionarioService.update(entity);
    }
    
    @DELETE
    @Path("/cuestionario/{id}")
    public void removeCuestionario(@PathParam("id") int id) throws Exception {
        cuestionarioService.delete(id);
    }
     ///agregado
    @GET 
    @Path("/cuestionariosForMateria/{idMateria}")
    @Produces("application/json")
    public ArrayList<Cuestionario> getCuestionariosForMateria(@PathParam("idMateria") int idMateria) throws Exception {
        ArrayList<Cuestionario> cuestionarios = (ArrayList<Cuestionario>) cuestionarioService.findByIdMateria(idMateria);
        return cuestionarios;
    }
     ///agregado
    @GET 
    @Path("/cuestionariosProfesor/{idProfesor}")
    @Produces("application/json")
    public ArrayList<Cuestionario> getCuestionariosProfe(@PathParam("idProfesor") int idProfesor) throws Exception {
        ArrayList<Cuestionario> cuestionarios = (ArrayList<Cuestionario>) cuestionarioService.findByIdProfesor(idProfesor);
        return cuestionarios;
    }
    
    
}
