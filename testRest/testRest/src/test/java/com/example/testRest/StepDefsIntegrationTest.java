/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testRest;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import cucumber.api.java.en.Given;
import org.springframework.http.HttpStatus;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Arrays;
import java.util.List;

public class StepDefsIntegrationTest extends TestRestApplicationTests {

    ObjectMapper mapper = new ObjectMapper();
   
    @When("^the client calls /baeldung$")
    public void the_client_issues_POST_hello() throws Throwable {
        executePost();
    }

    @Given("^the client calls /hello$")
    public void the_client_issues_GET_hello() throws Throwable {
        executeGet("http://localhost:8082/hello");
    }

    @When("^the client calls /location$")
    public void the_client_issues_GET_version() throws Throwable {
        executeGet("http://localhost:8082/location");
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @And("^the client receives fighter latitude (.+)$")
    public void the_client_receives_fighter_latitude(String latitude) throws Throwable {
        String gps = latestResponse.getBody();
        List<FighterGPS> fighters = Arrays.asList(mapper.readValue(gps, FighterGPS.class));
        FighterGPS g= fighters.get(0);
        assertThat(g.getLatitude(), is(latitude));
    }
    @And("^the client receives fighter longitude (.+)$")
    public void the_client_receives_fighter_longitude(String longitude) throws Throwable {
        String gps = latestResponse.getBody();
        List<FighterGPS> fighters = Arrays.asList(mapper.readValue(gps, FighterGPS.class));
        FighterGPS g= fighters.get(0);
        assertThat(g.getLongitude(), is(longitude));
    }
    @Then("^the client receives server version (.+)$")
    public void the_client_receives_server_version_hello(String version) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertThat(latestResponse.getBody(), is(version));
    }
}
