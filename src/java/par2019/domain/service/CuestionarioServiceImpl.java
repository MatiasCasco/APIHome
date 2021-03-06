/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Cuestionario;
import par2019.domain.repository.CuestionarioRepository;
import par2019.domain.model.entity.Grafica;
import par2019.domain.repository.Repository;

/**
 *
 * @author Matias
 */
public class CuestionarioServiceImpl extends BaseService<Cuestionario, Integer>  implements CuestionarioService{
    CuestionarioRepository<Cuestionario, Integer> CuestionarioRepository;

    public CuestionarioServiceImpl(CuestionarioRepository<Cuestionario, Integer> CuestionarioRepository) {
        super(CuestionarioRepository);
        this.CuestionarioRepository = CuestionarioRepository;
    }
    
    @Override
    public void insert(Cuestionario cuestionario) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0;
        if (CuestionarioRepository.ContainsMateria(cuestionario.getIdMateria()) == false){
            bandera = 1;
            throw new Exception(String.format("No existe la materia %d ", cuestionario.getIdMateria()));
        }
               
        if(bandera == 0){
            CuestionarioRepository.add(cuestionario);
        } 
    }

    @Override
    public void update(Cuestionario cuestionario) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0; 
        if (CuestionarioRepository.ContainsIdCuestionario(cuestionario.getIdCuestionario()) == false) {
            bandera = 1;
            throw new Exception(String.format("Ya existe el nombre del curso %d ", cuestionario.getIdCuestionario()));
        }
        
        if (CuestionarioRepository.ContainsMateria(cuestionario.getIdMateria()) == false){
            bandera = 1;
            throw new Exception(String.format("Ya existe el nombre del curso %d ", cuestionario.getIdMateria()));
        }
        
        if(bandera == 0){
            CuestionarioRepository.update(cuestionario);
        }  
    }

    @Override
    public void delete(Integer id) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0; 
        if (!CuestionarioRepository.ContainsIdCuestionario(id)) {
            bandera = 1;
            throw new Exception(String.format("No existe el curso con el id %s ", id));
        }
        if(bandera == 0){CuestionarioRepository.remove(id);}
    }

    @Override
    public Collection<Cuestionario> findByNameMateria(String nameMateria) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!CuestionarioRepository.ContainsNameMateria(nameMateria)){
            throw new Exception(String.format("No se encontro el nombre de la materia %s", nameMateria)); 
        }
        return CuestionarioRepository.findByNameMateria(nameMateria);
    }

    @Override
    public Cuestionario getCuestionario(int idCuestionario) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!CuestionarioRepository.ContainsIdCuestionario(idCuestionario)){
            throw new Exception(String.format("No se encontro el identificador %d del cuestionario", idCuestionario)); 
        }
        return CuestionarioRepository.getCuestionario(idCuestionario);
    }

    @Override
    public Collection<Cuestionario> findByNameCurso(String nameCurso) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!CuestionarioRepository.ContainsNameCurso(nameCurso)){
            throw new Exception(String.format("No se encontro el nombre del curso %s", nameCurso)); 
        }
        return CuestionarioRepository.findByNameCurso(nameCurso);
    }

    @Override
    public Collection<Cuestionario> findByNameCursoAndMateria(String nameCurso, String nameMateria) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!CuestionarioRepository.ContainsNameCurso(nameCurso)){
            throw new Exception(String.format("No se encontro el nombre del curso %s", nameCurso)); 
        }
        if(!CuestionarioRepository.ContainsNameMateria(nameMateria)){
            throw new Exception(String.format("No se encontro el nombre de la materia %s", nameMateria)); 
        }
        return CuestionarioRepository.findByNameCursoAndMateria(nameCurso, nameMateria);
    }

    @Override
    public Collection<Cuestionario> findByAlumnoAndMateria(int idAlumno, int idMateria) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!CuestionarioRepository.ContainsAlumno(idAlumno)){
            throw new Exception(String.format("No se encontro el identificador del alumno %d", idAlumno)); 
        }
        if(!CuestionarioRepository.ContainsMateria(idMateria)){
            throw new Exception(String.format("No se encontro el nombre de la materia %d", idMateria)); 
        }
        return CuestionarioRepository.findByAlumnoAndMateria(idAlumno, idMateria);
    }

     @Override
    public Collection<Cuestionario> findByNameCursoAndMateriaApp(String Curso, String Materia) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!CuestionarioRepository.ContainsNameCurso(Curso)){
            throw new Exception(String.format("No se encontro el nombre del curso %s", Curso)); 
        }
        if(!CuestionarioRepository.ContainsNameMateria(Materia)){
            throw new Exception(String.format("No se encontro el nombre de la materia %s", Materia)); 
        }
        return CuestionarioRepository.findByNameCursoAndMateriaApp(Curso, Materia);

    }
    
    @Override
    public Collection<Cuestionario> findByIdMateria(int idMateria) throws Exception {
        
        if (CuestionarioRepository.ContainsMateria(idMateria) == false){
            
            throw new Exception(String.format("No existe la materia %d ",idMateria));
        }
        return CuestionarioRepository.findByIdMateria(idMateria);
    
    }

    @Override
    public Collection<Cuestionario> findByIdProfesor(int idProfesor) throws Exception {
        return CuestionarioRepository.findByIdProfesor(idProfesor);
    }
 
    @Override
    public Collection<Grafica> resumenEvaluacion(int idCuestionario) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//         if (CuestionarioRepository.ContainsIdCuestionario(idCuestionario) == false){
//            
//            throw new Exception(String.format("No existe el curso %d ", idCuestionario));
//        }
        return CuestionarioRepository.resumenEvaluacion(idCuestionario);
    }

    @Override
    public Collection<Grafica> cuestionarioAlumnos(int idCuestionario) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!CuestionarioRepository.ContainsIdCuestionario(idCuestionario)){
            throw new Exception(String.format("No se encontro el identificador %d del cuestionario", idCuestionario)); 
        }
        return CuestionarioRepository.cuestionarioAlumnos(idCuestionario);
    }   
}
