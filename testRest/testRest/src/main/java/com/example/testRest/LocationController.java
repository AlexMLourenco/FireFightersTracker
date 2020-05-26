/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testRest;

/**
 *
 * @author manuel
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @RequestMapping(method = { RequestMethod.GET }, value = { "/location" })
    public String getVersion() {
        return "{\"name\": \"Manuel\", \"longitude\": 20.56211, \"latitude\": \"19.34563\"}";
    }
}