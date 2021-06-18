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
import par2019.domain.model.entity.Entity;
import par2019.domain.model.entity.Materia;
import par2019.util.DBUtils;

/**
 *
 * @author Matias
 */
public class JdbcMateriaRepository implements MateriaRepository<Materia, Integer>{

    @Override
    public boolean ContainsNameMateria(int idCurso, String materia){
        try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select * from materia where idcurso = ? and UPPER(nombremateria) like UPPER(?)");
            pstmt.setInt(1, idCurso);
            pstmt.setString(2, materia);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public Materia findMateriaInCurso(String NameCurso, String NameMateria) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Materia retValue = null;

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT m.idmateria, m.idcurso, m.nombremateria,c.nombrecurso FROM materia m, curso c WHERE m.idcurso = c.idcurso and UPPER(M.nombremateria) like UPPER(?) and UPPER(c.nombrecurso) like UPPER(?) ORDER BY m.idmateria");
            pstmt.setString(1, NameMateria);
            pstmt.setString(2, NameCurso);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue = new Materia(rs.getInt("idmateria"), rs.getString("nombremateria"), rs.getInt("idcurso"), rs.getString("nombrecurso"));
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
                Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }

    @Override
    public Materia getMateria(int idMateria) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Materia retValue = null;

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT m.idmateria, m.idcurso, m.nombremateria,c.nombrecurso FROM materia m, curso c WHERE m.idcurso = c.idcurso and idmateria = ?");
            pstmt.setInt(1, idMateria);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new Materia(rs.getInt("idmateria"), rs.getString("nombremateria"), rs.getInt("idcurso"), rs.getString("nombrecurso"));
            } else {
                retValue = new Materia(0, " ", 0," ");
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
                Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }

    @Override
    public Collection<Materia> findByNameMateria(String NameMateria) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Materia> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT m.idmateria, m.idcurso, m.nombremateria,c.nombrecurso FROM materia m, curso c WHERE m.idcurso = c.idcurso and UPPER(m.nombremateria) = UPPER(?) ORDER BY m.idmateria");
            pstmt.setString(1, NameMateria);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Materia(rs.getInt("idmateria"), rs.getString("nombremateria"), rs.getInt("idcurso"), rs.getString("nombrecurso")));
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
                Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }
    
    @Override
    public Collection<Materia> findByMateriasCurso(String NameCurso) throws Exception {
        Collection<Materia> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT m.idmateria, m.idcurso, m.nombremateria,c.nombrecurso FROM materia m, curso c WHERE m.idcurso = c.idcurso and UPPER(c.nombrecurso) like UPPER(?) ORDER BY m.idmateria");
            pstmt.setString(1, NameCurso);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Materia(rs.getInt("idmateria"), rs.getString("nombremateria"), rs.getInt("idcurso"), rs.getString("nombrecurso")));
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
                Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }

    @Override
    public void add(Materia entity) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("INSERT INTO materia (idcurso, nombremateria) values (?, ?)");
            // el http el campo que no le paso le pasa por defecto cero
            // entonces 37 significa que se le asigna un curso que no esta definido aun
            // y con un update se le asigna el curso correspondiente a la materia
            pstmt.setInt(1, (entity.getIdCurso() == 0)?37:entity.getIdCurso());
            pstmt.setString(2, entity.getNombre());
           
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
                Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            pstmt = c.prepareStatement("DELETE FROM materia WHERE idmateria = ?");

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
               Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Materia entity) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("UPDATE materia SET idcurso = ?, nombremateria = ? WHERE idmateria = ?");

            pstmt.setInt(1, entity.getIdCurso());
            pstmt.setString(2, entity.getNombre());
            pstmt.setInt(3, entity.getIdMateria());
           
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
                Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean contains(Integer id) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM materia where idmateria = ?");
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean ContainsIdCurso(int idCurso) {
        try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM curso where idcurso = ?");
            pstmt.setInt(1, idCurso);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public Entity get(Integer id) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Materia> getAll() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Materia> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT m.idmateria, m.idcurso, m.nombremateria,c.nombrecurso FROM materia m, curso c WHERE m.idcurso = c.idcurso");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Materia(rs.getInt("idmateria"), rs.getString("nombremateria"), rs.getInt("idcurso"), rs.getString("nombrecurso")));
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
                Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }

    @Override
    public boolean ContainsIdMateria(int idMateria) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM materia where idmateria = ?");
            pstmt.setInt(1, idMateria);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Collection<Materia> findByProfesor(int idProfesor) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Materia> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT m.idmateria, m.idcurso, m.nombremateria,c.nombrecurso FROM materia m join curso c on m.idcurso = c.idcurso where c.idProfesor = ? ORDER BY m.idcurso ASC");
            pstmt.setInt(1, idProfesor);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Materia(rs.getInt("idmateria"), rs.getString("nombremateria"), rs.getInt("idcurso"), rs.getString("nombrecurso")));
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
                Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }

    @Override
    public Collection<Materia> findByCurso(int idCurso) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       Collection<Materia> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT m.idmateria, m.idcurso, m.nombremateria,c.nombrecurso FROM materia m join curso c on m.idcurso = c.idcurso WHERE m.idcurso = ? ORDER BY m.idmateria");
            pstmt.setInt(1, idCurso);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Materia(rs.getInt("idmateria"), rs.getString("nombremateria"), rs.getInt("idcurso"), rs.getString("nombrecurso")));
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
                Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    
    
    }

    @Override
    public Collection<Materia> findByMateriasCursoDisponible(String NameCurso) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Materia> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;      
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select * from (SELECT m.idmateria, m.idcurso, m.nombremateria,c.nombrecurso, (select count(cue.idcuestionario) from cuestionario cue, curso cur where cue.idmateria = m.idmateria and UPPER(cur.nombreCurso) like UPPER(c.nombreCurso))as cant FROM materia m, curso c WHERE m.idcurso = c.idcurso and UPPER(c.nombrecurso) like UPPER(?) ORDER BY m.idmateria) as vista where cant >0 ");
            pstmt.setString(1, NameCurso);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retValue.add(new Materia(rs.getInt("idmateria"), rs.getString("nombremateria"), rs.getInt("idcurso"), rs.getString("nombrecurso")));
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
                Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }

    @Override
    public Collection<Materia> findByMateriasCursoTestDisponible(String NameCurso) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Materia> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;      
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select * from (SELECT DISTINCT m.idmateria, m.idcurso, m.nombremateria,c.nombrecurso, (select count(cue.idcuestionario) from cuestionario cue, curso cur where cue.idmateria = m.idmateria and UPPER(cur.nombreCurso) like UPPER(c.nombreCurso))as cant FROM materia m, curso c, cuestionario cu WHERE m.idcurso = c.idcurso and cu.idmateria = m.idmateria and now() >= cu.fechainicio and now() <= cu.fechacierre and UPPER(c.nombrecurso) like UPPER(?) ORDER BY m.idmateria) as vista where cant >0 ");
            pstmt.setString(1, NameCurso);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retValue.add(new Materia(rs.getInt("idmateria"), rs.getString("nombremateria"), rs.getInt("idcurso"), rs.getString("nombrecurso")));
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
                Logger.getLogger(JdbcMateriaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }
    
}
