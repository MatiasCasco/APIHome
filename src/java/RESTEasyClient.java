
import java.util.ArrayList;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import par2019.domain.model.entity.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauricio
 */
public class RESTEasyClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User st = new User(8, "Joaquinxzm", "Machucaa", "j.machasuca@pol.una.py", "j.machasuca", "1224345", 0);
        
        try {
            ResteasyClient client = new ResteasyClientBuilder().build();

            ResteasyWebTarget target = client.target("http://localhost:8084/par2019backend/rest/userapi/users");

            try ( //Response response = target.request().post(Entity.entity(st, "application/json"));
                    Response response = target.request().get()) {
                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
                
                System.out.println("Server response : \n");
                System.out.println(response.readEntity(String.class));
            }

        } catch (Exception e) {

            e.printStackTrace();

        }/*
       
            ResteasyClient client = new ResteasyClientBuilder().build();

            ResteasyWebTarget target = client.target("http://localhost:8084/ar2019backendp/rest/userapi/users");
            Response response = target.request().get();
            ArrayList<User> users=(ArrayList<User>) response.getEntity();
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
*/
       
       
       
       
       
       

    }

}
