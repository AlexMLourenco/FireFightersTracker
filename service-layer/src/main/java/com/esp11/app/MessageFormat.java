/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp11.app;

/**
 *
 * @author manuel
 */
public class MessageFormat {
    private String id;
    private String gps;
    private String env;
    private String hr;
    
    
    public MessageFormat(){
       
    }
    public String getId(){
        return id;
    }
    public String getGPS(){
        return gps;
    }
    public String getENV(){
        return env;
    }
    public String getHR(){
        return hr;
    }
    public void setID(String id){
        this.id = id;
    }
    public void setGPS(String gps){
        this.gps = gps;
    }
    public void setENV(String env){
        this.env = env;
    }public void setHR(String hr){
        this.hr = hr;
    }
    
    
}
