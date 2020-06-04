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
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        dryRun = false,
        features = "src/test/resources",
        glue = "com.esp11.app",
        plugin = {"json:target/Cucumber.json"}
)
public class LocationIntegrationTest {
    
}
