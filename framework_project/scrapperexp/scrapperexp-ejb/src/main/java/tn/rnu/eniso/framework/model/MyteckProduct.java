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
import javax.persistence.Lob;

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
    @Lob
    private String desciption ;
    private String linkToDetails ;
    private String imgPath;



    public MyteckProduct()  {

    }

    public MyteckProduct(int id, String productName, String price, String desciption, String linkToDetails, String imgPath) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.desciption = org.jsoup.parser.Parser.unescapeEntities(desciption, true);
        this.linkToDetails = linkToDetails;
        this.imgPath = imgPath;
    }
    
    
    public MyteckProduct( String productName, String price, String desciption, String linkToDetails , String imgPath) {
        //id = count.incrementAndGet();
        this.productName = productName;
        this.price = price;
        this.desciption = org.jsoup.parser.Parser.unescapeEntities(desciption, true);
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
        String unescapedString = org.jsoup.parser.Parser.unescapeEntities(desciption, true);
                        System.out.println(unescapedString);
                        desciption = unescapedString ;
       // String afterDecoding = StringEscapeUtils.unescapeHtml(beforeDecoding);
        return unescapedString;
    }

    public void setDesciption(String desciption) {
        this.desciption = org.jsoup.parser.Parser.unescapeEntities(desciption, true);
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
