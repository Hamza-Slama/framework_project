/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.controlleur;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tn.rnu.eniso.framework.model.MyteckProduct;
import tn.rnu.eniso.framework.service.MyTeckService;

/**
 *
 * @author hamzewi
 */
@ManagedBean
@SessionScoped
public class MyTeckController {
    
    @EJB
    MyTeckService service;
    private Model model = new Model();
   
    public void init() {
        System.out.println("hello hamza and ismail and bacem w bilel m3ana w ken t7ib hamza mara o5ra");
       service.saveAllMyTeckFullDescription();
       
    }
    
    public void saveData(){
      
        System.out.println("Ok From my teck save Data ");
        //System.out.println("a536");
        //service.SaveHolder(new MyteckProduct());
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @PostConstruct
    public void getMyteckProduct() {
        getModel().setMyteckProducts(service.findAll());
    }

    public static class Model {

        private List<MyteckProduct> dataHolders;

        public List<MyteckProduct> getMyteckProducts() {
            return dataHolders;
        }

        public void setMyteckProducts(List<MyteckProduct> dataHolders) {
            this.dataHolders = dataHolders;
        }
        
    }
    
}
