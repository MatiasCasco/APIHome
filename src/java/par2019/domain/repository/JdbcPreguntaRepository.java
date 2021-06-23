/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.repository;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import par2019.domain.model.entity.Entity;
import par2019.domain.model.entity.EstadisticaPregunta;
import par2019.domain.model.entity.Pregunta;
import par2019.util.DBUtils;
//import static sun.security.krb5.Confounder.bytes;

/**
 *
 * @author Matias
 */
public class JdbcPreguntaRepository implements PreguntaRepository<Pregunta, Integer>{

    @Override
    public boolean ContainsId(int idPregunta) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {  
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
            Logger.getLogger(JdbcPreguntaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Pregunta getPregunta(int idPregunta) throws Exception{
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Pregunta retValue = null;
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM pregunta WHERE idpregunta = ?");

            pstmt.setInt(1, idPregunta);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                rs.getBinaryStream("foto");
                retValue = new Pregunta(rs.getInt("idpregunta"), rs.getInt("idcuestionario"), rs.getInt("puntoasignado"), rs.getInt("puntoobtenido"), rs.getString("pregunta"), Base64.getEncoder().encodeToString(rs.getBytes("foto")));
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
            Logger.getLogger(JdbcPreguntaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Collection<Pregunta> findByIdCuestionario(int idCuestionario) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Pregunta> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM pregunta WHERE idcuestionario = ?");
            pstmt.setInt(1, idCuestionario);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Pregunta(rs.getInt("idpregunta"), rs.getInt("idcuestionario"), rs.getInt("puntoasignado"), rs.getInt("puntoobtenido"), rs.getString("pregunta")));
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
    public void add(Pregunta entity) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;
        // desde aqui 
        FileInputStream fis = null;
        // hasta aqui
        try {
//            File file = new File(entity.getRuta());
//            try {
//                fis = new FileInputStream(file);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(JdbcPreguntaRepository.class.getName()).log(Level.SEVERE, null, ex);
//            }
            c = DBUtils.getConnection();
            // le saque lo que es de foto en el sql insert
            pstmt = c.prepareStatement("INSERT INTO pregunta (idcuestionario, puntoasignado, puntoobtenido, pregunta, foto) values (?, ?, ?, ?, ?)");
            pstmt.setInt(1, entity.getIdCuestionario());
            pstmt.setInt(2, entity.getPuntoAsignado());
            pstmt.setInt(3, entity.getPuntoObtenido());
            pstmt.setString(4, entity.getPregunta());
            pstmt.setBytes(5, entity.getArchivoimg());
            //pstmt.setBinaryStream(5, entity.getArchivoimg());
//            pstmt.setBytes(5, entity.getArchivoimg2());
//            pstmt.setBinaryStream(5, fis, (int) file.length());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcPreguntaRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            pstmt = c.prepareStatement("DELETE FROM pregunta WHERE idpregunta = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcPreguntaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Pregunta entity) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("UPDATE pregunta SET idcuestionario = ?, puntoasignado = ?, puntoobtenido = ?, pregunta = ? WHERE idpregunta = ?");
            pstmt.setInt(1, entity.getIdCuestionario());
            pstmt.setInt(2, entity.getPuntoAsignado());
            pstmt.setInt(3, entity.getPuntoObtenido());
            pstmt.setString(4, entity.getPregunta());
//            pstmt.setBytes(5, entity.getArchivoimg());
            pstmt.setInt(5, entity.getIdPregunta());
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
                Logger.getLogger(JdbcPreguntaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void UpdatePreguntaImagen(Pregunta entity) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("UPDATE pregunta SET idcuestionario = ?, puntoasignado = ?, puntoobtenido = ?, pregunta = ?, foto = ? WHERE idpregunta = ?");
            pstmt.setInt(1, entity.getIdCuestionario());
            pstmt.setInt(2, entity.getPuntoAsignado());
            pstmt.setInt(3, entity.getPuntoObtenido());
            pstmt.setString(4, entity.getPregunta());
            pstmt.setBytes(5, entity.getArchivoimg());
            pstmt.setInt(6, entity.getIdPregunta());
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
                Logger.getLogger(JdbcPreguntaRepository.class.getName()).log(Level.SEVERE, null, ex);
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
    public Collection<Pregunta> getAll() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Pregunta> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select * from pregunta");
            rs = pstmt.executeQuery();
            while (rs.next()) {
//               String encoded = Base64.getEncoder().encodeToString(rs.getBytes("foto"));
               retValue.add(new Pregunta(rs.getInt("idpregunta"), rs.getInt("idcuestionario"), rs.getInt("puntoasignado"), rs.getInt("puntoobtenido"), rs.getString("pregunta")));
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
        return retValue;
            } catch (SQLException ex) {
                Logger.getLogger(JdbcPreguntaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
    }

    @Override
    public Collection<EstadisticaPregunta> getEstadisticas(int idCuestionario) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<EstadisticaPregunta> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;       
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select p.idpregunta, p.pregunta, "
            + "round(avg(rta.puntorealizado),2) as promedio,"
            + " ((select count(*) from rta_alumnos where puntorealizado = p.puntoasignado "
            + "and idpregunta = p.idpregunta)*100/cant) as correcto,"
            + " (100-(select count(*) from rta_alumnos where puntorealizado = p.puntoasignado "
            + "and idpregunta = p.idpregunta)*100/cant) as incorrecto "
            + "from rta_alumnos rta, pregunta p, cuestionario c, "
            + "(SELECT idcuestionario,count(*) as cant from puntuaciones"
            + " where idcuestionario = ?) as t WHERE c.idcuestionario = p.idcuestionario "
            + "and c.idcuestionario = t.idcuestionario and rta.idpregunta = p.idpregunta"
            + " and c.idcuestionario = ? GROUP BY p.idpregunta ORDER by p.idpregunta ");
            pstmt.setInt(1, idCuestionario);
            pstmt.setInt(2, idCuestionario);
            rs = pstmt.executeQuery();

            while (rs.next()) {              
                retValue.add(new EstadisticaPregunta(rs.getInt("idpregunta"),rs.getString("pregunta"),rs.getInt("promedio"),rs.getInt("correcto"),rs.getInt("incorrecto")));
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
