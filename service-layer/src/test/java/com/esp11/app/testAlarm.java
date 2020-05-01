/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp11.app;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import static org.junit.Assert.*;

/**
 *
 * @author manuel
 */
public class testAlarm {
 

    private String today;
    private FighterHR fighter = new FighterHR();

    @Given("the firefighter number $id")
    public void given(String id) {
        fighter.setName(id);
    }

    @When("firefighter heart rate is $hr bpm")
    public void firefighter_heart_rate_is(String hr) {
        fighter.setHR(hr);
    }

    @Then("a alert will be $alert")
    public void i_should_be_told(String alert) {
        boolean a = false;
        if (alert.equals("ON")){
            a = true;
        }else{
           a = false;
        }
        assertEquals(fighter.checkHR(), a);
    }
}

