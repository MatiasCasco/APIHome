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
import par2019.domain.model.entity.reporte;
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
        final String link  = "http://192.168.0.3:8084/homeApi/rest/imageApi/image/"; 
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
    public Collection<Test> WebTest(int Cuestionario) throws Exception {
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
        final String link  = "";
        String image = "";
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select p.idpregunta, p.pregunta, p.puntoasignado from pregunta p, cuestionario c \n" +
            "where c.idcuestionario = p.idcuestionario and c.idcuestionario = ? \n" +
            "ORDER BY p.idpregunta");
            
            pstmt.setInt(1, Cuestionario);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
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
                    if(rs.getInt("evaluacion")== 1) listaE.add(x);
                    x++;
                }
                retValue.add(new Test(listIndex.get(i), listaE, listIndex.get(i) , listaP.get(i), listPunto.get(i),image,listaR, listIdR));
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

    @Override
    public Collection<Test> TestRecover(int Cuestionario, int Alumno) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Test> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        List<String> listaP = new ArrayList();
        List<Integer> listIndex = new ArrayList();
        List<Integer> listPunto= new ArrayList();
        List<Integer> listPuntoO = new ArrayList();
        List<Integer> listIdR= new ArrayList();
        List<Integer> listaE = new ArrayList();
        List<String> listaR = new ArrayList();
        List<Integer> selected = new ArrayList();// Contiene las respuestas de cada pregunta
        final String link  = "http://192.168.0.3:8084/homeApi/rest/imageApi/image/";
        String image = "";
//        List list1 = new ArrayList();
//        List<String> list2 = new ArrayList();
//        list1.add(1);
//        list1.add(2);
//        list2.add("Mat");
//        list2.add("Cas");
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select p.idpregunta, p.pregunta, p.puntoasignado, rt.puntorealizado \n" +
            "from pregunta p, cuestionario c, rta_alumnos rt \n" +
            "where c.idcuestionario = p.idcuestionario and p.idpregunta = rt.idpregunta \n" +
            "and c.idcuestionario = ? and rt.idalumno = ? ORDER BY p.idpregunta");
            
            pstmt.setInt(1, Cuestionario);
            pstmt.setInt(2, Alumno);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                //retValue.add(new Test());
                listaP.add(rs.getString("pregunta"));
                listIndex.add(rs.getInt("idpregunta"));
                listPunto.add(rs.getInt("puntoasignado"));
                listPuntoO.add(rs.getInt("puntorealizado"));
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
                pstmt= c.prepareStatement("select rta.idrta as seleccionada from rta_marcadas rta, respuesta r\n" +
                "where rta.idrta = r.idrta AND r.idpregunta = ? and rta.idalumno = ?");
                pstmt.setInt(1, (int) listIndex.get(i));
                pstmt.setInt(1, Alumno);
                rs = pstmt.executeQuery();
                while(rs.next()){
                    selected.add(rs.getInt("seleccionada"));
                }  
                
                retValue.add(new Test((i+1), listaE, listIndex.get(i) , listaP.get(i), listPunto.get(i), listPuntoO.get(i), image, listaR, listIdR, selected));
                listaR = new ArrayList();
                listaE = new ArrayList();    
                listIdR = new ArrayList();
                selected = new ArrayList();               
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
    public Collection<Test> TestRecuperacion(int Cuestionario, int Alumno) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<Test> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        List<Integer> listIndex = new ArrayList();
        List<Integer> listPunto= new ArrayList();
        List<Integer> listIdR= new ArrayList();
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select p.idpregunta, rta.puntorealizado from pregunta p, rta_alumnos rta, cuestionario c "
                    + "where c.idcuestionario = p.idcuestionario and p.idpregunta = rta.idpregunta "
                    + "and c.idcuestionario = ? and rta.idalumno = ? ORDER BY p.idpregunta ");    
            pstmt.setInt(1, Cuestionario);
            pstmt.setInt(2, Alumno);
            rs = pstmt.executeQuery();

            while (rs.next()) {  
                listIndex.add(rs.getInt("idpregunta"));
                listPunto.add(rs.getInt("puntorealizado"));
            }
            for(int i = 0; i < listIndex.size(); i++) {
                pstmt = c.prepareStatement("select rta.idrta from rta_marcadas rta, respuesta r, pregunta p "
                        + "where rta.idrta = r.idrta and p.idpregunta = r.idpregunta "
                        + "and p.idpregunta = ? and rta.idalumno = ?");
                pstmt.setInt(1, listIndex.get(i));
                pstmt.setInt(2, Alumno);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    listIdR.add(rs.getInt("idrta"));                 
                }
                retValue.add(new Test(listIndex.get(i), listPunto.get(i),listIdR)); 
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
    public void updatePuntosXPregunta(int idAlumno, int idPregunta, int puntoObtenido) throws Exception {
             ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("UPDATE rta_alumnos SET puntorealizado = ? WHERE idalumno = ? AND idpregunta= ? ");

            pstmt.setInt(1, puntoObtenido); 
            pstmt.setInt(2, idAlumno);
            pstmt.setInt(3, idPregunta);
             
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
        }}

    @Override
    public Collection<par2019.domain.model.entity.reporte> reporte(int Cuestionario) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Collection<reporte> retValue = new ArrayList();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int indice = 0;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT  a.id, CONCAT(pe.apellido, ' ', pe.nombre) as ApellidoNombre, "
            + "(select sum(p.puntoasignado) from pregunta p, cuestionario cue "
            + "where p.idcuestionario = cue.idcuestionario and cue.idcuestionario = c.idcuestionario) as TP,"
            + " COALESCE((SELECT sum(rt.puntorealizado) from rta_alumnos rt, pregunta pr "
            + "where rt.idpregunta = pr.idpregunta and rt.idalumno = a.id "
            + "and pr.idcuestionario = c.idcuestionario),0) as PL "
            + "from alumno a, persona pe, materia m, cuestionario c "
            + "where a.idcurso = m.idcurso and m.idmateria = c.idmateria and a.id = pe.id "
            + "and c.idcuestionario = ? ORDER BY ApellidoNombre");
            pstmt.setInt(1, Cuestionario);           
            rs = pstmt.executeQuery();
            while (rs.next()) {  
                retValue.add(new reporte(indice++,'"'+rs.getString("ApellidoNombre")+'"', rs.getInt("TP"), rs.getInt("PL")));
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
