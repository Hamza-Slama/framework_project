/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.recommendationdata.model;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import tn.rnu.eniso.framework.recommendationdata.service.SearchedDataService;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;
import xyz.morphia.query.Query;

/**
 *
 * @author ismail
 */
@Configurable
@ComponentScan("tn.rnu.eniso.framework.recommendationdata.service")
public class MainTest {

    public static void main(String[] args) /*throws UnknownHostException*/ {
       /* ApplicationContext app = new AnnotationConfigApplicationContext(MainTest.class);
        SearchedDataService dataService = app.getBean(SearchedDataService.class);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Product pr = new Product();
            pr.setDesciption("Hello " + i);
            pr.setImgPath("img " + i);
            pr.setLinkToDetails("link " + i);
            pr.setPrice("price " + i);
            pr.setProductName("pc " + i);
            products.add(pr);
        }

        dataService.saveProducts(products);
        SearchedData d1 = new SearchedData();
        SearchedData d2 = new SearchedData();
        SearchedData d3 = new SearchedData();
        d1.setSearch("HP I5");
        d1.setDate(new Date());
        d2.setSearch("DELL I5");
        d2.setDate(new Date());
        d3.setSearch("ASUS I5");
        d3.setDate(new Date());
        dataService.saveSearch(d1);
        dataService.saveSearch(d2);
        dataService.saveSearch(d3);*/

        final Morphia morphia = new Morphia();
        morphia.mapPackage("tn.rnu.eniso.framework.recommendationdata.model");
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "admin");
        datastore.getDB().dropDatabase();
        datastore.ensureIndexes();
        SearchedData d1 = new SearchedData();
        SearchedData d2 = new SearchedData();
        SearchedData d3 = new SearchedData();
        d1.setId(1);
        d1.setSearch("HP I5");
        d1.setDate(new Date());
        d2.setId(2);
        d2.setSearch("DELL I5");
        d2.setDate(new Date());
        d3.setId(3);
        d3.setSearch("ASUS I5");
        d3.setDate(new Date());
        datastore.save(d1);
        datastore.save(d2);
        datastore.save(d3);
        final Query<SearchedData> query = datastore.createQuery(SearchedData.class);
        final List<SearchedData> employees = query.asList();
        System.out.println(employees);
    }
}
