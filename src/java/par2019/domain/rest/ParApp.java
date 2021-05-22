/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Mauricio
 */
public class ParApp extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public ParApp() {
        singletons.add(new UserRestService());
        singletons.add(new CursoRestService());
        singletons.add(new MateriaRestService());
        singletons.add(new CuestionarioRestService());
        singletons.add(new PreguntaRestService());
        singletons.add(new RespuestasRestService());
        singletons.add(new RetoRestService());
        singletons.add(new ImageRestService());
        singletons.add(new TestRestService());
        singletons.add(new PuntuacionRestService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
