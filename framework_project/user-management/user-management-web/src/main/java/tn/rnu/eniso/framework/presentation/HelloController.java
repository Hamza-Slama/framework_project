/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.presentation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import tn.rnu.eniso.framework.model.AppUser;

/**
 *
 * @author ismail
 */
@ManagedBean
@SessionScoped
public class HelloController {
    
    private Model model = new Model();
    
    @ManagedProperty("#{loginController}")
    LoginController controller;
    
    public Model getModel() {
        return model;
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
    
    @PostConstruct
    public void init() {
        getModel().setCurrentUser(controller.getModel().getCurrentUser());
    }
    
    public static class Model {
        
        private AppUser currentUser;
        
        public AppUser getCurrentUser() {
            return currentUser;
        }
        
        public void setCurrentUser(AppUser currentUser) {
            this.currentUser = currentUser;
        }
        
    }
}
