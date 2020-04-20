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
    private String id_ff;
    private String type;
    private String co;
    private String temp;
    private String hgt;
    private String no2;
    private String hum;
    private String lum;
    private String battery;
    
    public FighterHR(){
        
    }
    public Integer getId(){
        return id;
    }
    public String getDate(){
        return date;
    }
    public String getId_ff(){
        return id_ff;
    }
    public String getType(){
        return type;
    }
    public String getCo(){
        return co;
    }
    public String getTemp(){
        return temp;
    }
    public String getHgt(){
        return hgt;
    }
    public String getNo2(){
        return no2;
    }
    public String getHum(){
        return hum;
    }
    public String getLum(){
        return lum;
    }
    public String getBattery(){
        return battery;
    }
}
