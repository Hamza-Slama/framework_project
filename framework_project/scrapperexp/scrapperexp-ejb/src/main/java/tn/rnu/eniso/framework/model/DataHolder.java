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

    public class DataHolder implements Serializable {

       @Id
       @GeneratedValue
       private int id ; 
       private String Website ;
       private int nbrLink;
       private Boolean visited ;

    @Override
    public String toString() {
        return "DataHolder{" + "id=" + id + ", Website=" + Website + ", nbrLink=" + nbrLink + ", visited=" + visited + '}';
    }

       
        
    @Override
         public int hashCode(){int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.Website);
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
        final DataHolder other = (DataHolder) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.Website, other.Website)) {
            return false;
        }
        return true;
    }

    public DataHolder() {
    }


        public DataHolder(String website, int nbrLink, boolean visited) {
            Website = website;
            this.nbrLink = nbrLink;
            this.visited = visited;
        }

        public String getWebsite() {
            return Website;
        }

        public int getNbrLink() {
            return nbrLink;
        }

        public Boolean getVisited() {
            return visited;
        }

        public void setWebsite(String website) {
            Website = website;
        }

        public void setNbrLink(int nbrLink) {
            this.nbrLink = nbrLink;
        }

        public void setVisited(Boolean visited) {
            this.visited = visited;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        

    }



