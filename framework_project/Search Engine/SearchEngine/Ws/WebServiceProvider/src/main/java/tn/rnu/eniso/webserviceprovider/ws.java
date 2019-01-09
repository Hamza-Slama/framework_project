/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.webserviceprovider;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.lucene.queryparser.classic.ParseException;
import tn.rnu.eniso.framework.Services.ProductService;
import tn.rnu.eniso.framework.Services.SearchQueryService;

/**
 *
 * @author Bacem
 */

@Path("product")
public class ws {
    
     @EJB
     ProductService p ;
      @EJB
      SearchQueryService s ;
     
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("getAll")
    public String getClichedMessage(@Context HttpHeaders header, @Context HttpServletResponse response) {
        // Return some cliched textual content
        
          response.setHeader("Access-Control-Allow-Origin", "*");
          
        return new Gson().toJson(p.findAll() );
    }
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("getAllQueries")
    public Response getAllQueries(@QueryParam("word") String w) throws IOException, ParseException {
        
        
          return Response
		   .status(200)
                  .header("Access-Control-Allow-Origin", "*")
                   .entity(new Gson().toJson(s.allQueries(w) )).build();
     
    }
     @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("makeSearch")
    public Response makeSearch(@QueryParam("word") String w) throws IOException, ParseException {
        
          
        try {
            if(!s.allQueries(w).contains(w.toLowerCase()))
            s.indexQuery(w.toLowerCase());
        } catch (IOException ex) {
            Logger.getLogger(ws.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return Response
		   .status(200)
                   .header("Access-Control-Allow-Origin", "*")
		   .entity(new Gson().toJson(s.searchForProduct(w) )).build();
     
    }
    
    
}
