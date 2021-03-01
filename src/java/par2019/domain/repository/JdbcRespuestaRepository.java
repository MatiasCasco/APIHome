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
import par2019.domain.model.entity.Respuesta;
import par2019.util.DBUtils;

/**
 *
 * @author Matias
 */
public class JdbcRespuestaRepository implements RespuestaRepository<Respuesta, Integer>{

    @Override
    public Collection<Respuesta> findByIdCuestionario(int idCuestionario) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Respuesta> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select c.idcuestionario as Cuestionario, p.idpregunta as PreguntaId, p.pregunta as Pregunta,r.idrta as RespuestaId ,r.rtas as Respuesta, r.evaluacion as Evaluacion"
                    + " from cuestionario c, pregunta p, respuesta r\n" +
"where c.idcuestionario = p.idcuestionario and p.idpregunta = r.idpregunta and c.idcuestionario = ?");
            pstmt.setInt(1, idCuestionario);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Respuesta(rs.getInt("Cuestionario"), rs.getInt("RespuestaId"), rs.getInt("PreguntaId"), rs.getString("Pregunta"), rs.getString("Respuesta"), rs.getBoolean("Evaluacion")));
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
                Logger.getLogger(JdbcRespuestaRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            pstmt = c.prepareStatement("SELECT * FROM respuesta where idrta = ?");
            pstmt.setInt(1, Id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;                
            }             
        } catch (SQLException ex) {
            Logger.getLogger(JdbcRespuestaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean containsIdPregunta(int IdPregunta) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM pregunta where idpregunta = ?");
            pstmt.setInt(1, IdPregunta);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;                
            }             
        } catch (SQLException ex) {
            Logger.getLogger(JdbcRespuestaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public Respuesta  getRespuesta(int idRespuesta) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Respuesta retValue = null;
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select c.idcuestionario as Cuestionario, p.idpregunta as PreguntaId, p.pregunta as Pregunta, r.idrta as RespuestaId ,r.rtas as Respuesta, r.evaluacion as Evaluacion"
                    + " from cuestionario c, pregunta p, respuesta r\n" +
"where c.idcuestionario = p.idcuestionario and p.idpregunta = r.idpregunta and r.idrta = ?");

            pstmt.setInt(1, idRespuesta);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new Respuesta(rs.getInt("Cuestionario"), rs.getInt("RespuestaId"), rs.getInt("PreguntaId"), rs.getString("Pregunta"), rs.getString("Respuesta"), rs.getBoolean("Evaluacion"));
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
                Logger.getLogger(JdbcPreguntaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;

    }

    @Override
    public Collection<Respuesta> findByIdPregunta(int idPregunta) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Respuesta> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT c.idcuestionario as Cuestionario, p.idpregunta as PreguntaId, p.pregunta as Pregunta, r.idrta as RespuestaId, r.rtas as Respuesta, r.evaluacion as Evaluacion"
                + " FROM cuestionario c, pregunta p, respuesta r "
                + "where c.idcuestionario = p.idcuestionario and p.idpregunta = r.idpregunta and r.idpregunta = ?");
            pstmt.setInt(1, idPregunta);            
            rs = pstmt.executeQuery();
            while (rs.next()) { 
                retValue.add(new Respuesta(rs.getInt("Cuestionario"), rs.getInt("RespuestaId"), rs.getInt("PreguntaId"), rs.getString("Pregunta"), rs.getString("Respuesta"), rs.getBoolean("Evaluacion")));
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
                Logger.getLogger(JdbcRespuestaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }

    @Override
    public void add(Respuesta entity) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("INSERT INTO respuesta (idpregunta, rtas, evaluacion) values (?, ?, ?)");
            pstmt.setInt(1, entity.getIdPregunta());
            pstmt.setString(2, entity.getRespuesta());
            pstmt.setBoolean(3, entity.getEvaluacion());            
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
                Logger.getLogger(JdbcRespuestaRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            pstmt = c.prepareStatement("DELETE FROM respuesta WHERE idrta = ?");
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
               Logger.getLogger(JdbcRespuestaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Respuesta entity) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("UPDATE respuesta SET idpregunta = ?, rtas = ?, evaluacion = ? WHERE idrta = ?");
            pstmt.setInt(1, entity.getIdPregunta());
            pstmt.setString(2, entity.getRespuesta());
            pstmt.setBoolean(3, entity.getEvaluacion());
            pstmt.setInt(4, entity.getIdRta());        
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
                Logger.getLogger(JdbcRespuestaRepository.class.getName()).log(Level.SEVERE, null, ex);
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
    public Collection<Respuesta> getAll() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Respuesta> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT c.idcuestionario as Cuestionario, p.idpregunta as PreguntaId, p.pregunta as Pregunta, r.idrta as RespuestaId, r.rtas as Respuesta, r.evaluacion as Evaluacion "
                    + "FROM cuestionario c, pregunta p, respuesta r "
                    + "where c.idcuestionario = p.idcuestionario and p.idpregunta = r.idpregunta");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retValue.add(new Respuesta(rs.getInt("Cuestionario"), rs.getInt("RespuestaId"), rs.getInt("PreguntaId"), rs.getString("Pregunta"),rs.getString("Respuesta"), rs.getBoolean("Evaluacion")));
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
               Logger.getLogger(JdbcRespuestaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }

    @Override
    public boolean containsIdCuestionario(int IdCuestionario) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Entity retValue = null;   
            Connection c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM cuestionario where idcuestionario = ?");
            pstmt.setInt(1, IdCuestionario);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;                
            }             
        } catch (SQLException ex) {
            Logger.getLogger(JdbcRespuestaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
