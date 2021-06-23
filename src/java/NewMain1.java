
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
import par2019.domain.model.entity.Test;
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
public class NewMain1 {

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
        
        
        
      /* Collection<Ranking> retValue = new ArrayList();
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
            list.stream().forEach((i) -> {
                System.out.println(i.toString());
           });
             /*for(int i = 0; i <= list.size(); i++) {                
                pstmt = c.prepareStatement("SELECT a.id, pe.nombre, pe.apellido, subquery1.puntos, subquery2.puntaje\n" +
                "FROM alumno a, persona pe, (SELECT cu.idcurso, m.idmateria, SUM(c.puntos) AS puntos\n" +
                "FROM curso cu, materia m, cuestionario c WHERE cu.idcurso = m.idcurso AND m.idmateria = c.idmateria \n" +
                "AND UPPER(cu.nombrecurso) LIKE UPPER(?) AND UPPER(m.nombremateria) LIKE UPPER(?)) AS subquery1,\n" +
                "(SELECT p.id, cu.idcurso, SUM(p.puntaje) AS puntaje\n" +
                "FROM puntuaciones p, cuestionario c, materia m, curso cu\n" +
                "WHERE p.idcuestionario = c.idcuestionario AND c.idmateria = m.idmateria AND cu.idcurso = m.idcurso\n" +
                "AND UPPER(cu.nombrecurso) LIKE UPPER(?) AND UPPER(m.nombremateria) LIKE UPPER(?)\n" +
                "AND p.id = ?) AS subquery2\n" +
                "WHERE subquery1.idcurso = subquery2.idcurso AND subquery2.id = a.id AND pe.id = a.id AND a.id = ?");
                pstmt.setString(1, "primeroTT");
                pstmt.setString(2, "Matematicas");
                pstmt.setString(3, "primeroTT");
                pstmt.setString(4, "Matematicas");
                pstmt.setInt(5, list.get(i));
                pstmt.setInt(6, list.get(i));
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    Ranking r=new Ranking(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("puntos"), rs.getInt("puntaje"));
                    System.out.println(r.toString());
                    lista.add(r);
                }
            } */  
            /*Collections.sort(lista,Collections.reverseOrder());
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
            }*/
        /******************************************************/
           Collection<Test> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        List<String> listaP = new ArrayList();
        List<Integer> listIndex = new ArrayList();
        List<Integer> listPunto= new ArrayList();
        List<Integer> listIdR= new ArrayList();
        List<Integer> listaE = new ArrayList();
        List<String> listaR = new ArrayList(); // Contiene las respuestas de cada pregunta
        final String link  = "http://192.168.0.10:8084/homeApi/rest/imageApi/image/";
        String image = "";
//        List list1 = new ArrayList();
//        List<String> list2 = new ArrayList();
//        list1.add(1);
//        list1.add(2);
//        list2.add("Mat");
//        list2.add("Cas");
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select p.idpregunta, p.pregunta, p.puntoasignado from pregunta p, cuestionario c \n" +
            "where c.idcuestionario = p.idcuestionario and c.idcuestionario = ? \n" +
            "ORDER BY p.idpregunta");
            
            pstmt.setInt(1, 75);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                //retValue.add(new Test());
                listaP.add(rs.getString("pregunta"));
                listIndex.add(rs.getInt("idpregunta"));
                listPunto.add(rs.getInt("puntoasignado"));
            }
            for(int i = 0; i < listaP.size(); i++) {
                pstmt = c.prepareStatement("select r.idrta, r.rtas, r.evaluacion from respuesta r, pregunta p, cuestionario c"
                        + " where r.idpregunta = p.idpregunta and p.idcuestionario = c.idcuestionario"
                        + " and p.idpregunta = ? ");
                pstmt.setInt(1, (int) listIndex.get(i));
                image = link + listIndex.get(i);
                rs = pstmt.executeQuery();
                int x = 0;
                while (rs.next()) {
                    listIdR.add(rs.getInt("idrta"));
                    listaR.add(rs.getString("rtas"));
                    //listaE.add(rs.getInt("evaluacion"));
                    if(rs.getInt("evaluacion")== 1) listaE.add(x);
                    x++;
                   
                }
                Test t=new Test((i+1), listaE, listIndex.get(i) , listaP.get(i), listPunto.get(i),image,listaR, listIdR);
                System.out.println(t.toString());        
                retValue.add(t);
                listaR = new ArrayList();
                listaE = new ArrayList();    
                listIdR = new ArrayList();
            }
//            retValue.add(new Test(1,list1,listaP.get(1),"imagen",list2));
           //return retValue;
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
