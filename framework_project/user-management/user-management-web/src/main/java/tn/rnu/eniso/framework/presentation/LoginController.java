/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.presentation;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tn.rnu.eniso.framework.model.AppUser;
import tn.rnu.eniso.framework.service.SubscriptionService;

/**
 *
 * @author ismail
 */
@ManagedBean
@SessionScoped
public class LoginController {

    private Model model = new Model();
    @EJB
    private SubscriptionService service;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String subscribe() {

        return "subscribe";
    }

    public String login() {
        AppUser user = service.findUser(getModel().getLogin(), getModel().getPassword());
        if (user == null) {
            return "login";
        } else {
            getModel().setCurrentUser(user);
            return "hello";
        }
    }

    public static class Model {

        private String login;
        private String password;
        private AppUser currentUser;

        public AppUser getCurrentUser() {
            return currentUser;
        }

        public void setCurrentUser(AppUser currentUser) {
            this.currentUser = currentUser;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }
}
