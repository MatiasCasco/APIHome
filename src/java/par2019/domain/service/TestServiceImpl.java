/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import java.util.List;
import par2019.domain.model.entity.Test;
import par2019.domain.repository.TestRepository;

/**
 *
 * @author User
 */
public class TestServiceImpl extends BaseService<Test, Integer> implements TestService{
     TestRepository<Test, Integer> TestRepository;

    /**
     *
     * @param RetoRepository
     */
    public TestServiceImpl(TestRepository<Test, Integer> TestRepository) {
    super(TestRepository);
    this.TestRepository = TestRepository;
    }

    @Override
    public Collection<Test> Test(int Cuestionario) throws Exception {
    //public Collection<String> Test(int Cuestionario) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!TestRepository.containsIdCuestionario(Cuestionario)){
            throw new Exception(String.format("No se encontro el identificador del cuestionario %d", Cuestionario)); 
        }
        return TestRepository.Test(Cuestionario);
    }

    @Override
    public void addRtaMarcadas(int idAlumno, int idrta) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (TestRepository.containsIdRta(idrta)== false){
            bandera = 1;
            throw new Exception(String.format("No existe en BD el parametro %d", idrta));
        }
       
        if (TestRepository.containsIdAlumno(idAlumno)== false) {
            bandera = 1;
            throw new Exception(String.format("No existe el id del alumno %d",idAlumno));
        }
        
        if(bandera == 0){
            TestRepository.addRtaMarcadas(idAlumno, idrta);
        }        
    }

    @Override
    public void addPuntosXPregunta(int idAlumno, int idPregunta, int puntoRealizado) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (TestRepository.containsIdPregunta(idPregunta)== false){
            bandera = 1;
            throw new Exception(String.format("No existe en BD el parametro %d", idPregunta));
        }
       
        if (TestRepository.containsIdAlumno(idAlumno)== false) {
            bandera = 1;
            throw new Exception(String.format("No existe el id del alumno %d",idAlumno));
        }
        
        if(bandera == 0){
            TestRepository.addPuntosXPregunta(idAlumno, idPregunta, puntoRealizado);
        }        
    }

    @Override
    public void addPuntosXCuestionarios(int idAlumno, int idCuestionario, int puntoRealizado) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        if (TestRepository.containsIdCuestionario(idCuestionario)== false){
//            bandera = 1;
//            throw new Exception(String.format("No existe en BD el parametro %d", idCuestionario));
//        }
//       
//        if (TestRepository.containsIdAlumno(idAlumno)== false) {
//            bandera = 1;
//            throw new Exception(String.format("No existe el id del alumno %d",idAlumno));
//        }
        
        if(bandera == 0){
            TestRepository.addPuntosXCuestionarios(idAlumno, idCuestionario, puntoRealizado);
        }  
    }
    
    
}
