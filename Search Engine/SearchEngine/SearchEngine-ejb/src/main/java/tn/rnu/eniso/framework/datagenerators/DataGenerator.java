/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.datagenerators;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import tn.rnu.eniso.framework.IndexingCore.DocumentIndexer;
import tn.rnu.eniso.framework.IndexingCore.ProductDocumentBuilder;
import tn.rnu.eniso.framework.Services.ProductService;
import tn.rnu.eniso.framework.models.Product;

/**
 *
 * @author Bacem
 */
public class DataGenerator {
    
    
    public static Product[] generateDataSet()
    {
        ArrayList<Product> res = new ArrayList<>() ;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Bacem\\Desktop\\fwk\\projet semestriel\\framework_project\\Search Engine\\SearchEngine\\SearchEngine-ejb\\src\\main\\java\\tn\\rnu\\eniso\\framework\\datagenerators\\DataSet.json"));
           
           
             return   new Gson().fromJson(br, Product[].class)  ;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(DataGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         return null ;
    }
    public static  void saveAllData()
    {
        ProductService ser = new ProductService();
         for (Product a : generateDataSet())
        {
          
            
            ser.saveProduct(a);
        }
    }
      public static  List<Product> readAllData()
    {
        ProductService ser = new ProductService();
        
        return   ser.findAll() ;
    }
    public static void main(String[] args) throws IOException, ParseException {
        ProductDocumentBuilder builder = new ProductDocumentBuilder() ;
          DocumentIndexer indexer = null ;
       
            indexer = new DocumentIndexer("product");
      
        
        for (Product a : generateDataSet())
        { 
            System.out.println(a.toString());
            a.setId(a.getPrice());
            builder.setEntitySource(a);
            indexer.getIndexWriter().addDocument(builder.buildDocument()) ;
        }
        System.out.println( indexer.getIndexWriter().commit());
        WhitespaceAnalyzer anal = new WhitespaceAnalyzer();
        QueryParser parser = new QueryParser("name", anal);
        TopDocs result =      indexer.getIndexSearcher().search(parser.parse("HP"), 100) ;
        System.out.println(result.totalHits);
           for (ScoreDoc a : result.scoreDocs)
        { 
            System.out.println(indexer.getIndexSearcher().doc(a.doc).toString());
        }
       
           indexer.getIndexWriter().close();
           indexer.getIndexSearcher().getIndexReader().close();
    }
    

}
