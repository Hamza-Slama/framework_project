/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.enterprise.context.Initialized;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.lucene.queryparser.classic.ParseException;
import tn.rnu.eniso.framework.Services.ProductService;
import tn.rnu.eniso.framework.Services.SearchQueryService;
import tn.rnu.eniso.framework.models.Product;


/**
 *
 * @author Bacem
 */
@ManagedBean
@SessionScoped
public class SearchEngineController {

    @EJB
    SearchQueryService metier;
   
    @EJB
    ProductService metier2;

    private String searchQuery;
    private List<Product> searchedProducts = new ArrayList<>();
    private List<String> allQueries = new ArrayList<>();

    /* public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i +"sdq");
        }
         System.out.println(query);
        return results;
    }*/
 /*public List<Theme> completeTheme(String query) {
        List<Theme> allThemes = service.getThemes();
        List<Theme> filteredThemes = new ArrayList<Theme>();
         
        for (int i = 0; i < allThemes.size(); i++) {
            Theme skin = allThemes.get(i);
            if(skin.getName().toLowerCase().contains(query)) {
                filteredThemes.add(skin);
            }
        }
         
        return filteredThemes;
    }*/
    public List<String> completeTermContains(String query) {
      
        try {
            allQueries = metier.allQueries(query.toLowerCase());
        } catch (IOException ex) {
            Logger.getLogger(SearchEngineController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SearchEngineController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        List<String> filteredTermes = new ArrayList<String>();

        for (int i = 0; i < allQueries.size(); i++) {
            String term = allQueries.get(i);
            if (term.toLowerCase().contains(query.toLowerCase())) {
                filteredTermes.add(term);
            }
        }

        return filteredTermes;
    }

    public String startSearch() throws IOException, ParseException  {

        
        try {
            if(!allQueries.contains(searchQuery.toLowerCase()))
            metier.indexQuery(searchQuery.toLowerCase());
        } catch (IOException ex) {
            Logger.getLogger(SearchEngineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       this.searchedProducts = metier.searchForProduct(searchQuery.toLowerCase()) ;
        return  "listing_search_result" ;
    }
    public void indexInnerData()
    {
        try {
            metier2.indexInnerData();
        } catch (IOException ex) {
            Logger.getLogger(SearchEngineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<Product> getSearchedProducts() {
        return searchedProducts;
    }

    public void setSearchedProducts(List<Product> searchedProducts) {
        this.searchedProducts = searchedProducts;
    }

}
