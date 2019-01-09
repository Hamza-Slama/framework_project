/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.presentation;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import tn.rnu.eniso.framework.model.ApplicationUsr;

/**
 *
 * @author ismail
 */
@ManagedBean
@SessionScoped
public class HelloController implements Serializable{
    
    private Model model = new Model();
    
    @ManagedProperty("#{loginController}")
    private LoginController loginController;

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    
    
    public Model getModel() {
        return model;
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
    
    @PostConstruct
    public void init() {
        getModel().setCurrentUser(loginController.getModel().getCurrentUser());
    }
    
    public static class Model {
        
        private ApplicationUsr currentUser;
        
        public ApplicationUsr getCurrentUser() {
            return currentUser;
        }
        
        public void setCurrentUser(ApplicationUsr currentUser) {
            this.currentUser = currentUser;
        }
        
    }
}
