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
    public static void main(String[] args) {
      
        for (Product a : generateDataSet())
        {
            System.out.println(a.toString());
        }
    }

}
