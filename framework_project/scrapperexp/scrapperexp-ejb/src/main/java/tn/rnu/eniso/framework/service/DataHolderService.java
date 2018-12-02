/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tn.rnu.eniso.framework.model.DataHolder;

/**
 *
 * @author hamzewi
 */
@Stateless
public class DataHolderService  {
    
    @PersistenceContext
    EntityManager dal;

    public void SaveHolder(DataHolder a) {
        //select
        dal.persist(a);
        /*DataHolder old = dal.find(DataHolder.class, a.getId());
        if (old != null) {
            //update
            dal.merge(a);
        } else {
            //insert
            dal.persist(a);
        }*/
    }

    public void remove(int DataHolderId) {
        DataHolder old = dal.find(DataHolder.class, DataHolderId);
        if(old!=null){
            dal.remove(old);
        }
    }
    

    public List<DataHolder> findAll(){
        
        
        return dal.createQuery("Select x from DataHolder x")
                .getResultList();
    }

    public void saveAll(){
        
    Set<DataHolder> listLink = getListWebSite("https://www.tutorialspoint.com/");    
        for (Iterator<DataHolder> it = listLink.iterator(); it.hasNext(); ) {
            DataHolder data = it.next();
            SaveHolder(data);
            System.out.println(data.getWebsite() +
                    " nbr link "+data.getNbrLink() 
                    +" ,visited "+data.getVisited());
        }
        
    }
    
    public static Set<DataHolder> getListWebSite (String BASE_URL){
            long start = System.currentTimeMillis();
    int count2 = 0 ;
    Set<DataHolder> listLink = new HashSet<>();
        try {
            final Document documents = Jsoup.connect(BASE_URL)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .ignoreHttpErrors(true)
                    .get();
        System.out.println(documents.outerHtml());
            Elements links = documents.select("a[href]");
            int count = 0 ;
            int countBase = 0 ;
            int i = 0 ;
            for (Element link : links){

                if (link.attr("href").contains(BASE_URL)){

                    System.out.println("--------------------- Site n ° "+i+" ----------------");
                    System.out.println("\n link "+link.attr("href"));
                    System.out.println("Text : "+link.text());
                    final Document document1 = Jsoup.connect(link.attr("href"))
                            .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                            .referrer("http://www.google.com")
                            .ignoreHttpErrors(true)
                            .get();
                    Elements links1 = document1.select("a[href]");

                            for (Element link1 : links1){
                                count2++;
                                if (link1.attr("href").contains(BASE_URL)){
                                    listLink.add(new DataHolder(link1.attr("href"),0,false));
                                    count2++;

                                }

                            }

                    listLink.add(new DataHolder(link.attr("href"),count2,true));
                    System.out.println("le nombre des link de site n° "+i +" = "+count2);
                    count+=count2;
                    count2 = 0 ;
                    long finish = System.currentTimeMillis();
                    long timeElapsed = finish - start;
                    System.out.println(""+timeElapsed+"ms" );
                    i++;
                    System.out.println("\n Number of Link = "+count);

                    countBase++;
                }

            }

            listLink.add(new DataHolder(BASE_URL,countBase,true));

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (HttpStatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listLink;
        }
    
}
