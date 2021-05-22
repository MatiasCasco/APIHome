/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import par2019.domain.model.entity.Entity;
import par2019.domain.model.entity.Test;
import par2019.util.DBUtils;

/**
 *
 * @author User
 */
public class JdbcTestRepository implements TestRepository<Test, Integer> {

    @Override
    public boolean containsIdCuestionario(int Cuestionario) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM cuestionario where idcuestionario = ?");

            pstmt.setInt(1, Cuestionario);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCursoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Collection<Test> Test(int Cuestionario) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        final String link  = "http://192.168.0.14:8084/homeApi/rest/imageApi/image/";
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
            
            pstmt.setInt(1, Cuestionario);
            
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
                retValue.add(new Test((i+1), listaE, listIndex.get(i) , listaP.get(i), listPunto.get(i),image,listaR, listIdR));
                listaR = new ArrayList();
                listaE = new ArrayList();    
                listIdR = new ArrayList();
            }
//            retValue.add(new Test(1,list1,listaP.get(1),"imagen",list2));
            return retValue;
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
        return retValue;
    }

    @Override
    public void add(Test entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Test entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Test> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addRtaMarcadas(int idAlumno, int idrta) throws Exception  {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("INSERT INTO rta_marcadas (idalumno, idrta) values (?, ?)");

            pstmt.setInt(1, idAlumno);
            pstmt.setInt(2, idrta);  
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcCursoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void addPuntosXPregunta(int idAlumno, int idPregunta, int puntoRealizado) throws Exception  {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("INSERT INTO rta_alumnos (idalumno, idpregunta, puntorealizado) values (?, ?, ?)");

            pstmt.setInt(1, idAlumno);
            pstmt.setInt(2, idPregunta);
            pstmt.setInt(3, puntoRealizado);  
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcCursoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void addPuntosXCuestionarios(int idAlumno, int idCuestionario, int puntoRealizado) throws Exception  {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("INSERT INTO puntuaciones (id, idcuestionario, puntaje) values (?, ?, ?)");

            pstmt.setInt(1, idAlumno);
            pstmt.setInt(2, idCuestionario);
            pstmt.setInt(3, puntoRealizado);  
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcCursoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean containsIdRta(int idRta) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM respuesta where idrta = ?");

            pstmt.setInt(1, idRta);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCursoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean containsIdAlumno(int idAlumno) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM alumno where id = ?");

            pstmt.setInt(1, idAlumno);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCursoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean containsIdPregunta(int idPregunta) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM pregunta where idpregunta = ?");

            pstmt.setInt(1, idPregunta);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCursoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
