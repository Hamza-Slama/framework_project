/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.IndexingCore;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import tn.rnu.eniso.framework.models.Product;

/**
 *
 * @author Bacem
 */
public class ProductDocumentBuilder implements DocumentBuilderInterface<Product>{
   private Product instance ;
   private Document doc ;
   
    @Override
    public void setEntitySource(Product u) {
       this.instance = u ;
    }

    @Override
    public Document buildDocument() {
    
        doc = new Document();
        if (instance == null)
            throw new RuntimeException("Attempt to Build Document on Null Object") ;
        else {
            doc.add( new StoredField("id",instance.getId()));
               doc.add( new  StringField("price",instance.getPrice(),Field.Store.NO));
               doc.add(new TextField("name", instance.getProductName().toLowerCase(), Field.Store.NO));
               
               doc.add( new TextField("description", instance.getDesciption().toLowerCase(), Field.Store.NO)) ;
        
             
        }
      ;
        return doc ;
    }
    
}
