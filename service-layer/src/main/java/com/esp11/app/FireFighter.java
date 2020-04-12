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
public class FireFighter {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private int time;
    private String ID;
    private String name;
    private String alt;
    private String lat;
    private String lng;
    private String temp;
    private String CO2;
    private String pressure;
    private String hr;
    private String hgt;
    private String humidity;
    private String luminosity;
    private String battery;
 
    
    public FireFighter(int time,String[] info){
        this.time = time;
        this.ID = info[0];
        this.name = info[1];
        this.alt = info[2];
        this.lat = info[3];
        this.lng = info[4];
        this.temp = info[5];
        this.CO2 = info[6];
        this.pressure = info[7];
        this.hr = info[8];
        this.hgt = info[9];
        this.humidity = info[10];
        this.luminosity = info[11];
        this.battery = info[12];
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
    @Override
    public String toString() {
    return "";
            
         /*   
        "{ Time=" + times + " , " + country + " , "+ flightID +" , "+ longitude +" , "+ latitude +
        '}';*/
            
  }
}
