/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.rest;

import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import par2019.domain.model.entity.Puntuacion;
import par2019.domain.model.entity.Ranking;
import par2019.domain.repository.JdbcPuntuacionRepository;
import par2019.domain.repository.JdbcRankingRepository;
import par2019.domain.service.PuntuacionServiceImpl;
import par2019.domain.service.RankingServiceImpl;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("puntajeApi")
public class PuntuacionRestService {
    private final PuntuacionServiceImpl puntuacionService = new PuntuacionServiceImpl(new JdbcPuntuacionRepository());
    private final RankingServiceImpl rankingService = new RankingServiceImpl(new JdbcRankingRepository());
    @GET
    @Path("/resueltos/{idAlumno}")
    @Produces("application/json")
    public ArrayList<Puntuacion> getResueltos(@PathParam("idAlumno") int idAlumno) throws Exception {
        ArrayList<Puntuacion> cuestionariosSoluccionados = (ArrayList<Puntuacion>) puntuacionService.listaCuestionariosResueltos(idAlumno);
        return cuestionariosSoluccionados;
    }
    
    @GET
    @Path("/ranking/{idCuestionario}")
    @Produces("application/json")
    public ArrayList<Ranking> getRanking(@PathParam("idCuestionario") int idCuestionario) throws Exception {
        ArrayList<Ranking> ranking = (ArrayList<Ranking>) rankingService.listaPuntajes(idCuestionario);
        return ranking;
    }
    
    @GET
    @Path("/rankingGlobal/curso/{nameCurso}/materia/{nameMateria}")
    @Produces("application/json")
    public ArrayList<Ranking> getRankingGlobal(@PathParam("nameCurso") String nameCurso, @PathParam("nameMateria") String nameMateria) throws Exception {
        ArrayList<Ranking> rankingG = (ArrayList<Ranking>) rankingService.rankingGlobal(nameCurso, nameMateria);
        return rankingG;
    }    
}
