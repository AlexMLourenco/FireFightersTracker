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
public class FighterHR {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String date;
    private String name;
    private String type;
    private String hr;
    
    public FighterHR(){
        
    }
    public Integer getId(){
        return id;
    }
    public String getDate(){
        return date;
    }
    public String getName(){
        return name;
    }public String getType(){
        return type;
    }
    public String getHr(){
        return hr;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setHR(String hr){
        this.hr = hr;
    }
    
    public boolean checkHR(){
        int rate = Integer.parseInt(this.hr);
        if (rate > 100){
            return true;  
        }
        else{
            return false;
        }
    }
   
}


