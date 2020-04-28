/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp11.app;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 *
 * @author manuel
 */
//@Component
@RestController
public class ApiController {
    
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RepositoryGPS repositorygps;
    @Autowired
    private RepositoryENV repositoryenv;
    @Autowired
    private RepositoryHR repositoryhr;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    //private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    public String str = "";
    String[] array = {"a1","a2","vr12"};
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/fighters/gps")
    public String reportCurrentTime() throws JsonProcessingException {
        /*
        Team f = restTemplate.getForObject(
        "https://opensky-network.org/api/states/all", Team.class);
        String[][] flights;
        flights = f.getString();
        int c = 0;
        for (int i = 0; i < 100; i++) {
        Plane p = new Plane(f.getTime(),flights[i][2],flights[i][0],flights[i][5], flights[i][6]);
        repository.save(p);
        if (p.getCountry().equals("Canada")){
        c++;
        }
        planes[i] = p;
        }
        log.info("count" + c);
        kafkaTemplate.send("flights", "Adicionados "+ planes.length + " novos registos de voos. Sendo "+ c+ " com origem no Canada." );
        model.addAttribute("planes", planes);
        log.info("Flights in repository:");
         */
        ObjectMapper mapper = new ObjectMapper();
        String[] actual = new String[3];
        for (int i = 0; i < array.length;i++) {
            
            List<FighterGPS> list = repositorygps.findByName(array[i]);
            
            if(list.size() > 0){
                FighterGPS last = list.get(list.size() - 1); 
                actual[i] = mapper.writeValueAsString(last);
            }
            else{
                actual[i] = "{}";
            }   
        }
        
        String strr = Arrays.toString(actual); 
        return strr;
    }
    
    @GetMapping("/fighters/gps")
    public String fightersGPS() throws JsonProcessingException {
    
        ObjectMapper mapper = new ObjectMapper();
        Iterable<FighterGPS> ff = repositorygps.findAll();//.forEach(x -> log.info(x.toString()));
        FighterGPS[] fs = Iterables.toArray(ff, FighterGPS.class);
        String[] gps = new String[fs.length];
        for(int i =0; i < fs.length;i++){
            gps[i] = mapper.writeValueAsString(fs[i]);
        }
        String strr = Arrays.toString(gps);
        return strr;
    }
    
    @GetMapping("/fighters/hr")
    public String fightersHR() throws JsonProcessingException {
    
        ObjectMapper mapper = new ObjectMapper();
        Iterable<FighterHR> ff = repositoryhr.findAll();//.forEach(x -> log.info(x.toString()));
        FighterHR[] fs = Iterables.toArray(ff, FighterHR.class);
        String[] hr = new String[fs.length];
        for(int i =0; i < fs.length;i++){
            hr[i] = mapper.writeValueAsString(fs[i]);
        }
        String strr = Arrays.toString(hr);
        return strr;    
    }
    
    @GetMapping("/fighters/env")
    public String fightersENV() throws JsonProcessingException {
    
        ObjectMapper mapper = new ObjectMapper();
        Iterable<FighterENV> ff = repositoryenv.findAll();//.forEach(x -> log.info(x.toString()));
        FighterENV[] fs = Iterables.toArray(ff, FighterENV.class);
        String[] env = new String[fs.length];
        for(int i =0; i < fs.length;i++){
            env[i] = mapper.writeValueAsString(fs[i]);
        }
        String strr = Arrays.toString(env);
        return strr;
    }
    
    @KafkaListener(topics = "gps", groupId = "team")
    public void listenGPS(String message) throws JsonProcessingException {
        
        JSONObject jsonObject = new JSONObject(message);
        Gson gson = new Gson();
        FighterGPS t = gson.fromJson(jsonObject.toString(), FighterGPS.class);
        System.out.println("Received Messasge: " + jsonObject.toString());
        
        repositorygps.save(t);

    }
    
    @KafkaListener(topics = "hr", groupId = "team")
    public void listenHR(String message) throws JsonProcessingException {
        
        JSONObject jsonObject = new JSONObject(message);
        Gson gson = new Gson();
        FighterHR t = gson.fromJson(jsonObject.toString(), FighterHR.class);
        System.out.println("Received Messasge: " + jsonObject.toString());
        
        repositoryhr.save(t);
    }
 
    @KafkaListener(topics = "env", groupId = "team")
    public void listenENV(String message) throws JsonProcessingException {
        
        JSONObject jsonObject = new JSONObject(message);
        Gson gson = new Gson();
        FighterENV t = gson.fromJson(jsonObject.toString(), FighterENV.class);
        System.out.println("Received Messasge: " + jsonObject.toString());
        
        repositoryenv.save(t);   
    }
}
