/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import tn.rnu.eniso.framework.Services.ProductService;
import tn.rnu.eniso.framework.models.Product;

/**
 *
 * @author Bacem
 */
@ManagedBean
@RequestScoped
public class ProductsList {
    @EJB ProductService metier;
    private List<Product> model=new ArrayList<>();

  @PostConstruct
    private void init(){
        model=metier.findAll();
    }
    public List<Product> getModel() {
        return model;
    }
   
}
