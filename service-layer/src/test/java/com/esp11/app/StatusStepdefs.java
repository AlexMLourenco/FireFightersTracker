package com.esp11.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StatusStepdefs {

    private String today;
    public FighterHR fighter = new FighterHR();

    @Given("^The firefighter number \"([^\"]*)\"$")
    public void theFirefighterNumber(String id) throws Throwable {
        fighter.setName(id);
    }

    @When("I want to check the informations of the firefighter")
    public void iWantToCheckTheInformationsOfTheFirefighter() {
    }

    @Then("The firefighter status will appear on a window in the map")
    public void theFirefighterStatusWillAppearOnAWindowInTheMap() {
    }
}
