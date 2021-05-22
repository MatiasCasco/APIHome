
package par2019.domain.repository;

import java.util.Collection;
import java.util.List;


/**
 *
 * @author User
 */
public interface TestRepository<Test, Integer> extends Repository<Test, Integer> {
     /**
     *
     * @param Cuestionario
     * @return
     */
    public boolean containsIdCuestionario(int Cuestionario); 
    /**
     *
     * @param Cuestionario
     * @return
     * @throws Exception
     */
    public Collection<Test> Test(int Cuestionario) throws Exception;

    /**
     *
     * @param idAlumno
     * @param idrta
     */
    public void addRtaMarcadas(int idAlumno, int idrta) throws Exception;
    
     /**
     *
     * @param idRta
     * @return
     */
    public boolean containsIdRta(int idRta); 
  
    /**
     *
     * @param idAlumno
     * @return
     */
    public boolean containsIdAlumno(int idAlumno); 
    
     /**
     *
     * @param idPregunta
     * @return
     */
    public boolean containsIdPregunta(int idPregunta); 
    
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
     * @param idCuestionario
     * @param puntoRealizado
     */
    public void addPuntosXCuestionarios(int idAlumno, int idCuestionario, int puntoRealizado) throws Exception;
}
