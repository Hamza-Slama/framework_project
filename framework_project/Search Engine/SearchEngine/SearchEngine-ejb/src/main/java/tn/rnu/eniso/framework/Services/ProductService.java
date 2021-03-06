/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.Services;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.SynchronizationType;
import jdk.nashorn.internal.runtime.Context;
import org.apache.lucene.document.Document;
import tn.rnu.eniso.framework.IndexingCore.DocumentIndexer;
import tn.rnu.eniso.framework.IndexingCore.ProductDocumentBuilder;
import tn.rnu.eniso.framework.models.Product;


/**
 *
 * @author martin
 */
@Stateless
public class ProductService  {

    @PersistenceContext
    EntityManager dal   ;

   
    
    
  public void saveProduct(Product a) {
  
      System.out.println(a.toString());
      Product old = dal.find(Product.class, a.getId());
       if (old != null) {
           
           dal.merge(a);
        } else {
             if (a.getId()==-1)
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
        return dal.createQuery("Select x from Product x where x.productName = :p1")
                .setParameter("p1", theName)
                .getResultList();
    }
      public List<Product> findProuductsByIdList(List<Integer> id){
       ArrayList<Product> res = new ArrayList<>() ;
       for (Integer el: id) {
           res.add(findProuductById(el));
       }
       return res ;
    }
      public Product findProuductById(int id){


        return  (Product) dal.createQuery("Select x from Product x where x.id =  :p ")
                           .setParameter("p", id)
                .getSingleResult();
    }
    public List<Product> findAll(){
        return dal.createQuery("Select x from Product x")
                .getResultList();
    }
  
    public void indexInnerData() throws IOException
    {
        
        DocumentIndexer indexer = new DocumentIndexer("product");
        indexer.getIndexWriter().deleteAll();
        ProductDocumentBuilder builder = new ProductDocumentBuilder();
        Document doc ;
        for (Product p: findAll())
        {
            builder.setEntitySource(p);
            doc = builder.buildDocument();
            indexer.getIndexWriter().addDocument(doc);
        }
        indexer.getIndexWriter().commit();
        indexer.close();
    }
 
}
