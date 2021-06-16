
import java.util.Collection;
import par2019.domain.model.entity.Curso;
import par2019.domain.model.entity.Materia;
import par2019.domain.model.entity.User;
import par2019.domain.repository.JdbcCursoRepository;
import par2019.domain.repository.JdbcMateriaRepository;
import par2019.domain.repository.JdbcUserRepository;
import par2019.domain.service.CursoServiceImpl;
import par2019.domain.service.MateriaServiceImpl;
import par2019.domain.service.UserServiceImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauricio
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       /* UserServiceImpl usi = new UserServiceImpl(new JdbcUserRepository());
        User user = new User(0, "Mauricio", "Machuca", "m.machuca@pol.una.py", "m.machuca", "mauricio2019", 0);
        usi.add(user);*/

//        usi.delete(1);
        /*CursoServiceImpl curs= new CursoServiceImpl(new JdbcCursoRepository());
        Collection<Curso> c =curs.findByIdProfesor(1);
        for (Curso i:c){
            System.out.println(i.toString());
        }*/
        MateriaServiceImpl matS= new MateriaServiceImpl(new JdbcMateriaRepository());
        Collection<Materia> materias=matS.findByProfesor(26);
        for(Materia m: materias){
            System.out.println(m.toString());
        }
    }
    
}
