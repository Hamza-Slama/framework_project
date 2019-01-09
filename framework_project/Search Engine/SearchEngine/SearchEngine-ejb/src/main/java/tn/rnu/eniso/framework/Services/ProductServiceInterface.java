/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.Services;

import java.util.List;
import javax.ejb.Remote;
import tn.rnu.eniso.framework.models.Product;

/**
 *
 * @author Bacem
 */
@Remote
public interface ProductServiceInterface {
      public List<Product> findAll() ;
     
}
