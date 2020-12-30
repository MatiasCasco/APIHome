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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import par2019.domain.model.entity.Cuestionario;
import par2019.domain.model.entity.Entity;
import par2019.domain.model.entity.Materia;
import par2019.util.DBUtils;

/**
 *
 * @author Matias
 */
public class JdbcCuestionarioRepository implements CuestionarioRepository<Cuestionario, Integer> {

    @Override
    public Cuestionario getCuestionario(int idCuestionario) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Cuestionario retValue = null;
        DateFormat convertido1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat convertido2 = new SimpleDateFormat("HH-mm-ss");
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM cuestionario WHERE idcuestionario = ?");

            pstmt.setInt(1, idCuestionario);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new Cuestionario(rs.getInt("idcuestionario"), rs.getInt("idmateria"), convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntos"), convertido2.format(rs.getTime("tiempolimite")));
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
                Logger.getLogger(JdbcCuestionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }

    @Override
    public boolean ContainsIdCuestionario(int idCuestionario) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            Logger.getLogger(JdbcCuestionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Collection<Cuestionario> findByNameMateria(String NameMateria) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Cuestionario> retValue = new ArrayList();
        DateFormat convertido1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat convertido2 = new SimpleDateFormat("HH-mm-ss");
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select c.idcuestionario, c.idmateria, c.fechacierre, c.fechainicio, c.puntos, c.tiempolimite  "
                    + "from cuestionario c, materia m where c.idmateria = m.idmateria and UPPER(m.nombremateria) like UPPER(?)");
            pstmt.setString(1, NameMateria);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Cuestionario(rs.getInt("idcuestionario"), rs.getInt("idmateria"), convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntos"), convertido2.format(rs.getTime("tiempolimite"))));                 
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
                Logger.getLogger(JdbcCuestionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }

    @Override
    public void add(Cuestionario entity) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;
//        SimpleDateFormat formatoDeFecha = new SimpleDateFormat(“dd/MM/yyyy”);
//formatoDeFecha.format(fecha)
        try {
            c = DBUtils.getConnection();
            //pstmt = c.prepareStatement("INSERT INTO cuestionario (idmateria, fechacierre, fechainicio, puntos, tiempolimite) values (?, to_date(?,'DD/MM/YY'), to_date(?,'DD/MM/YY'), ?, to_timestamp(?, 'HH24:MI:ss'))");
            pstmt = c.prepareStatement("INSERT INTO cuestionario (idmateria, fechacierre, fechainicio, puntos, tiempolimite) values (?, ?, ?, ?, ?)");
            pstmt.setInt(1, (entity.getIdMateria()));
            pstmt.setString(2, entity.getFechaCierre());
            pstmt.setString(3, entity.getFechaInicio());
            pstmt.setInt(4, entity.getPuntos());
            pstmt.setString(5, entity.getTiempoLimite());
            
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
                Logger.getLogger(JdbcCuestionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            pstmt = c.prepareStatement("DELETE FROM cuestionario WHERE idcuestionario = ?");

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
                Logger.getLogger(JdbcCuestionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Cuestionario entity) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
//            pstmt = c.prepareStatement("UPDATE cuestionario SET idmateria = ?, fechacierre = to_date(?,'DD/MM/YY'), fechainicio = to_date(?,'DD/MM/YY'), puntos = ?, tiempolimite = to_timestamp(?, 'HH24:MI') WHERE idcuestionario = ?");
            pstmt = c.prepareStatement("UPDATE cuestionario SET idmateria = ?, fechacierre = ?, fechainicio = ?, puntos = ?, tiempolimite = ? WHERE idcuestionario = ?");

            pstmt.setInt(1, entity.getIdMateria());
            pstmt.setString(2, entity.getFechaCierre());
            pstmt.setString(3, entity.getFechaInicio());
            pstmt.setInt(4, entity.getPuntos());
            pstmt.setString(5, entity.getTiempoLimite());
            pstmt.setInt(6, entity.getIdCuestionario());
            
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
                Logger.getLogger(JdbcCuestionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean contains(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean ContainsMateria(int idMateria) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM materia where idmateria = ?");
            pstmt.setInt(1, idMateria);
       
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCuestionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean ContainsNameMateria(String NameMateria) {
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM materia where nombremateria = ?");
            pstmt.setString(1, NameMateria);
       
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCuestionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public Entity get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
    }

    @Override
    public Collection<Cuestionario> getAll() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Cuestionario> retValue = new ArrayList();
        DateFormat convertido1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat convertido2 = new SimpleDateFormat("HH-mm-ss");
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM cuestionario");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Cuestionario(rs.getInt("idcuestionario"), rs.getInt("idmateria"), convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntos"), convertido2.format(rs.getTime("tiempolimite"))));
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
                Logger.getLogger(JdbcCuestionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }
    
}
