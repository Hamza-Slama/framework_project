/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.presentation;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tn.rnu.eniso.framework.model.ApplicationUsr;
import tn.rnu.eniso.framework.model.GenderEnum;
import tn.rnu.eniso.framework.service.SubscriptionService;

/**
 *
 * @author ismail
 */
@ManagedBean
@SessionScoped
public class SubscribeController implements Serializable{

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
        if (service.userExist(getModel().getEmail())) {
            return "subscribe";
        } else {
            ApplicationUsr user=new ApplicationUsr();
            user.setBirthDate(getModel().getBirthDate());
            user.setEmailAddress(getModel().getEmail());
            user.setFirstName(getModel().getFirstName());
            user.setLastName(getModel().getLastName());
            user.setLogin(getModel().getEmail());
            user.setPassword(getModel().getPassword());
            user.setSubscriptionDate(new Date());
            service.saveUser(user);
            return "login";
        }
    }

    public static class Model {

        private String firstName;
        private String lastName;
        private String password;
        private String email;
        private Date birthDate;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }
}
