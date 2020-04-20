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
    private RepositoryGPS repository;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    //private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    public String str = "";
    FighterGPS[] array = new FighterGPS[3];
    
    @GetMapping("/fighters")
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
        Iterable<FighterGPS> ff = repository.findAll();//.forEach(x -> log.info(x.toString()));
        FighterGPS[] fs = Iterables.toArray(ff, FighterGPS.class);
        String jsonString = mapper.writeValueAsString(fs[fs.length-1]);
        
        return jsonString;
    }
    
    @KafkaListener(topics = "gps", groupId = "team")
    public void listenGPS(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject(message);
        Gson gson = new Gson();
        FighterGPS t = gson.fromJson(jsonObject.toString(), FighterGPS.class);
        System.out.println("Received Messasge: " + jsonObject.toString());
        str = message;
        repository.save(t);
        /*
        String[][] ffs;
        ffs = t.getFighters();
           
        for (int i = 0; i < ffs.length; i++) {            
            FireFighter ff = new FireFighter(t.getTime(),ffs[i]);
            repository.save(ff);
            array[i] = ff;
        }
*/
        /*
    }
    @KafkaListener(topics = "hr", groupId = "team")
    public void listenHR(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject(message);
        Gson gson = new Gson();
        FighterGPS t = gson.fromJson(jsonObject.toString(), FighterGPS.class);
        System.out.println("Received Messasge: " + jsonObject.toString());
        str = message;
        repository.save(t);
        /*
        String[][] ffs;
        ffs = t.getFighters();
           
        for (int i = 0; i < ffs.length; i++) {            
            FireFighter ff = new FireFighter(t.getTime(),ffs[i]);
            repository.save(ff);
            array[i] = ff;
        }

    }
    @KafkaListener(topics = "env", groupId = "team")
    public void listenENV(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject(message);
        Gson gson = new Gson();
        FighterGPS t = gson.fromJson(jsonObject.toString(), FighterGPS.class);
        System.out.println("Received Messasge: " + jsonObject.toString());
        str = message;
        repository.save(t);
        /*
        String[][] ffs;
        ffs = t.getFighters();
           
        for (int i = 0; i < ffs.length; i++) {            
            FireFighter ff = new FireFighter(t.getTime(),ffs[i]);
            repository.save(ff);
            array[i] = ff;
        }
*/
    }
}
