/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.IndexingCore;

import java.util.List;
import org.apache.lucene.document.Document;


/**
 *
 * @author Bacem
 */
public interface DocumentBuilderInterface<T> {
    public Document buildDocument();
     public void setEntitySource(T u) ;
}
