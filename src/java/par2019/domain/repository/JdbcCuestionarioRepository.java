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
import par2019.domain.model.entity.Grafica;
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
            pstmt = c.prepareStatement("select c.idcuestionario, c.descripcion, c.idmateria,m.nombremateria,c.fechacierre, c.fechainicio, c.puntos, c.tiempolimite, m.idcurso, cu.nombrecurso"
                    + " from cuestionario c, curso cu, materia m where cu.idcurso = m.idcurso and m.idmateria = c.idmateria and c.idcuestionario = ? ORDER BY c.idcuestionario");

            pstmt.setInt(1, idCuestionario);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new Cuestionario(rs.getInt("idcuestionario"),rs.getString("descripcion"), rs.getInt("idmateria"), rs.getString("nombremateria") ,convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntos"), convertido2.format(rs.getTime("tiempolimite")), rs.getInt("idcurso"), rs.getString("nombrecurso"));
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
    public void add(Cuestionario entity) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;
//        SimpleDateFormat formatoDeFecha = new SimpleDateFormat(“dd/MM/yyyy”);
//formatoDeFecha.format(fecha)
        try {
            c = DBUtils.getConnection();
            //pstmt = c.prepareStatement("INSERT INTO cuestionario (idmateria, fechacierre, fechainicio, puntos, tiempolimite) values (?, to_date(?,'DD/MM/YY'), to_date(?,'DD/MM/YY'), ?, to_timestamp(?, 'HH24:MI:ss'))");
           pstmt = c.prepareStatement("INSERT INTO cuestionario (descripcion, idmateria, fechacierre, fechainicio, puntos, tiempolimite) values (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, (entity.getDescripcion()));
            pstmt.setInt(2, (entity.getIdMateria()));
            pstmt.setString(3, entity.getFechaCierre());
            pstmt.setString(4, entity.getFechaInicio());
            pstmt.setInt(5, entity.getPuntos());
            pstmt.setString(6, entity.getTiempoLimite());
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
            pstmt = c.prepareStatement("UPDATE cuestionario SET descripcion = ?, idmateria = ?, fechacierre = ?, fechainicio = ?, puntos = ?, tiempolimite = ? WHERE idcuestionario = ?");
            pstmt.setString(1, entity.getDescripcion());
            pstmt.setInt(2, entity.getIdMateria());
            pstmt.setString(3, entity.getFechaCierre());
            pstmt.setString(4, entity.getFechaInicio());
            pstmt.setInt(5, entity.getPuntos());
            pstmt.setString(6, entity.getTiempoLimite());
            pstmt.setInt(7, entity.getIdCuestionario());
            
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

    @Override
    public boolean ContainsNameMateria(String nameMateria) {
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM materia where UPPER(nombremateria) like UPPER(?);");
            pstmt.setString(1, nameMateria);
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
            pstmt = c.prepareStatement("select c.idcuestionario, c.descripcion, c.idmateria,m.nombremateria,c.fechacierre, c.fechainicio, c.puntos, c.tiempolimite, m.idcurso, cu.nombrecurso"
                    + " from cuestionario c, curso cu, materia m where cu.idcurso = m.idcurso and m.idmateria = c.idmateria"
                    + " ORDER BY c.idcuestionario ");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Cuestionario(rs.getInt("idcuestionario"), rs.getString("descripcion"), rs.getInt("idmateria"), rs.getString("nombremateria"),convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntos"), convertido2.format(rs.getTime("tiempolimite")), rs.getInt("idcurso"), rs.getString("nombrecurso")));
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
    public boolean ContainsCurso(int idCurso) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM curso where idcurso = ?");
            pstmt.setInt(1, idCurso);
       
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCuestionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean ContainsNameCurso(String nameCurso) {
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM curso where UPPER(nombrecurso) like UPPER(?)");
            pstmt.setString(1, nameCurso);
       
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
    public Collection<Cuestionario> findByNameCurso(String nameCurso) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Cuestionario> retValue = new ArrayList();
        DateFormat convertido1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat convertido2 = new SimpleDateFormat("HH-mm-ss");
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select c.idcuestionario, c.descripcion,c.idmateria, m.nombremateria,c.fechacierre, c.fechainicio, c.puntos, c.tiempolimite, m.idcurso ,cu.nombrecurso\n" +
                                        "from cuestionario c, curso cu, materia m \n" +
                                        "where cu.idcurso = m.idcurso and m.idmateria = c.idmateria\n" +
                                        "and UPPER(cu.nombrecurso) like UPPER(?) ORDER BY c.idcuestionario");
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Cuestionario(rs.getInt("idcuestionario"),rs.getString("descripcion"), rs.getInt("idmateria"), rs.getString("nombremateria"),convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntos"), convertido2.format(rs.getTime("tiempolimite")), rs.getInt("idCurso"), rs.getString("nombreCurso")));                 
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
    public Collection<Cuestionario> findByNameCursoAndMateria(String nameCurso, String nameMateria) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Cuestionario> retValue = new ArrayList();
        DateFormat convertido1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat convertido2 = new SimpleDateFormat("HH-mm-ss");
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
//            pstmt = c.prepareStatement("select c.idcuestionario, c.idmateria, m.nombremateria,c.fechacierre, c.fechainicio, \n" +
//            "c.puntos, c.tiempolimite, m.idcurso, cu.nombrecurso\n" +
//            "from cuestionario c, curso cu, materia m\n" +
//            "where cu.idcurso = m.idcurso and m.idmateria = c.idmateria\n" +
//            "and now() >= c.fechainicio and now() <= c.fechacierre\n" +
//            "and UPPER(m.nombremateria) like UPPER(?)\n" +
//            "and UPPER(cu.nombrecurso) like UPPER(?)"
//            + " ORDER BY c.idcuestionario");
            pstmt = c.prepareStatement("select c.idcuestionario, c.descripcion, c.idmateria, m.nombremateria,c.fechacierre, c.fechainicio, c.puntos, c.tiempolimite, m.idcurso, cu.nombrecurso "
                    + "from cuestionario c, curso cu, materia m "
                    + "where cu.idcurso = m.idcurso and m.idmateria = c.idmateria "
                    + "and YEAR(c.fechacierre) = YEAR(NOW())"
                    + "and UPPER(m.nombremateria) like UPPER(?) "
                    + "and UPPER(cu.nombrecurso) like UPPER(?) ORDER BY c.idcuestionario");
            pstmt.setString(1,nameMateria);
            pstmt.setString(2,nameCurso);
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Cuestionario(rs.getInt("idcuestionario"), rs.getString("descripcion"), rs.getInt("idmateria"), rs.getString("nombremateria"),convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntos"), convertido2.format(rs.getTime("tiempolimite")), rs.getInt("idCurso"), rs.getString("nombrecurso")));                 
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
    public Collection<Cuestionario> findByNameMateria(String nameMateria) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Cuestionario> retValue = new ArrayList();
        DateFormat convertido1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat convertido2 = new SimpleDateFormat("HH-mm-ss");
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
             pstmt = c.prepareStatement("select c.idcuestionario, c.descripcion, c.idmateria, m.nombremateria,c.fechacierre, c.fechainicio, c.puntos, c.tiempolimite, m.idcurso ,cu.nombrecurso "
                    + "from cuestionario c, curso cu, materia m where cu.idcurso = m.idcurso and m.idmateria = c.idmateria"
                    + "and YEAR(c.fechacierre) = YEAR(NOW())" 
                    + " and UPPER(m.nombremateria) like UPPER(?);");
            pstmt.setString(1,nameMateria);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Cuestionario(rs.getInt("idcuestionario"), rs.getString("descripcion"), rs.getInt("idmateria"), rs.getString("nombremateria"),convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntos"), convertido2.format(rs.getTime("tiempolimite")), rs.getInt("idCurso"), rs.getString("nombreCurso")));
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
    public Collection<Cuestionario> findByAlumnoAndMateria(int idAlumno, int idMateria) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Cuestionario> retValue = new ArrayList();
        DateFormat convertido1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat convertido2 = new SimpleDateFormat("HH-mm-ss");
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select c.idcuestionario, c.descripcion, c.idmateria, m.nombremateria, "
                    + "c.fechacierre, c.fechainicio, pu.puntaje, c.tiempolimite, m.idcurso, cu.nombrecurso"
                    + " from cuestionario c, curso cu, materia m, puntuaciones pu"
                    + " where cu.idcurso = m.idcurso and m.idmateria = c.idmateria "
                    + "and c.idcuestionario = pu.idcuestionario "
                    + "and m.idmateria = ? and pu.id = ? ORDER BY c.idcuestionario ");
            pstmt.setInt(1,idMateria);
            pstmt.setInt(2,idAlumno);
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Cuestionario(rs.getInt("idcuestionario"), rs.getString("descripcion"), rs.getInt("idmateria"), rs.getString("nombremateria"),convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntaje"), convertido2.format(rs.getTime("tiempolimite")), rs.getInt("idCurso"), rs.getString("nombrecurso")));                 
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
    public boolean ContainsAlumno(int idAlumno) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {  
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
            Logger.getLogger(JdbcCuestionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

   
    @Override
    public Collection<Cuestionario> findByIdMateria(int idMateria) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Cuestionario> retValue = new ArrayList();
        DateFormat convertido1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat convertido2 = new SimpleDateFormat("HH-mm-ss");
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
             pstmt = c.prepareStatement("select c.idcuestionario, c.descripcion,c.idmateria, m.nombremateria,c.fechacierre, c.fechainicio, c.puntos, c.tiempolimite, m.idcurso ,cu.nombrecurso\n" +
            "from cuestionario c join curso cu join materia m on cu.idcurso = m.idcurso and m.idmateria = c.idmateria"
                    + " WHERE m.idmateria = ? and YEAR(c.fechacierre) = YEAR(NOW())");
            pstmt.setInt(1,idMateria);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
            retValue.add(new Cuestionario(rs.getInt("idcuestionario"), rs.getString("descripcion"), rs.getInt("idmateria"), rs.getString("nombremateria"),convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntos"), convertido2.format(rs.getTime("tiempolimite")), rs.getInt("idCurso"), rs.getString("nombreCurso")));
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
    public Collection<Cuestionario> findByIdProfesor(int idProfesor) throws Exception {
        Collection<Cuestionario> retValue = new ArrayList();
        DateFormat convertido1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat convertido2 = new SimpleDateFormat("HH-mm-ss");
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
           pstmt = c.prepareStatement("select c.idcuestionario, c.descripcion, c.idmateria, m.nombremateria,c.fechacierre, c.fechainicio, c.puntos, c.tiempolimite, m.idcurso ,cu.nombrecurso\n" +
            "from cuestionario c join curso cu join materia m on cu.idcurso = m.idcurso and m.idmateria = c.idmateria"
                    + " WHERE cu.idProfesor = ?");
            pstmt.setInt(1,idProfesor);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Cuestionario(rs.getInt("idcuestionario"),rs.getString("descripcion"), rs.getInt("idmateria"), rs.getString("nombremateria"),convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntos"), convertido2.format(rs.getTime("tiempolimite")), rs.getInt("idCurso"), rs.getString("nombreCurso")));                 
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
    public Collection<Cuestionario> findByNameCursoAndMateriaApp(String Curso, String Materia) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Cuestionario> retValue = new ArrayList();
        DateFormat convertido1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat convertido2 = new SimpleDateFormat("HH-mm-ss");
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select c.idcuestionario, c.descripcion, c.idmateria, m.nombremateria,\n" +
            "c.fechacierre, c.fechainicio, c.puntos, c.tiempolimite, m.idcurso ,cu.nombrecurso\n" +
            "from cuestionario c, curso cu, materia m\n" +
            "where cu.idcurso = m.idcurso and m.idmateria = c.idmateria\n" +
            "and now() >= c.fechainicio and now() <= c.fechacierre\n" +
            "and UPPER(cu.nombrecurso) like UPPER(?)\n" + 
            "and UPPER(m.nombremateria) like UPPER(?)\n" +
            "ORDER BY c.idcuestionario");
            pstmt.setString(1, Curso);
            pstmt.setString(2, Materia);
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Cuestionario(rs.getInt("idcuestionario"), rs.getString("descripcion"), rs.getInt("idmateria"), rs.getString("nombremateria"),convertido1.format(rs.getDate("fechacierre")), convertido1.format(rs.getDate("fechainicio")), rs.getInt("puntos"), convertido2.format(rs.getTime("tiempolimite")), rs.getInt("idCurso"), rs.getString("nombreCurso")));                 
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
    public Collection<Grafica> resumenEvaluacion(int idCuestionario) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Grafica> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select count(round(100*p.puntaje/(select sum(puntoasignado) from pregunta where idcuestionario = ?),2)) as mayor, (select count(pe.id) from persona pe, alumno a, curso cu, materia m, cuestionario cue where pe.id = a.id and a.idCurso = cu.idcurso and cu.idcurso = m.idcurso and m.idmateria = cue.idmateria and cue.idcuestionario = ?) as alumnos from cuestionario c, puntuaciones p where c.idcuestionario = p.idcuestionario and p.idcuestionario = ? and round(100*p.puntaje/(select sum(puntoasignado) from pregunta where idcuestionario = ?),2) >= 70 ");
            pstmt.setInt(1, idCuestionario);
            pstmt.setInt(2, idCuestionario);
            pstmt.setInt(3, idCuestionario);
            pstmt.setInt(4, idCuestionario);
            rs = pstmt.executeQuery();

            while (rs.next()) {              
                retValue.add(new Grafica("Porcentaje >= a 70%", rs.getInt("mayor"), "Catidad mayor e igual a 70%"));
                retValue.add(new Grafica("Porcentaje < a 70%", (rs.getInt("alumnos")-rs.getInt("mayor")), "Catidad menor a 70%"));
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
    public Collection<Grafica> cuestionarioAlumnos(int idCuestionario) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Grafica> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT count(p.id) as presentes,"
            + " (select count(a.id) from alumno a, materia m, cuestionario c "
            + "where a.idCurso = m.idcurso and m.idmateria = c.idmateria "
            + "and c.idcuestionario = p.idcuestionario) as total "
            + "from puntuaciones p, cuestionario c where p.idcuestionario = c.idcuestionario "
            + "and p.idcuestionario = ? ");
            pstmt.setInt(1, idCuestionario);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Grafica("Se presentaron", rs.getInt("presentes"), "Se presentaron"));
                retValue.add(new Grafica("No se presentaron", rs.getInt("total")- rs.getInt("presentes"), "No se presentaron"));
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
