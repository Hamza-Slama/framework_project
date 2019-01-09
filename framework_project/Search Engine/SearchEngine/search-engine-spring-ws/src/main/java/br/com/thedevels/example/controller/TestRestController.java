/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thedevels.example.controller;

import static org.eclipse.persistence.logging.SessionLog.EJB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.rnu.eniso.framework.Services.ProductService;


/**
 *
 * @author Dell-Junior
 */
@RestController
public class TestRestController {
    

    
    @RequestMapping("/")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return   "Hello ," + name ;
    }
    @RequestMapping("/ser")
    public String greetings(@RequestParam(value="name", defaultValue="World") String name) {
        return   "Hello ," + name + "**************************" ;
    }
    
}
