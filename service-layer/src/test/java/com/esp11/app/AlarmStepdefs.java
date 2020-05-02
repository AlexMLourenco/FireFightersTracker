package com.esp11.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;

public class AlarmStepdefs {
    
    private String today;
    public FighterHR fighter = new FighterHR();

    @Given("^The firefighter number \"([^\"]*)\"$")
    public void theFirefighterNumber(String id) throws Throwable {
        fighter.setName(id);
    }
    
    @When("^The firefighter heart rate is (\\d+) bpm$")
    public void theFirefighterHeartRateIsBpm(String hr) {
        fighter.setHR(hr);
    }

    @Then("^The alert should be (\\d+)$")
    public void theAlertShouldBe(int on) {
        boolean a = false;
        if (on == 1){
            a = true;
        }else{
            a = false;
        }
        assertEquals(fighter.checkHR(), a);
    }

}
