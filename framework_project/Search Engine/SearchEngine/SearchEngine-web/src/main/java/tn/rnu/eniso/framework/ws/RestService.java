/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.ws;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

/**
 *
 * @author Bacem
 */
@Path("/IndexData")
@ApplicationPath("/resources")
public class RestService  extends Application{
    
    
    @Path("/help")
    @GET
    public String getHelp()
    {
        return "Indexing the Data base Web Service" ;
    }
}
