/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.joakimlien.pi_car;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
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
    
    public Connect connectController;
    
    @POST
    @Path("manual")
    public void manual_control(@QueryParam("direction")String direction){
        System.out.print(direction);
        
       connectController = new Connect();
        
       System.out.println("Connection is established");
    }
    
    // toggle metode som bestemmer om den er av eller på, eller boolean
    
}
