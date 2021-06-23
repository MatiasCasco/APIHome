
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import par2019.domain.model.entity.Curso;
import par2019.domain.model.entity.Materia;
import par2019.domain.model.entity.Ranking;
import par2019.domain.model.entity.User;
import par2019.domain.repository.JdbcCursoRepository;
import par2019.domain.repository.JdbcMateriaRepository;
import par2019.domain.repository.JdbcRankingRepository;
import par2019.domain.repository.JdbcUserRepository;
import par2019.domain.service.CursoServiceImpl;
import par2019.domain.service.MateriaServiceImpl;
import par2019.domain.service.RankingServiceImpl;
import par2019.domain.service.UserServiceImpl;
import par2019.util.DBUtils;

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
        /*MateriaServiceImpl matS= new MateriaServiceImpl(new JdbcMateriaRepository());
        Collection<Materia> materias=matS.findByProfesor(26);
        for(Materia m: materias){
            System.out.println(m.toString());
        }*/
       /* RankingServiceImpl rs= new RankingServiceImpl(new JdbcRankingRepository());
        Collection<Ranking> rks= rs.rankingGlobalById(1, 22);
        for (Ranking r : rks) {
            System.out.println(r.toString());
            
        }*/
        
        
        
        Collection<Ranking> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Integer> list = new ArrayList();
        List<Ranking> lista = new ArrayList();
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT id FROM alumno");
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                //retValue.add(new Ranking(rs.getString("nombre"),rs.getString("apellido"), rs.getInt("puntaje")));
                list.add(rs.getInt("id"));
            }
            for(int i = 0; i < list.size(); i++) {
                System.out.println("posicion: "+i+" id alumno:" +list.get(i));
                /*pstmt = c.prepareStatement("SELECT a.id, pe.nombre, pe.apellido, subquery1.puntos, subquery2.puntaje\n" +
                          "FROM alumno a , persona pe , \n" +
                               "(SELECT cu.idcurso, m.idmateria, SUM(c.puntos) AS puntos\n" +
                                "FROM curso cu JOIN materia m join cuestionario c on cu.idcurso = m.idcurso AND m.idmateria = c.idmateria \n" +
                                "WHERE cu.idcurso = ?  AND m.idmateria = ? \n" +
                            ") AS subquery1 ,\n" +
                            "(SELECT p.id, cu.idcurso, SUM(p.puntaje) AS puntaje\n" +
                                "FROM puntuaciones p join cuestionario c join materia m join curso cu\n" +
                                    "on p.idcuestionario = c.idcuestionario AND c.idmateria = m.idmateria AND cu.idcurso = m.idcurso\n" +
                                    "WHERE cu.idcurso=?  AND m.idmateria=? AND p.id = ? \n" +
                                ") AS subquery2\n" +
                            "where subquery1.idcurso = subquery2.idcurso AND subquery2.id = a.id AND pe.id = a.id"
                        + "");*/
                pstmt = c.prepareStatement("SELECT a.id, pe.nombre, pe.apellido, subquery1.puntos, subquery2.puntaje\n" +
                          "FROM alumno a join persona pe join \n" +
                               "(SELECT cu.idcurso, m.idmateria, SUM(c.puntos) AS puntos\n" +
                                "FROM curso cu JOIN materia m join cuestionario c on cu.idcurso = m.idcurso AND m.idmateria = c.idmateria \n" +
                                "WHERE cu.idcurso = ?  AND m.idmateria = ? \n" +
                            ") AS subquery1 join\n" +
                            "(SELECT p.id, cu.idcurso, SUM(p.puntaje) AS puntaje\n" +
                                "FROM puntuaciones p join cuestionario c join materia m join curso cu\n" +
                                    "on p.idcuestionario = c.idcuestionario AND c.idmateria = m.idmateria AND cu.idcurso = m.idcurso\n" +
                                    "WHERE cu.idcurso=?  AND m.idmateria=? AND p.id = ? \n" +
                                ") AS subquery2\n" +
                            "on subquery1.idcurso = subquery2.idcurso AND subquery2.id = a.id AND pe.id = a.id"
                        + "");
                pstmt.setInt(1, 1);
                pstmt.setInt(2, 22);
                pstmt.setInt(3, 1);
                pstmt.setInt(4, 22);
                pstmt.setInt(5, list.get(i));
                //pstmt.setInt(6, list.get(i));
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    Ranking r=new Ranking(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("puntos"), rs.getInt("puntaje"));
                    System.out.println(r.toString());
                    lista.add(r);
                }
            }   
            Collections.sort(lista,Collections.reverseOrder());
            lista.stream().forEach((r) -> {
                retValue.add(r);
             });   
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcCursoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
}
