package com.esp11.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InformationStepdefs {

    private String today;
    public FighterHR fighter = new FighterHR();

    @Given("^The firefighter number \"([^\"]*)\"$")
    public void theFirefighterNumber(String id) throws Throwable {
        fighter.setName(id);
    }

    @When("I want to check the firefighter global information")
    public void iWantToCheckTheFirefighterGlobalInformation() {
    }

    @Then("The firefighter information about GPS, Environment and Heart Rate will appear on the dashboard")
    public void theFirefighterInformationAboutGPSEnvironmentAndHeartRateWillAppearOnTheDashboard() {
    }
}
