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
public class alarm {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String type;
    private String state;
    private String date;
    private String value;

    private String fighter;
    
    public alarm(){
        
    }
    public alarm(String fighter){
        this.fighter = fighter;
        this.state = "false";
        
        
    }
    public void setId(Integer id){
        this.id = id;
    }
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
    public void setDate(String date){
        this.date = date;
    }
    public Integer getId(){
        return id;
    }
    public String getDate(){
        return date;
    }
    
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return state;
    }
    public void setFighter(String fighter){
        this.fighter = fighter;
    }
    public String getFighter(){
        return fighter;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
    
}
