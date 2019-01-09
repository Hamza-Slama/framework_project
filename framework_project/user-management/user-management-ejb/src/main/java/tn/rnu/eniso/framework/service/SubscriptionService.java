/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.rnu.eniso.framework.model.ApplicationUsr;
import tn.rnu.eniso.framework.model.Preference;
import tn.rnu.eniso.framework.model.Search;

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

    /*@PostConstruct
    public void defaultConfiguration() {
        UserType userType = new UserType();
        userType.setCode("Admin");
        userType.setName("Admin");
        UserType userType1=new UserType();
        userType1.setCode("Client");
        userType1.setName("Client");
        em.persist(userType);
    }*/
    public boolean userExist(String login) {
        List<ApplicationUsr> user = em.createQuery("Select e from ApplicationUsr e where e.login = :p")
                .setParameter("p", login).getResultList();
        if (user.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Preference> findPreferencesByUser(ApplicationUsr user) {
        List<Preference> preferences = em.createQuery("Select e from Preference e where e.user = :p")
                .setParameter("p", user).getResultList();
        return preferences;

    }

    public List<Search> findSearchesByUser(ApplicationUsr user) {
        List<Search> searches = em.createQuery("Select e from Search e where e.user = :p")
                .setParameter("p", user).getResultList();
        return searches;
    }

    public void saveUser(ApplicationUsr user) {
        em.persist(user);
    }

    public ApplicationUsr findUser(String login, String password) {
        List<ApplicationUsr> user = em.createQuery("Select e from ApplicationUsr e where e.login = :p and e.password = :q ")
                .setParameter("p", login).setParameter("q", password).getResultList();
        if (user.size() > 0) {
            return user.get(0);
        } else {
            return null;
        }

    }
}
