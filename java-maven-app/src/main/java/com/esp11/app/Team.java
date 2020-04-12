/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp11.app;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.*;
/**
 *
 * @author manuel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
    
     private int time;
    
    private String[][] states;
    
    public Team(){
    
    }
    public void setTime(){
        this.time = time;
    }
    
    public void setString(){
        this.states = states;
    }
    
    public int getTime(){
        return time;
    }
    
    public String[][] getStates(){
        return states;
    }
    public String[][] getString(){
        return states;
    }
    
    @Override
    public String toString() {
    return 
            
            "Value{" +
        "Time=" + time + "states: " + Arrays.toString(states[0]) +
        '}';
  }
}
