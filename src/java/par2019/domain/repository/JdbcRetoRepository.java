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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.infinispan.affinity.impl.RndKeyGenerator.rnd;
import par2019.domain.model.entity.Entity;
import par2019.domain.model.entity.Reto;
import par2019.util.DBUtils;

/**
 *
 * @author User
 */
public class JdbcRetoRepository implements RetoRepository<Reto, Integer>{

    @Override
    public Collection<Reto> reto(int list, int sizeOptions, String materia, String curso) throws Exception {
//    public Collection<Reto> reto(int list, int sizeOptions, String materia, String curso) throws Exception {
//    public List reto(int list, int sizeOptions, String materia) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<String> listaP = new ArrayList(); // Almacena todas las preguntas recuperadas de BD
        List listIndex = new ArrayList(); // Guarda el valor del identificador de las preguntas
        List preguntasR = new ArrayList();// Almacena las posiciones aleatorias de las preguntas
        List listaE = new ArrayList();
        List posiciones = new ArrayList();
        List<String> listaR = new ArrayList(); // Contiene las respuestas de cada pregunta
        List<String> listaOpciones = new ArrayList();
        final String link  = "http://192.168.0.3:8084/homeApi/rest/imageApi/image/";
        String image = "";
        int sizeList = 0;
        int contTrue = 0;
        int contFalse = 0;
        int indexR = 0;
        int contF = 0;
        int contV = 0;
        int div = 0, inicio = 0;
        Collection<Reto> retValue = new ArrayList(); // la estructura a retornar a la app
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("select count(*) as cant from pregunta p, cuestionario c, materia m, curso cu "
                    + "where c.idcuestionario = p.idcuestionario and c.idmateria = m.idmateria and m.idcurso = cu.idcurso "
                    + "and UPPER(m.nombremateria) like UPPER(?) "
                    + "and UPPER(cu.nombreCurso) like UPPER(?);");
            pstmt.setString(1, materia);
            pstmt.setString(2, curso);
            rs = pstmt.executeQuery();
            while (rs.next()) {  
              div = rs.getInt("cant");
            }
            div = (int) div/list;
            if(div >= 2){
                int numeroAleatorio = (int) Math.round(Math.random()*div);
                inicio = numeroAleatorio*list-list;
            } else {
                inicio = 0;
            }
            pstmt = c.prepareStatement("select p.idpregunta, p.pregunta from pregunta p, cuestionario c, materia m, curso cu "
                    + "where c.idcuestionario = p.idcuestionario and c.idmateria = m.idmateria and m.idcurso = cu.idcurso "
                    + "and UPPER(m.nombremateria) like UPPER(?) "
                    + "and UPPER(cu.nombreCurso) like UPPER(?) "
                    + "ORDER BY p.idpregunta limit ?,?;");
            pstmt.setString(1, materia);
            pstmt.setString(2, curso);
            pstmt.setInt(3, inicio);
            pstmt.setInt(4, list);
            rs = pstmt.executeQuery();

            while (rs.next()) {  
//                retValue.add(new Respuesta(rs.getInt("Cuestionario"), rs.getInt("RespuestaId"), rs.getInt("PreguntaId"), rs.getString("Pregunta"), rs.getString("Respuesta"), rs.getBoolean("Evaluacion")));
                listaP.add(rs.getString("pregunta"));
                listIndex.add(rs.getInt("idpregunta"));  
            }
            sizeList = listaP.size();
//            if(listaP.size() < list){
//                //sizeList = listaP.size(); 
//                sizeList = listaP.size();
//            }else{
//                //sizeList = list;
//                sizeList = listaP.size();
//            }
            preguntasR.clear();
            preguntasR = numerosAleatorio(sizeList);
            for(int i = 0; i < list; i++) {
                
                pstmt = c.prepareStatement("select r.rtas, r.evaluacion from respuesta r, pregunta p, cuestionario c, materia m where r.idpregunta = p.idpregunta and p.idcuestionario = c.idcuestionario and c.idmateria = m.idmateria"
                        + " and UPPER(m.nombremateria) like UPPER(?) and p.idpregunta = ? ");
                pstmt.setString(1, materia);
                pstmt.setInt(2, (int) listIndex.get((int) preguntasR.get(i)));
                image = link + listIndex.get((int) preguntasR.get(i));
                rs = pstmt.executeQuery();
                contFalse = 0;
                contTrue = 0;
                while (rs.next()) {  
                    listaR.add(rs.getString("rtas"));
                    listaE.add(rs.getString("evaluacion"));
                    if("0".equalsIgnoreCase(rs.getString("evaluacion"))){
                        contFalse++;
                    }else{
                         contTrue++;
                    }
                }
                if(contFalse >= (sizeOptions-1) || contTrue > 1){
                    posiciones.clear();
                    posiciones = numerosAleatorio(listaR.size());
                    for (int j=0; j<listaE.size(); j++) {
                        if(listaR.size()== contTrue){
                            if (listaE.get((int)posiciones.get(j)).equals("1")) {
                                if(contV < (sizeOptions-1)){
                                    listaOpciones.add(listaR.get((int)posiciones.get(j)));
                                    contV++;
                                }
                                if(contV == (sizeOptions-1)|| ((j==listaE.size()-1)&&contF==0)){
                                    if(materia.equalsIgnoreCase("guarani")){
                                        listaOpciones.add("Enterovea oî porâ");
                                    } else {
                                        listaOpciones.add("Todas las opciones son verdaderas");
                                    }   
                                    indexR = (listaOpciones.size()-1);
                                    contV++;
                                }
                            }                               
                        }
                        if (listaE.get((int)posiciones.get(j)).equals("0")) {                               
                            if(contF < (sizeOptions-1)){
                                listaOpciones.add(listaR.get((int)posiciones.get(j)));
                                contF++;
                            }
                        }else {                          
                            if(contV < 1){
                                listaOpciones.add(listaR.get((int)posiciones.get(j)));
                                indexR = (listaOpciones.size()-1);
                                contV++;
                            }
                        }
                        if(contF == (sizeOptions-1)&&(j==listaE.size()-1)&&contV==0){
                            if(materia.equalsIgnoreCase("guarani")){
                                listaOpciones.add("Enterovea oî vai");
                            }else{
                                listaOpciones.add("Todas las opciones son falsas");
                            }
                            indexR = (listaOpciones.size()-1);
                        }
                        if(listaOpciones.size() == (sizeOptions)){
//                                retValue.add(new Reto((i+1), 1, listaP.get((int) preguntasR.get(i)), listaOpciones));
                            break;
                        }                    
                    }
                    
                    retValue.add(new Reto((i+1), indexR, listaP.get((int) preguntasR.get(i)), image,listaOpciones));
                    listaOpciones= new ArrayList();
                    //listaOpciones.clear();      
                    contF = 0;
                    contV = 0;

                }else {
                    posiciones.clear();
                    posiciones = numerosAleatorio(listaR.size());
                    for (int k=0; k<listaE.size(); k++) {
                        if (listaE.get((int)posiciones.get(k)).equals("0")) {                               
                             listaOpciones.add(listaR.get((int)posiciones.get(k)));
                        }else {                                                      
                            listaOpciones.add(listaR.get((int)posiciones.get(k)));
                            indexR = (listaOpciones.size()-1);                         
                        }
                        if(contFalse==2 && contTrue==0 &&k == (listaE.size()-1)){
                            if(materia.equalsIgnoreCase("guarani")){
                                    listaOpciones.add("Enterovea oî vai");
                                }else{
                                    listaOpciones.add("Todas las opciones son falsas");
                                }
                            indexR = (listaOpciones.size()-1); 
                        }
                        if(sizeOptions > 3 && listaOpciones.size() < sizeOptions && k == (listaE.size()-1) && (contFalse!=2 && contTrue!=0)){
                            int numeroAleatorio = (int) Math.ceil(Math.random()*2);
                            if(numeroAleatorio == 1){
                                if(materia.equalsIgnoreCase("guarani")){
                                        listaOpciones.add("Enterovea oî porâ");
                                    } else {
                                        listaOpciones.add("Todas las opciones son verdaderas");
                                    }
                            }else{
                                if(materia.equalsIgnoreCase("guarani")){
                                    listaOpciones.add("Enterovea oî vai");
                                }else{
                                    listaOpciones.add("Todas las opciones son falsas");
                                }
                            }
                        }
                    }
                    retValue.add(new Reto((i+1), indexR, listaP.get((int) preguntasR.get(i)), image,listaOpciones));
                    listaOpciones= new ArrayList();
                    //listaOpciones.clear();
                }                    
//                }
//                retValue.add(new Reto((i+1), 1, listaP.get((int) preguntasR.get(i)), listaR));
            listaR = new ArrayList();
            listaE = new ArrayList();                
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
               Logger.getLogger(JdbcRetoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retValue;
        
//        return preguntasR;
    }

    @Override
    public void add(Reto entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Reto entity) {
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
    public Collection<Reto> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List numerosAleatorio(int size){
        List list = new ArrayList();
        while(list.size() < size){
          int numeroAleatorio = (int) Math.ceil(Math.random()*((size-1)-(-1))+(-1));
          boolean existe = false;
            for (Object preguntasR1 : list) {
                if ((int) preguntasR1 == numeroAleatorio) {
                    existe = true;
                    break;
                }
            }
          if(existe == false){
            list.add(numeroAleatorio);
          }
        }
        return list;
    }
}
