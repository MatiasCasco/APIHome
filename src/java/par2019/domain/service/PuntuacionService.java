/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Puntuacion;

/**
 *
 * @author User
 */
public interface PuntuacionService {
     /**
     *  Retorna la estructura la lista de Cuestionarios resueltos por un alumno
     * @param idAlumno
     * @return
     * @throws Exception
     */
    public Collection<Puntuacion> listaCuestionariosResueltos(int idAlumno) throws Exception;
}
