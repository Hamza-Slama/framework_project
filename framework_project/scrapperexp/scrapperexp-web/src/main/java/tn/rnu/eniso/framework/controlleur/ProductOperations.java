/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.controlleur;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import tn.rnu.eniso.framework.model.MyteckProduct;
import tn.rnu.eniso.framework.service.MyTeckService;

/**
 *
 * @author hamzewi
 */
@ManagedBean
public class ProductOperations {
    @EJB MyTeckService metier;
    private MyteckProduct model= new MyteckProduct(-1, "", "", "", "", "");
    
    
    
    public MyteckProduct getModel() {
        return model;
    }
    
    public String save(){
           System.out.println("Editing " + model.toString());
        metier.SaveHolder(model);
        
        model=new MyteckProduct(-1, "", "", "", "", "");
        return "list" ;
    }
    public String deleteRow(MyteckProduct a)
    {
        
        metier.removeProduct(a);
        return "list";
    }
    public String goToProductPage(MyteckProduct a){
        model = a ; 
        return  "create";
    }
     public String goToProductPage(){
          System.out.println("Creating " );
        model = new MyteckProduct(-1, "", "", "", "", "");
        return "create";
    }
}
