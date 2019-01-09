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
import static tn.rnu.eniso.framework.consts.Const.MYTECK_URL_PC_GAMER;
import static tn.rnu.eniso.framework.consts.Const.MYTECK_URL_PC_PORTABLE;
import static tn.rnu.eniso.framework.consts.Const.NBR_PAGE_PC_GAMER;
import static tn.rnu.eniso.framework.consts.Const.NBR_PAGE_PC_PORTABLE;
import tn.rnu.eniso.framework.model.MyTeckFullDescription;
import tn.rnu.eniso.framework.model.MyTeckFullModel;

/**
 *
 * @author hamzewi
 */
@Stateless
public class MyTeckService {
    
     @PersistenceContext
    EntityManager dal;

      public void SaveHolder(MyteckProduct a) {
        dal.persist(a);
      
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
       
    ArrayList<MyteckProduct> maps = getMyTeckProduct(MYTECK_URL_PC_PORTABLE);    
    ArrayList<MyteckProduct> mapsPcGamer = getMyTeckProduct(MYTECK_URL_PC_GAMER); 
            maps.addAll(mapsPcGamer);
        for (Iterator<MyteckProduct> it = maps.iterator(); it.hasNext(); ) {
            MyteckProduct data = it.next();
            SaveHolder(data);
        }
        
    }
    
    
    public void removeProduct(MyteckProduct a) {
        MyteckProduct old = dal.find(MyteckProduct.class, a.getId());
        if(old!=null){
            dal.remove(old);
        }
    }
    
    public List<MyteckProduct> findProuductsByName(String theName){
        return dal.createQuery("Select x from Product x where x.productName = :p1")
                .setParameter("p1", theName)
                .getResultList();
    }
      public List<MyteckProduct> findProuductsByIdList(List<Integer> id){
       ArrayList<MyteckProduct> res = new ArrayList<>() ;
       for (Integer el: id) {
           res.add(findProuductById(el));
       }
       return res ;
    }
      public MyteckProduct findProuductById(int id){


        return  (MyteckProduct) dal.createQuery("Select x from Product x where x.id =  :p ")
                           .setParameter("p", id)
                .getSingleResult();
    }
      
    
    
    public   ArrayList<MyteckProduct> getMyTeckProduct (String BASE_URL){
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
    

     public void SaveProductWithDescription (MyTeckFullModel a) {
        dal.persist(a);
      
    }
     
      public List<MyTeckFullModel> findAllMyTeckFullDescription(){
        
        
        return dal.createQuery("Select x from MyTeckFullModel x")
                .getResultList();
    }

    public void saveAllMyTeckFullDescription(){
        System.out.println("SaveAll MyTeckFullModel ");
       
    ArrayList<MyTeckFullModel> maps = getMyTeckProductWithDescription(MYTECK_URL_PC_PORTABLE,NBR_PAGE_PC_PORTABLE);
    ArrayList<MyTeckFullModel> mapsPcGamer = getMyTeckProductWithDescription(MYTECK_URL_PC_GAMER,3);
        maps.addAll(mapsPcGamer);
        for (Iterator<MyTeckFullModel> it = maps.iterator(); it.hasNext(); ) {
            MyTeckFullModel data = it.next();
            SaveProductWithDescription(data);
        }
        
    }
     
public static  MyTeckFullDescription getDescriptionMyTeckByProduct (String BASE_URL){
        long start = System.currentTimeMillis();
        int count2 = 0 ;
        
        
        String processeur="" ; 
        String freqProcesseur =""; 
        String mememoire="" ; 
        String ecran="" ; 
        String disqueDur="" ; 
        String carteGraphique="" ; 
        
        
        ArrayList<MyTeckFullDescription> myteckDescriptionsProducts = new ArrayList<>();
 
        try {
                
            final Document documents = Jsoup.connect(BASE_URL)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .ignoreHttpErrors(true)
                    .get();
            Elements links = documents.select("table.table-data-sheet");

            int i = 0 ;
            for (Element row : links){
                
                Elements innerDivs = row.select("tr");
                for (Element rows : innerDivs){
                    Elements tdBalise =   rows.select("td");
                    int cc = 1 ; 
                    for (Element tdBal : tdBalise){
                        if (tdBal.text().equals("PROCESSEUR")){
                            processeur = tdBal.nextElementSibling().text(); 
                            System.out.println("PROCESSEUR = "+tdBal.nextElementSibling().text());
                       }
                        if (tdBal.text().equals("Frequence Processeur")){
                            freqProcesseur =tdBal.nextElementSibling().text();
                            System.out.println("Frequence Processeur= "+tdBal.nextElementSibling().text());
                       }
                        if (tdBal.text().equals("MEMOIRE")){
                            mememoire = tdBal.nextElementSibling().text();
                            System.out.println("MEMOIRE = " + tdBal.nextElementSibling().text());
                       }
                        if (tdBal.text().equals("Ecran")){
                            ecran = tdBal.nextElementSibling().text();
                            System.out.println("Ecran = "+tdBal.nextElementSibling().text());
                       }
                        if (tdBal.text().equals("DISQUE DUR")){
                            disqueDur = tdBal.nextElementSibling().text();
                            System.out.println("DISQUE DUR =" +tdBal.nextElementSibling().text());
                       }
                        if (tdBal.text().equals("CARTE GRAPHIQUE")){
                            carteGraphique = tdBal.nextElementSibling().text();
                            System.out.println("CARTE GRAPHIQUE =" + tdBal.nextElementSibling().text());
                       }
                       
                           // System.out.println(tdBal.text());
                            myteckDescriptionsProducts.add(new MyTeckFullDescription(processeur,freqProcesseur,mememoire,ecran,disqueDur,carteGraphique));
                        
                       
                    }
                }
             
                //myteckDescriptionsProducts .add(new MyteckProduct(productName ,price,desciption,linkToDetails,imgPath));
                 i++;
               
            }

           MyTeckFullDescription model = new MyTeckFullDescription(processeur,freqProcesseur,mememoire,ecran,disqueDur,carteGraphique); 
            return model ; 
        } catch (NullPointerException e) { e.printStackTrace(); } catch (HttpStatusException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
       return  null ;

    }

public static  ArrayList<MyTeckFullModel> getMyTeckProductWithDescription (String BASE_URL ,int nbrPages){
        long start = System.currentTimeMillis();
        int count2 = 0 ;
         String productName ="";
         String price ="";
         String desciption="" ;
         String linkToDetails="" ;
         String imgPath ="";
        ArrayList<MyTeckFullModel> myteckProducts = new ArrayList<>();
        MyTeckFullDescription myteckDetailsByProducts = new MyTeckFullDescription();
        int countPage = 1 ;
        try {
            while(countPage<nbrPages){

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
                        System.out.println("----------------Details--------------------\n\n");
                       myteckDetailsByProducts =  getDescriptionMyTeckByProduct(linkToDetails);
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
                         String unescapedString = org.jsoup.parser.Parser.unescapeEntities(link1.text(), true);
                        System.out.println(unescapedString);
                        desciption = unescapedString ;
                       /* System.out.println(link1.html());
                        desciption = link1.html() ;*/

                    }
                    System.out.println("----------------Prices -----------------------\n");
                    Elements pngs = rows.select("table td span.price");
                    for (Element png : pngs) {
                        System.out.println(png.html());
                        price = png.html();
                    }

                }
                //adding
                myteckProducts .add(new MyTeckFullModel(productName ,price,myteckDetailsByProducts, desciption,linkToDetails,imgPath));
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
