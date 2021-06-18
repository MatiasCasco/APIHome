/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Materia;
import par2019.domain.repository.MateriaRepository;
import par2019.domain.repository.Repository;

/**
 *
 * @author Matias
 */
public class MateriaServiceImpl extends BaseService<Materia, Integer> implements MateriaService {
    MateriaRepository<Materia, Integer> MateriaRepository;

    /**
     *
     * @param MateriaRepository
     */
    public MateriaServiceImpl(MateriaRepository<Materia, Integer> MateriaRepository) {
        super(MateriaRepository);
        this.MateriaRepository = MateriaRepository;
    }
    
    @Override
    public void insert(Materia materia) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0;
        if (MateriaRepository.ContainsIdCurso(materia.getIdCurso()) == false){
            bandera = 1;
            throw new Exception(String.format("No existe el curso ", materia.getIdCurso()));
        }
       
        if (materia.getNombre() == null || "".equals(materia.getNombre())) {
            bandera = 1;
            throw new Exception("La descripcion no puede ser nulo o cadena vacia.");
        }
        
        if(MateriaRepository.ContainsNameMateria(materia.getIdCurso(), materia.getNombre()) == true){
            bandera = 1;
            throw new Exception("El nombre de la materia ya existe en el curso.");
        }
        
        if(bandera == 0){
            MateriaRepository.add(materia);
        }      
    }

    @Override
    public void update(Materia materia) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0; 
        if (materia.getIdCurso() == '0') {
            bandera = 1;
            throw new Exception("el id del curso no puede estar vacio");
        }
        
        if (materia.getNombre() == null || "".equals(materia.getNombre())) {
            bandera = 1;
            throw new Exception("La descripcion no puede ser nulo o cadena vacia.");
        }
        //considerar id categoria y aggregar        //super.add(product);
        if(bandera == 0){
            MateriaRepository.update(materia);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0; 
        if (!MateriaRepository.contains(id)) {
            bandera = 1;
            throw new Exception(String.format("No existe el curso con el id %s ", id));
        }
        if(bandera == 0){MateriaRepository.remove(id);}  
    }

    @Override
    public Materia findMateriaInCurso(String NameCurso, String NameMateria) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bandera = 0;
        Materia materia = null;
        if (NameMateria.equals(null) || "".equals(NameMateria)) {
            bandera = 1;
            throw new Exception("La descripcion no puede ser nulo o cadena vacia.");
        }
        if (NameCurso.equals(null) || "".equals(NameCurso)) {
            bandera = 1;
            throw new Exception("La descripcion no puede ser nulo o cadena vacia.");
        }
        if(bandera == 0){
            materia = MateriaRepository.findMateriaInCurso(NameCurso, NameMateria);
        }
        return materia;
    }

    @Override
    public Collection<Materia> findByMateriasCurso(String NameCurso) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(NameCurso.equals(" ") || NameCurso.equals(null)){
            throw new Exception(String.format("El nombre del curso no puede ser ni null, ni una cadena vacio"));
        }
        return MateriaRepository.findByMateriasCurso(NameCurso);
    }

    @Override
    public Collection<Materia> findByNameMateria(String NameMateria) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(NameMateria.equals(" ") || NameMateria.equals(null)){
            throw new Exception(String.format("El nombre de la materia no puede ser ni null, ni una cadena vacio"));
        }
        return MateriaRepository.findByNameMateria(NameMateria);
    }

    @Override
    public Materia findByIdMateria(int idMateria) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         if(!MateriaRepository.ContainsIdMateria(idMateria)){
            throw new Exception(String.format("No se encontro el identificador %d de la materia", idMateria)); 
        }
        return MateriaRepository.getMateria(idMateria);
    }

    @Override
    public Collection<Materia> findByProfesor(int idProfesor) throws Exception {
        /*if(){
            throw new Exception(String.format("El nombre de la materia no puede ser ni null, ni una cadena vacio"));
        }*/
        return MateriaRepository.findByProfesor(idProfesor);
    }

    @Override
    public Collection<Materia> findByCurso(int idCurso) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
        if (MateriaRepository.ContainsIdCurso(idCurso) == false){
           
            throw new Exception(String.format("No existe el curso ",idCurso));
        }
        return MateriaRepository.findByCurso(idCurso);
    
    }

    @Override
    public Collection<Materia> findByMateriasCursoDisponible(String NameCurso) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(NameCurso.equals(" ") || NameCurso.equals(null)){
            throw new Exception(String.format("El nombre del curso no puede ser ni null, ni una cadena vacio"));
        }
        return MateriaRepository.findByMateriasCursoDisponible(NameCurso);
    }

    @Override
    public Collection<Materia> findByMateriasCursoTestDisponible(String NameCurso) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(NameCurso.equals(" ") || NameCurso.equals(null)){
            throw new Exception(String.format("El nombre del curso no puede ser ni null, ni una cadena vacio"));
        }
        return MateriaRepository.findByMateriasCursoTestDisponible(NameCurso);
    }
 
}
