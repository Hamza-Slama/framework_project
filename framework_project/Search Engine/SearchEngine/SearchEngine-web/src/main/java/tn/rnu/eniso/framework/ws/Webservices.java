/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import tn.rnu.eniso.framework.Services.ProductService;

/**
 *
 * @author Bacem
 */
@Stateless
@Path("indexing")
public class Webservices {
   
  
    
    
 @GET
 @Produces(MediaType.TEXT_PLAIN)
  public String sayPlainTextHello() {
       
              return "Hello Jersey";
  }
    
}
