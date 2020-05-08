package com.esp11.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LocationStepdefs {

    private String today;
    public FighterHR fighter = new FighterHR();

    @Given("^The firefighter number \"([^\"]*)\"$")
    public void theFirefighterNumber(String id) throws Throwable {
        fighter.setName(id);
    }

    @When("I want to know where the firefighter is")
    public void iWantToKnowWhereTheFirefighterIs() {
    }

    @Then("The firefighter appears on the map")
    public void theFirefighterAppearsOnTheMap() {
    }
}
