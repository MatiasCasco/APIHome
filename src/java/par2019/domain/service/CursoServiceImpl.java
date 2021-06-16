/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Curso;
import par2019.domain.model.entity.Entity;
import par2019.domain.repository.CursoRepository;
import par2019.domain.repository.Repository;
//import par2019.domain.repository.Repository;


/**
 *
 * @author Matias
 */
public class CursoServiceImpl extends BaseService<Curso, Integer> implements CursoService {
    CursoRepository<Curso, Integer>  CursoRepository;

    /**
     *
     * @param CursoRepository
     */
    
    public CursoServiceImpl(CursoRepository<Curso, Integer> CursoRepository) {
        super(CursoRepository);
        this.CursoRepository = CursoRepository;
    }

   
    @Override
    public void update(Curso curso) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0; 
        if (curso.getIdCurso() == 0) {
            bandera = 1;
            throw new Exception("el id no puede ser 0");
        }
        
        if (curso.getNombre() == null || "".equals(curso.getNombre())) {
            bandera = 1;
            throw new Exception("La descripcion no puede ser nulo o cadena vacia.");
        }
        //considerar id categoria y aggregar        //super.add(product);
        if(bandera == 0){
            CursoRepository.update(curso);
        }        
    }

    @Override
    public void delete(Integer id) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0; 
        if (!CursoRepository.containsId(id)) {
            bandera = 1;
            throw new Exception(String.format("No existe el curso con el id %s ", id));
        }
        if(bandera == 0){CursoRepository.remove(id);}   
    }

    @Override
    public Collection<Curso> findByCriteria(String Criteria) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!CursoRepository.containsNameProfesor(Criteria)){
            throw new Exception(String.format("No se encontro el nombre del profesor %s", Criteria)); 
        }
        return CursoRepository.findByCriteria(Criteria);
    }
    
    

    @Override
    public Curso findByIdCurso(int Id) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!CursoRepository.containsId(Id)){
            throw new Exception(String.format("No se encontro el identificador %d del curso", Id)); 
        }
        return CursoRepository.findByIdCurso(Id);
    }
    
    @Override
    public Collection<Curso> findByNameCurso(String Name) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        if(!CursoRepository.containsNameCurso(Name)){
//            throw new Exception(String.format("No se encontro el nombre del curso %s", Name));
//        }
        return CursoRepository.findByNameCurso(Name);
    }

    @Override
    public void insert(Curso curso) throws Exception {
        int bandera = 0;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (CursoRepository.containsNameCurso(curso.getNombre()) == true){
            bandera = 1;
            throw new Exception(String.format("Ya existe el nombre del curso %s ", curso.getNombre()));
        }
       
        if (curso.getNombre() == null || "".equals(curso.getNombre())) {
            bandera = 1;
            throw new Exception("La descripcion no puede ser nulo o cadena vacia.");
        }
        
        if(bandera == 0){
            CursoRepository.add(curso);
        }        
    }
    
     @Override
    public Collection<Curso> findByIdProfesor(int idProfesor) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        if(!CursoRepository.containsNameCurso(Name)){
//            throw new Exception(String.format("No se encontro el nombre del curso %s", Name));
//        }
        return CursoRepository.findByIdProfesor(idProfesor);
    }
    
}
