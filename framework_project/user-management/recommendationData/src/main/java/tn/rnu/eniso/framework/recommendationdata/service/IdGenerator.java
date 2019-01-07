/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.recommendationdata.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ismail
 */
@Repository
@Scope("singleton")
public class IdGenerator {

    private long generatedId=0;

    public long getGeneratedId() {
        generatedId++;
        return generatedId;
    }
}
