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
import tn.rnu.eniso.framework.model.DataHolder;
import tn.rnu.eniso.framework.service.DataHolderService;

/**
 *
 * @author hamzewi
 */
@ManagedBean
@SessionScoped
public class ScrapperController {

    @EJB
    DataHolderService service;
    private Model model = new Model();

    @Init
    public void init() {
 
    }
    
    public void saveData(){
      
        System.out.println("Ok");
        //System.out.println("a536");
       // service.SaveHolder(new DataHolder());
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @PostConstruct
    public void getDataHolder() {
        getModel().setDataHolders(service.findAll());
    }

    public static class Model {

        private List<DataHolder> dataHolders;

        public List<DataHolder> getDataHolders() {
            return dataHolders;
        }

        public void setDataHolders(List<DataHolder> dataHolders) {
            this.dataHolders = dataHolders;
        }

    }
}
