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
import par2019.domain.model.entity.resumenSemestre;
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
            pstmt = c.prepareStatement("select c.idcurso, c.idprofesor, p.nombre as nombreprofesor, c.nombrecurso, c.claveprofesor,"
                    + " c.clavealumno from curso c, persona p\n" +
            "where c.idprofesor = p.id and UPPER(p.nombre) like UPPER(?) and p.rol = 2");
            pstmt.setString(1, Criteria);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Curso(rs.getInt("idcurso"), rs.getString("nombrecurso"),rs.getInt("idprofesor"), rs.getString("nombreprofesor"),rs.getString("claveprofesor"), rs.getString("clavealumno")));
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
            pstmt = c.prepareStatement("SELECT c.idcurso, c.idprofesor, p.nombre as nombreprofesor, c.nombrecurso, c.claveprofesor,"
                    + " c.clavealumno  from curso c, persona p\n" +
            "where c.idprofesor = p.id and UPPER(c.nombrecurso) like UPPER(?)");
            pstmt.setString(1, Name);

            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Curso(rs.getInt("idcurso"), rs.getString("nombrecurso"),rs.getInt("idprofesor"), rs.getString("nombreprofesor"),rs.getString("claveprofesor"), rs.getString("clavealumno")));
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
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Curso curso = (Curso) this.findByNameCurso(Curso);
            if(Curso.equals(curso.getNombre())){
                return true;
            } ;
        } catch (Exception ex) {
            //Exception Handler
        }
        return false;
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
            pstmt = c.prepareStatement("INSERT INTO curso (nombrecurso, idprofesor, claveprofesor, clavealumno) values (?, ?, ?, ?)");

            pstmt.setString(1, entity.getNombre());
            pstmt.setInt(2, (entity.getIdProfesor() == 0)?4:entity.getIdProfesor() ); //creo que es sin profesor confirmado o algo por el estilo
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
            pstmt = c.prepareStatement("SELECT c.idcurso, c.idprofesor, p.nombre as nombreprofesor,p.apellido as apellido, c.nombrecurso, c.claveprofesor,\n" +
" c.clavealumno  from curso c, persona p\n" +
"where c.idprofesor = p.id");
            

            rs = pstmt.executeQuery();

            while (rs.next()) {
                String nombre="\""+rs.getString("nombreprofesor")+" "+rs.getString("apellido")+"\"";
                retValue.add(new Curso(rs.getInt("idcurso"), rs.getString("nombrecurso"), rs.getInt("idprofesor"), nombre,rs.getString("claveprofesor"), rs.getString("clavealumno")));
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
            pstmt = c.prepareStatement("SELECT c.idcurso, c.idprofesor, p.nombre as nombreprofesor, c.nombrecurso, c.claveprofesor,"
                    + " c.clavealumno  from curso c, persona p\n" +
            "where c.idprofesor = p.id and idcurso = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                retValue = new Curso(rs.getInt("idcurso"), rs.getString("nombrecurso"), rs.getInt("idprofesor"), rs.getString("nombreprofesor"),rs.getString("claveprofesor"), rs.getString("clavealumno"));
            } else {
                retValue = new Curso(0," ",0,""," "," ");
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
   public Collection<Curso> findByIdProfesor(int idProfesor) throws Exception {
      Collection<Curso> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
           /* pstmt = c.prepareStatement("select c.idcurso, c.idprofesor, p.nombre as nombreprofesor, c.nombrecurso, c.claveprofesor,"
                    + " c.clavealumno from curso c, persona p\n" +
            "where c.idprofesor = p.id and UPPER(p.nombre) like UPPER(?) and p.rol = 1");*/
            pstmt = c.prepareStatement("SELECT c.idcurso,c.idProfesor,p.nombre as nombreProfesor,c.nombreCurso,c.claveProfesor,c.claveAlumno FROM persona p join curso c on c.idProfesor=p.id WHERE  p.rol=2 and p.id=? and idProfesor ORDER BY c.idcurso ASC" );
            pstmt.setInt(1,idProfesor );
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                retValue.add(new Curso(rs.getInt("idcurso"), rs.getString("nombrecurso"),rs.getInt("idprofesor"), rs.getString("nombreprofesor"),rs.getString("claveprofesor"), rs.getString("clavealumno")));
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
    public int cantAlumnos(String curso) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cantAlumnos = 0;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select count(a.id) as cant from alumno a, curso cu "
                    + "where a.idCurso = cu.idcurso "
                    + "and UPPER(cu.nombreCurso) like UPPER(?)");
            pstmt.setString(1, curso);            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                cantAlumnos = rs.getInt("cant");
            }    
            return cantAlumnos;
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
        return cantAlumnos;
    }
    
     @Override
    public int cantAlumnosXidCurso(int curso) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cantAlumnos = 0;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select count(a.id) as cant from alumno a, curso cu "
                    + "where a.idCurso = cu.idcurso "
                    + "and cu.idcurso = ?");
            pstmt.setInt(1, curso);            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                cantAlumnos = rs.getInt("cant");
            }    
            return cantAlumnos;
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
        return cantAlumnos;
    }

    @Override
    public Collection<resumenSemestre> resumenSemestre(int idCurso, int mesApertura, int mesCierre) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<resumenSemestre> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select ma.nombremateria, p.idcuestionario as test, "
            + "round(avg(p.puntaje),2) as promedio, cue.fechainicio, MONTH(cue.fechacierre) as mes "
            + "from puntuaciones p, cuestionario cue, curso cu, materia ma "
            + "where cu.idcurso = ma.idcurso and ma.idmateria = cue.idmateria "
            + "and cue.idcuestionario = p.idcuestionario and cu.idcurso = ? "
            + "and MONTH(cue.fechacierre) >= ? AND MONTH(cue.fechacierre) <= ? and YEAR(cue.fechacierre) = YEAR(NOW())"
            + "GROUP by ma.nombremateria, MONTH(cue.fechacierre) ORDER BY ma.nombremateria");
            pstmt.setInt(1, idCurso );
            pstmt.setInt(2, mesApertura);
            pstmt.setInt(3, mesCierre);
            rs = pstmt.executeQuery();
            while (rs.next()) {  
                retValue.add(new resumenSemestre(rs.getString("nombremateria"), rs.getInt("test"), rs.getInt("promedio"), rs.getInt("mes")));
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
    
}
