/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testRest;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *
 * @author manuel
 */

public class FighterGPS {
    
   

    private String name;

    private String latitude;
    private String longitude;

    
 
    
    public FighterGPS(){
        
    }
    
    public String getName(){
        return name;
    }
   
    public String getLatitude(){
        return latitude;
    }
    public String getLongitude(){
        return longitude;
    }
    public void setName(String name){
        this.name = name;
    }
}
