/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp11.app;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

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
/**
 *
 * @author manuel
 */
@Component
@Controller
public class ApiController {
    
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Repository repository;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    public String str = "";
    FireFighter[] array = new FireFighter[3];
    //@Scheduled(fixedRate = 10000)
    @GetMapping("/flight")
    public FireFighter[] reportCurrentTime(Model model) {
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
        
        repository.findAll().forEach(x -> log.info(x.toString()));
        */
        return array;
    }
    
    @KafkaListener(topics = "topicName", groupId = "team")
    public void listen(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Team t = mapper.readValue(message, Team.class);
        System.out.println("Received Messasge: " + message);
        
        String[][] ffs;
        ffs = t.getString();
           
        for (int i = 0; i < ffs.length; i++) {            
            FireFighter ff = new FireFighter(t.getTime(),ffs[i]);
            repository.save(ff);
            array[i] = ff;
        }
    }
}
