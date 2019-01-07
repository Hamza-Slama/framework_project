/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.recommendationdata.service;

import com.mongodb.MongoClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.rnu.eniso.framework.recommendationdata.model.Product;
import tn.rnu.eniso.framework.recommendationdata.model.SearchedData;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

/**
 *
 * @author ismail
 */
@Service
public class SearchedDataService {

    @Autowired
    private IdGenerator idGenerator;

    public void saveProducts(List<Product> products) {
        long id = 1;
        final Morphia morphia = new Morphia();
        morphia.mapPackage("tn.rnu.eniso.framework.recommendationdata.model");
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "admin");
        datastore.getDB().dropDatabase();
        datastore.ensureIndexes();
        for (Product product : products) {
            product.setId(id);
            datastore.save(product);
            id++;
        }
    }

    public void saveSearch(SearchedData data) {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("tn.rnu.eniso.framework.recommendationdata.model");
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "admin");
        data.setId(idGenerator.getGeneratedId());
        datastore.save(data);
    }
}
