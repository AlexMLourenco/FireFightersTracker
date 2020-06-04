/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testRest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class BaeldungController {

    @GetMapping("/hello")
    public String sayHello(HttpServletResponse response) {
        return "hello";
    }

    @PostMapping("/baeldung")
    public String sayHelloPost(HttpServletResponse response) {
        return "hello";
    }
}
