/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ismail
 */
@Entity
public class Search implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String field;
    @Temporal(TemporalType.DATE)
    private Date searchDate;
    @ManyToOne
    private ApplicationUsr user;

    public Search(long id, String field, Date searchDate, ApplicationUsr user) {
        this.id = id;
        this.field = field;
        this.searchDate = searchDate;
        this.user = user;
    }

    public Search() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public ApplicationUsr getUser() {
        return user;
    }

    public void setUser(ApplicationUsr user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Search other = (Search) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Search{" + "id=" + id + ", field=" + field + ", searchDate=" + searchDate + ", user=" + user + '}';
    }

}
