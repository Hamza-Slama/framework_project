/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.IndexingCore;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
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
            doc.add( new LongPoint("id",instance.getId()));
               doc.add( new LongPoint("price",instance.getPrice()));
               doc.add(new StringField("name", instance.getName(), Field.Store.NO));
                doc.add(new StringField("category", instance.getCategory(), Field.Store.YES));
               doc.add( new TextField("description", instance.getDescription(), Field.Store.NO)) ;
               doc.add( new  StoredField("number", instance.getPrice())) ;
        }
        Document doctemp = doc ;
        doc  = new Document() ;
        return doctemp ;
    }
    
}
