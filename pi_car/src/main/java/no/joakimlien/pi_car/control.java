/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.joakimlien.pi_car;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author joakimlien
 */
@Stateless
@Path("control")
@Produces(MediaType.APPLICATION_JSON)
public class control {
    
    @POST
    @Produces( { "application/x-javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("manual")
    public void manual_control(@QueryParam("direction")String direction){
        Connect connect = new Connect(direction);
    }
    
    
    
}
