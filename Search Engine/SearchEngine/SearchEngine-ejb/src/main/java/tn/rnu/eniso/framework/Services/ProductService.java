/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.Services;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.SynchronizationType;
import jdk.nashorn.internal.runtime.Context;
import tn.rnu.eniso.framework.models.Product;


/**
 *
 * @author martin
 */
@Stateless
public class ProductService {

    @PersistenceContext
    EntityManager dal   ;

   
    
    
  public void saveProduct(Product a) {
  
      System.out.println(a.toString());
      Product old = dal.find(Product.class, a.getId());
       if (old != null) {
           
           dal.merge(a);
        } else {
             if (a.getId()==-1)
                 a.setId(null);
            dal.persist(a);
        }
    }

    public void removeProduct(Product a) {
        Product old = dal.find(Product.class, a.getId());
        if(old!=null){
            dal.remove(old);
        }
    }
    
    public List<Product> findProuductsByName(String theName){
        return dal.createQuery("Select x from Product x where x.name = :p1")
                .setParameter("p1", theName)
                .getResultList();
    }
    public List<Product> findAll(){
        return dal.createQuery("Select x from Product x")
                .getResultList();
    }
  
 
}
