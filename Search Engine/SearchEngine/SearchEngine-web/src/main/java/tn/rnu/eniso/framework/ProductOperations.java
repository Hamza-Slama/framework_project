/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.enterprise.context.Initialized;
import javax.faces.bean.ManagedBean;
import tn.rnu.eniso.framework.Services.ProductService;
import tn.rnu.eniso.framework.models.Product;


/**
 *
 * @author Bacem
 */
@ManagedBean
public class ProductOperations {
    @EJB ProductService metier;
    private Product model= new Product(-1L,"","","",0L);
    
    
    
    public Product getModel() {
        return model;
    }
    
    public String save(){
           System.out.println("Editing " + model.toString());
        metier.saveProduct(model);
        
        model= new Product(-1l,"","","",0l);
        return "list" ;
    }
    public String deleteRow(Product a)
    {
        
        metier.removeProduct(a);
        return "list";
    }
    public String goToProductPage(Product a){
        model = a ; 
        return  "create";
    }
     public String goToProductPage(){
          System.out.println("Creating " );
        model = new Product(-1l,"","","",0l);
        return "create";
    }
}
