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
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import par2019.domain.model.entity.Test;
import par2019.domain.model.entity.reporte;
import par2019.domain.repository.JdbcTestRepository;
import par2019.domain.service.TestServiceImpl;


/**
 * REST Web Service
 *
 * @author User
 */
@Path("testApi")
public class TestRestService {
    private final TestServiceImpl TestService = new TestServiceImpl(new JdbcTestRepository());
  
    @GET
    @Path("/test/{Cuestionario}")
    @Produces("application/json")
    public ArrayList<Test> getTest(@PathParam("Cuestionario") int Cuestionario) {
    //public ArrayList<String> getTest(@PathParam("Cuestionario") int Cuestionario) {
       ArrayList<Test> test = new ArrayList();
        //ArrayList<String> test = new ArrayList();
       try {
            test = (ArrayList<Test>) TestService.Test(Cuestionario);
            //test =  (ArrayList<String>) TestService.Test(Cuestionario);
       } catch (Exception ex) {
           Logger.getLogger(RetoRestService.class.getName()).log(Level.SEVERE, null, ex);
       }
        return test;
    }
    @GET
    @Path("/webtest/{Cuestionario}")
    @Produces("application/json")
    public ArrayList<Test> getWebTest(@PathParam("Cuestionario") int Cuestionario) {
    //public ArrayList<String> getTest(@PathParam("Cuestionario") int Cuestionario) {
       ArrayList<Test> test = new ArrayList();
        //ArrayList<String> test = new ArrayList();
       try {
            test = (ArrayList<Test>) TestService.WebTest(Cuestionario);
            //test =  (ArrayList<String>) TestService.Test(Cuestionario);
       } catch (Exception ex) {
           Logger.getLogger(RetoRestService.class.getName()).log(Level.SEVERE, null, ex);
       }
        return test;
    }
    
    @GET
    @Path("/test/{Cuestionario}/alumno/{Alumno}")
    @Produces("application/json")
    public ArrayList<Test> getTestRecover(@PathParam("Cuestionario") int Cuestionario, @PathParam("Alumno") int Alumno) {
    //public ArrayList<String> getTest(@PathParam("Cuestionario") int Cuestionario) {
       ArrayList<Test> test = new ArrayList();
        //ArrayList<String> test = new ArrayList();
       try {
            test = (ArrayList<Test>) TestService.TestRecuperacion(Cuestionario, Alumno);
            //test =  (ArrayList<String>) TestService.Test(Cuestionario);
       } catch (Exception ex) {
           Logger.getLogger(RetoRestService.class.getName()).log(Level.SEVERE, null, ex);
       }
        return test;
    }
    
    @GET
    @Path("/reporte/{Cuestionario}")
    @Produces("application/json")
    public ArrayList<reporte> getReporte(@PathParam("Cuestionario") int Cuestionario) throws Exception {
       ArrayList<reporte> reporte = (ArrayList<reporte>) TestService.reporteTest(Cuestionario);
       return reporte;
    }
    
    @POST
    @Path("/cuestionario/{Cuestionario}/alumno/{Alumno}/puntos/{Puntaje}")
    @Produces("application/json")
    public String addCuestionario(@PathParam("Cuestionario") int Cuestionario,@PathParam("Alumno") int Alumno,@PathParam("Puntaje") int Puntaje) throws Exception{
        TestService.addPuntosXCuestionarios(Alumno, Cuestionario, Puntaje);
        String cadena = "Se inserto con exito";
        return cadena;
    }
    
    @POST
    @Path("/pregunta/{Pregunta}/alumno/{Alumno}/puntos/{Puntaje}")
    @Produces("application/json")
    public String addPregunta(@PathParam("Pregunta") int Pregunta,@PathParam("Alumno") int Alumno,@PathParam("Puntaje") int Puntaje) throws Exception{
        TestService.addPuntosXPregunta(Alumno, Pregunta, Puntaje);
        String cadena = "Se inserto con exito";
        return cadena;
    }
    
    @POST
    @Path("/alumno/{Alumno}/rta/{Rta}")
    @Produces("application/json")
    public String addRtaMarcadas(@PathParam("Alumno") int Alumno,@PathParam("Rta") int Rta) throws Exception{
        TestService.addRtaMarcadas(Alumno, Rta);
        String cadena = "Se inserto con exito";
        return cadena;
    }
    
    
    
    @PUT
    @Path("/pregunta/{idPregunta}/alumno/{idAlumno}/puntos/{Puntaje}")
    
    public void updatePuntaje(@PathParam("idPregunta") int idPregunta,@PathParam("idAlumno") int idAlumno,@PathParam("Puntaje") int Puntaje) throws Exception{
        
         try {
            TestService.updatePuntosXPregunta(idAlumno, idPregunta, Puntaje);
        } catch (Exception ex) {
            Logger.getLogger(TestRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
