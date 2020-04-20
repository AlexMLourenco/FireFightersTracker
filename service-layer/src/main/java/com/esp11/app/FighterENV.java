/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp11.app;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *
 * @author manuel
 */
@Entity
public class FighterENV {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String date;
    private String id_ff;
    private String type;
    private String hr;
    
    public FighterENV(){
        
    }
    public Integer getId(){
        return id;
    }
    public String getDate(){
        return date;
    }
    public String getId_ff(){
        return id_ff;
    }public String getType(){
        return type;
    }
    public String getHr(){
        return hr;
    }
    
}
