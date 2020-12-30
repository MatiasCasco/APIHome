/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.service;

import java.util.Collection;
import par2019.domain.model.entity.Cuestionario;
import par2019.domain.repository.CuestionarioRepository;
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
    public Collection<Cuestionario> findByNameMateria(String NameMateria) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!CuestionarioRepository.ContainsNameMateria(NameMateria)){
            throw new Exception(String.format("No se encontro el nombre del profesor %s", NameMateria)); 
        }
        return CuestionarioRepository.findByNameMateria(NameMateria);
    }

    @Override
    public Cuestionario getCuestionario(int idCuestionario) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!CuestionarioRepository.ContainsIdCuestionario(idCuestionario)){
            throw new Exception(String.format("No se encontro el identificador %d del cuestionario", idCuestionario)); 
        }
        return CuestionarioRepository.getCuestionario(idCuestionario);
    }
    
}
