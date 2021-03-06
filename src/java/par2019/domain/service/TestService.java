/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Test;
import par2019.domain.model.entity.reporte;
import par2019.domain.repository.TestRepository;

/**
 *
 * @author User
 */
public interface TestService {
     /**
     *
     * @param Cuestionario
     * @return
     * @throws Exception
     */
    public Collection<Test> Test(int Cuestionario) throws Exception;
     /**
     *
     * @param Cuestionario
     * @return
     * @throws Exception
     */
    public Collection<Test> WebTest(int Cuestionario) throws Exception;
    //public Collection<String>Test(int Cuestionario) throws Exception;
     /**
     *
     * @param idAlumno
     * @param idrta
     */
    public void addRtaMarcadas(int idAlumno, int idrta)throws Exception;

    /**
     *
     * @param idAlumno
     * @param idPregunta
     * @param puntoRealizado
     */
    public void addPuntosXPregunta(int idAlumno, int idPregunta, int puntoRealizado) throws Exception;
     /**
     *
     * @param idAlumno
     * @param idPregunta
     * @param puntoObtenido
     */
    public void updatePuntosXPregunta(int idAlumno, int idPregunta, int puntoObtenido) throws Exception;

    /**
     *
     * @param idAlumno
     * @param idCuestionario
     * @param puntoRealizado
     */
    public void addPuntosXCuestionarios(int idAlumno, int idCuestionario, int puntoRealizado) throws Exception;
    
    /**
     *
     * @param Cuestionario
     * @param Alumno
     * @return
     * @throws Exception
     */
    public Collection<Test> TestRecover(int Cuestionario, int Alumno) throws Exception;
    
     /**
     *
     * @param Cuestionario
     * @param Alumno
     * @return
     * @throws Exception
     */
    public Collection<Test> TestRecuperacion(int Cuestionario, int Alumno) throws Exception;
    
     /**
     *
     * @param Cuestionario
     * @return
     * @throws Exception
     */
    public Collection<reporte> reporteTest(int Cuestionario) throws Exception;
}
