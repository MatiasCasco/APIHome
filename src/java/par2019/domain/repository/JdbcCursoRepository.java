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
import java.util.logging.Level;
import java.util.logging.Logger;
import par2019.domain.model.entity.Curso;
import par2019.domain.model.entity.Entity;
import par2019.util.DBUtils;

/**
 *
 * @author Matias
 */
public class JdbcCursoRepository implements CursoRepository<Curso, Integer> {
    // Criteria significaba en contrar los cursos de un profesor
    @Override
    public Collection<Curso> findByCriteria(String Criteria) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Curso> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select c.idcurso, c.idprofesor, c.nombrecurso, c.claveprofesor,"
                    + " c.clavealumno from curso c, persona p\n" +
            "where c.idprofesor = p.id and UPPER(p.nombre) like UPPER(?) and p.rol = 1");
            pstmt.setString(1, Criteria);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Curso(rs.getInt("idcurso"), rs.getString("nombrecurso"),rs.getInt("idprofesor"), rs.getString("claveprofesor"), rs.getString("clavealumno")));
            }
            
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
    public Collection<Curso> findByNameCurso(String Name) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Curso> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM curso WHERE UPPER(nombrecurso) like UPPER(?)");
            pstmt.setString(1, Name);

            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Curso(rs.getInt("idcurso"), rs.getString("nombrecurso"),rs.getInt("idprofesor"), rs.getString("claveprofesor"), rs.getString("clavealumno")));
            }
//            } else {
//                retValue = new Curso(0,null,null,0,null);
//            }

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
    public boolean containsId(int Id) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM curso where idcurso = ?");

            pstmt.setInt(1, Id);

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
    public boolean containsNameCurso(String Curso) {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
//        try {
//            Curso curso = (Curso) this.findByNameCurso(Curso);
//            if(Curso.equals(curso.getNombre())){
//                return true;
//            } ;
//        } catch (Exception ex) {
//            //Exception Handler
//        }
//        return false;
    }
    
    @Override
    public boolean containsNameProfesor(String Name) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM persona where nombre = ?");

            pstmt.setString(1, Name);

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
    public void add(Curso entity) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("INSERT INTO curso (idprofesor, nombrecurso, claveprofesor, clavealumno) values (?, ?, ?, ?)");

            pstmt.setInt(1, (entity.getIdProfesor() == 0)?4:entity.getIdProfesor() );
            pstmt.setString(2, entity.getNombre());
            pstmt.setString(3, entity.getClaveProfesor());
            pstmt.setString(4, entity.getClaveAlumno());
            
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
    public void remove(Integer id) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("DELETE FROM curso WHERE idcurso = ?");

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
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
    public void update(Curso entity) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("UPDATE curso SET idprofesor = ?, nombrecurso = ?, claveprofesor = ?, clavealumno = ? WHERE idcurso = ?");

            pstmt.setInt(1, entity.getIdProfesor());
            pstmt.setString(2, entity.getNombre());
            pstmt.setString(3, entity.getClaveProfesor());
            pstmt.setString(4, entity.getClaveAlumno());
            pstmt.setInt(5, entity.getIdCurso());
            
            pstmt.executeUpdate();
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
    public boolean contains(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Curso> getAll() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Curso> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM curso");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Curso(rs.getInt("idcurso"), rs.getString("nombrecurso"), rs.getInt("idprofesor"), rs.getString("claveprofesor"), rs.getString("clavealumno")));
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
    public Curso findByIdCurso(int id) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Curso retValue = null;
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM curso WHERE idcurso = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                retValue = new Curso(rs.getInt("idcurso"), rs.getString("nombrecurso"), rs.getInt("idprofesor"), rs.getString("claveprofesor"), rs.getString("clavealumno"));
            } else {
                retValue = new Curso(0," ",0," "," ");
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
