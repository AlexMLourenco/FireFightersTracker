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
    private String date;
    private String fighterID;
    private String type;
    private String gps_alt_tag;
    private String gps_tag_lat;
    private String gps_tag_long;
    private String gps_time_tag;
    
 
    
    public FireFighter(){
        
    }
    public Integer getId() {
        return id;
    }
    public String getDate(){
        return date;
    }
    public String getfighterID(){
        return fighterID;
    }
    public String getType(){
        return type;
    }
    public String getGps_alt_tag(){
        return gps_alt_tag;
    }
    public String getGps_tag_lat(){
        return gps_tag_lat;
    }
    public String getGps_tag_long(){
        return gps_tag_long;
    }
    public String getGps_time_tag(){
        return gps_alt_tag;
    }
    public void setId() {
        this.id = id;
    }
    
    
    
    @Override
    public String toString() {
    return "";
           //"{ date:" + date +",gps_time_tag:"+gps_time_tag+",gps_tag_lat:"+gps_tag_lat+,"fighterID":"vr12","gps_alt_tag":"1142","gps_tag_long":"-8.16043226","type":"gps"}" 
         /*   
        "{ Time=" + times + " , " + country + " , "+ flightID +" , "+ longitude +" , "+ latitude +
        '}';*/
         
    }
}
