/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author hamzewi
 */
 @Entity
public class MyteckProduct implements Serializable {
   
    // private static  final AtomicInteger count = new AtomicInteger(0);
    @Id
    @GeneratedValue
    private int id ;
    private String productName ;
    private String price ;
    private String desciption ;
    private String linkToDetails ;
    private String imgPath;



    public MyteckProduct()  {

    }
    
    public MyteckProduct( String productName, String price, String desciption, String linkToDetails , String imgPath) {
        //id = count.incrementAndGet();
        this.productName = productName;
        this.price = price;
        this.desciption = desciption;
        this.linkToDetails = linkToDetails;
        this.imgPath= imgPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getLinkToDetails() {
        return linkToDetails;
    }

    public void setLinkToDetails(String linkToDetails) {
        this.linkToDetails = linkToDetails;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyteckProduct that = (MyteckProduct) o;
        return id == that.id &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(price, that.price) &&
                Objects.equals(desciption, that.desciption) &&
                Objects.equals(linkToDetails, that.linkToDetails);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, productName, price, desciption, linkToDetails);
    }
}
