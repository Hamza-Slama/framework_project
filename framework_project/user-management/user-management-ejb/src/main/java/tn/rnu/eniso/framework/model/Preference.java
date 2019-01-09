/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ismail
 */
@Entity
public class Preference implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String productName;
    @Column(length = 20)
    private String price;
    @Column(length = 20)
    private String processor;
    @Column(length = 20)
    private String frequence;
    @Column(length = 50)
    private String memory;
    @Column(length = 50)
    private String screen;
    @Column(length = 50)
    private String hardDisk;
    @Column(length = 50)
    private String GraphicCard;
    private String linkToDetails;
    private String linkToImage;
    @Temporal(TemporalType.DATE)
    private Date preferenceDate;
    private int rank;
    private ApplicationUsr user;

    public Preference(long id, String productName, String price, String processor, String frequence, String memory, String screen, String hardDisk, String GraphicCard, String linkToDetails, String linkToImage, Date preferenceDate, int rank, ApplicationUsr user) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.processor = processor;
        this.frequence = frequence;
        this.memory = memory;
        this.screen = screen;
        this.hardDisk = hardDisk;
        this.GraphicCard = GraphicCard;
        this.linkToDetails = linkToDetails;
        this.linkToImage = linkToImage;
        this.preferenceDate = preferenceDate;
        this.rank = rank;
        this.user = user;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Date getPreferenceDate() {
        return preferenceDate;
    }

    public void setPreferenceDate(Date preferenceDate) {
        this.preferenceDate = preferenceDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public String getGraphicCard() {
        return GraphicCard;
    }

    public void setGraphicCard(String GraphicCard) {
        this.GraphicCard = GraphicCard;
    }

    public String getLinkToDetails() {
        return linkToDetails;
    }

    public void setLinkToDetails(String linkToDetails) {
        this.linkToDetails = linkToDetails;
    }

    public String getLinkToImage() {
        return linkToImage;
    }

    public void setLinkToImage(String linkToImage) {
        this.linkToImage = linkToImage;
    }

    public ApplicationUsr getUser() {
        return user;
    }

    public void setUser(ApplicationUsr user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Preference{" + "id=" + id + ", productName=" + productName + ", price=" + price + ", processor=" + processor + ", frequence=" + frequence + ", memory=" + memory + ", screen=" + screen + ", hardDisk=" + hardDisk + ", GraphicCard=" + GraphicCard + ", linkToDetails=" + linkToDetails + ", linkToImage=" + linkToImage + ", preferenceDate=" + preferenceDate + ", rank=" + rank + ", user=" + user + '}';
    }

    public Preference() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Preference other = (Preference) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
