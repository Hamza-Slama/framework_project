/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.rnu.eniso.framework.model.MyteckProduct;
import tn.rnu.eniso.framework.model.MyteckProduct;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 * @author hamzewi
 */
@Stateless
public class MyTeckService {
    
     @PersistenceContext
    EntityManager dal;

      public void SaveHolder(MyteckProduct a) {
        //select
        dal.persist(a);
        /*MyteckProduct old = dal.find(MyteckProduct.class, a.getId());
        if (old != null) {
            //update
            dal.merge(a);
        } else {
            //insert
            dal.persist(a);
        }*/
    }
      
      public void remove(int MyteckProductId) {
        MyteckProduct old = dal.find(MyteckProduct.class, MyteckProductId);
        if(old!=null){
            dal.remove(old);
        }
    }
    

    public List<MyteckProduct> findAll(){
        
        
        return dal.createQuery("Select x from MyteckProduct x")
                .getResultList();
    }

    public void saveAll(){
        System.out.println("SaveAll Myteck ");
        
    ArrayList<MyteckProduct> listLink = getLinkHashMap("https://www.mytek.tn/13-pc-portable?selected_filters=page-");    
        for (Iterator<MyteckProduct> it = listLink.iterator(); it.hasNext(); ) {
            MyteckProduct data = it.next();
            SaveHolder(data);
        }
        
    }
    
    
    public   ArrayList<MyteckProduct> getLinkHashMap (String BASE_URL){
        long start = System.currentTimeMillis();
        int count2 = 0 ;
         String productName ="";
         String price ="";
         String desciption="" ;
         String linkToDetails="" ;
         String imgPath ="";
        ArrayList<MyteckProduct> myteckProducts = new ArrayList<>();
        int countPage = 1 ;
        int NBR_PAGE_PC_PORTABLE = 19;
        try {
            while(countPage<NBR_PAGE_PC_PORTABLE){

                System.out.println("Page number ° "+countPage +"------------\n \n ");
                String url = BASE_URL+Integer.toString(countPage) ;
                System.out.println("-------------------------------------\n");
                System.out.println("URL " + url);
                System.out.println("-------------------------------------\n");
            final Document documents = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .ignoreHttpErrors(true)
                    .get();
            Elements links = documents.select("div.product-container");

            int i = 0 ;
            for (Element row : links){
                System.out.println("---------------Product n° "+ i+" ---------------");

              //  left-block
                Elements innerDivs = row.select("div.left-block");
                for (Element rows : innerDivs){
                    Elements links1 =   rows.select("a[href]");
                    for (Element link1 : links1){
                        System.out.println(link1.attr("href") +
                                " title = "+link1.attr("title"));
                        linkToDetails = link1.attr("href") ;
                    }
                    System.out.println("-----Images-------\n");
                    Elements pngs = rows.select("img[src]");
                    for (Element png : pngs) {
                        System.out.println("title: " + png.attr("alt"));
                        System.out.println("src: " + png.attr("src"));
                        imgPath  = png.attr("src")  ;
                        productName = png.attr("alt")  ;
                    }

                }
//
                Elements innerDivsRightBlock = row.select("div.right-block");
//                //product-desc
                for (Element rows : innerDivsRightBlock){
                    Elements links1 =   rows.select("p.product-desc");
                    for (Element link1 : links1){
                        System.out.println("------- Description-----\n");
                        System.out.println(link1.html());
                        desciption = link1.html() ;

                    }
                    System.out.println("----------------Prices -----------------------\n");
                    Elements pngs = rows.select("table td span.price");
                    for (Element png : pngs) {
                        System.out.println(png.html());
                        price = png.html();
                    }

                }
                //adding
                myteckProducts .add(new MyteckProduct(productName ,price,desciption,linkToDetails,imgPath));
                 i++;
                System.out.println("\n \n ");
            }

            countPage++;
            }
            return myteckProducts ;
        } catch (NullPointerException e) { e.printStackTrace(); } catch (HttpStatusException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
       return  null ;

    }

    
     
}
