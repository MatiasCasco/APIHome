/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.repository;

import java.util.Collection;

/**
 *
 * @author User
 */
public interface PuntuacionRepository<Puntuacion, Integer> extends Repository<Puntuacion, Integer> {
     /**
     *
     * @param idAlumno
     * @return
     */
    public boolean containsIdAlumno(int idAlumno); 
    
     /**
     *
     * @param idCuestionario
     * @return
     */
    public boolean containsIdCuestionario(int idCuestionario);
    
    /**
     *
     * @param idAlumno
     * @return
     */
    public Collection<Puntuacion> listaCuestionariosResueltos(int idAlumno);
}
