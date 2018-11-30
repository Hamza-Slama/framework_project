/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.service;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.rnu.eniso.framework.model.AppUser;
import tn.rnu.eniso.framework.model.UserType;

/**
 *
 * @author ismail
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SubscriptionService {
    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void defaultConfiguration() {
        UserType userType = new UserType();
        userType.setCode("Admin");
        userType.setName("Admin");
        em.persist(userType);
    }
    
    public boolean userExist(String login) {
        List<AppUser> user = em.createQuery("Select e from AppUser e where e.login = :p")
                .setParameter("p", login).getResultList();
        if (user.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public void saveUser(AppUser user) {
        em.persist(user);
    }
    
    public AppUser findUser(String login, String password) {
        List<AppUser> user = em.createQuery("Select e from AppUser e where e.login = :p and e.password = :q ")
                .setParameter("p", login).setParameter("q", password).getResultList();
        if (user.size() > 0) {
            return user.get(0);
        } else {
            return null;
        }
        
    }
}
