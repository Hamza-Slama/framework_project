/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.recommendationdata.model;

import java.util.Date;
import xyz.morphia.annotations.Entity;
import xyz.morphia.annotations.Id;

/**
 *
 * @author ismail
 */
@Entity("searched_data")
public class SearchedData {
    @Id
    private long id;
    private String search;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
