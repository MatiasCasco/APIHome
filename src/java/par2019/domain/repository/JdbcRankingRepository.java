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
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import par2019.domain.model.entity.Entity;
import par2019.domain.model.entity.Ranking;
import par2019.util.DBUtils;

/**
 *
 * @author User
 */
public class JdbcRankingRepository implements RankingRepository<Ranking, Integer> {

    @Override
    public boolean containsIdCuestionario(int idCuestionario) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM cuestionario where idcuestionario = ?");

            pstmt.setInt(1, idCuestionario);

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
    public Collection<Ranking> listaPuntajes(int idCuestionario) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Ranking> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT p.nombre, p.apellido, pu.puntaje from puntuaciones pu, persona p\n" +
            "where pu.id = p.id and pu.idcuestionario = ? ORDER BY pu.puntaje DESC");
            pstmt.setInt(1, idCuestionario);

            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Ranking(rs.getString("nombre"),rs.getString("apellido"), rs.getInt("puntaje")));
            }
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
    public boolean containsIdAlumno(int idAlumno) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM persona where id = ?");
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
    public Collection<Ranking> rankingGlobal(String nameCurso, String nameMateria) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                pstmt.setString(1, nameCurso);
                pstmt.setString(2, nameMateria);
                pstmt.setString(3, nameCurso);
                pstmt.setString(4, nameMateria);
                pstmt.setInt(5, list.get(i));
                pstmt.setInt(6, list.get(i));
                rs = pstmt.executeQuery();
                while (rs.next()) {  
                    lista.add(new Ranking(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("puntos"), rs.getInt("puntaje")));
                }
            }   
            Collections.sort(lista,Collections.reverseOrder());
            for(int i = 0; i < list.size(); i++) {
                retValue.add(lista.get(i));
            }    
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
    public void add(Ranking entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Ranking entity) {
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
    public Collection<Ranking> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   

    @Override
    public Collection<Ranking> rankingGlobalbyId(int idCurso, int idMateria) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                pstmt.setInt(1, idCurso);
                pstmt.setInt(2, idMateria);
                pstmt.setInt(3, idCurso);
                pstmt.setInt(4, idMateria);
                pstmt.setInt(5, list.get(i));
                //pstmt.setInt(6, list.get(i));
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    Ranking r=new Ranking(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("puntos"), rs.getInt("puntaje"));
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
        return retValue;
    }
    
    

    @Override
    public Collection<Ranking> listaPuntajesC(int idCuestionario) {
       Collection<Ranking> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT p.id, p.nombre, p.apellido, pu.puntaje \n" +
                "from puntuaciones pu join persona p\n" +
                "on pu.id = p.id where pu.idcuestionario = ? ORDER BY pu.puntaje DESC");
            pstmt.setInt(1, idCuestionario);

            rs = pstmt.executeQuery();
            Ranking r;
            while (rs.next()) {  
                 r=new Ranking(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido"), rs.getInt("puntaje"));
                 retValue.add(r);
               
            }
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
    
}
