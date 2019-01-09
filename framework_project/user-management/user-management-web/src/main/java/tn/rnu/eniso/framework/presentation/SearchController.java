/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.presentation;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import tn.rnu.eniso.framework.model.Search;
import tn.rnu.eniso.framework.service.SubscriptionService;

/**
 *
 * @author ismail
 */
@ManagedBean
@SessionScoped
public class SearchController implements Serializable {

    public Model model = new Model();
    @EJB
    private SubscriptionService service;

    @ManagedProperty("#{loginController}")
    private LoginController loginController;

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    @PostConstruct
    public void initializer() {
        getModel().setSearches(service.findSearchesByUser(loginController.getModel().getCurrentUser()));
    }

    /*public void goToProfile() {
        getModel().setHeaderTitle("Profile");
        getModel().setIsPreferences(false);
        getModel().setIsSearches(false);
        getModel().setIsProfile(true);
    }

    public String goToSearchesHistory() {
        getModel().setHeaderTitle("Search History");
        getModel().setIsPreferences(false);
        getModel().setIsSearches(true);
        getModel().setIsProfile(false);
        return "dashboard";
    }

    public void goToPreferences() {
        getModel().setHeaderTitle("Preferences");
        getModel().setIsPreferences(true);
        getModel().setIsSearches(false);
        getModel().setIsProfile(false);
    }*/

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public class Model {

        private List<Search> searches;

        public List<Search> getSearches() {
            return searches;
        }

        public void setSearches(List<Search> searches) {
            this.searches = searches;
        }

    }

}
