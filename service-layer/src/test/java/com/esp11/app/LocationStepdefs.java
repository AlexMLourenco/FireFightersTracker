package com.esp11.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import static org.hamcrest.Matchers.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.util.Properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationStepdefs extends SpringIntegrationTest {
    @Autowired
    private RepositoryGPS gpsRep;
    
    
    @Before
    public void setUp() {
        FighterGPS gps = new FighterGPS();
        
        
        List<FighterGPS> array = new ArrayList<>();
        array.add(gps);
        //Mockito.when(gpsRep.findByName("a1")).thenReturn(array);
        
    }
    ObjectMapper mapper = new ObjectMapper();

    @When("I want to know where the firefighter is")
    public void iWantToKnowWhereTheFirefighterIs() throws IOException{
        executeGet("http://localhost:8080//fighters/gps");
    }

    @Then("The firefighter appears on the map")
    public void theFirefighterAppearsOnTheMap() throws JsonMappingException, JsonProcessingException {
        List<FighterGPS> list = gpsRep.findByName("a1");
        

        String body = latestResponse.getBody();
        List<FighterGPS> g = Arrays.asList(mapper.readValue(body, FighterGPS[].class));
        
        //assertThat(list.size(), is(g.size()));
        assertTrue(list.equals(g));
    }



}
    /**
    @Mock
    public FighterGPS FighterGPSMock = mock(FighterGPS.class);

    private String lat = "";
    private String lon = "";

    @Given("The firefighterGPS number {string}")
    public void theFirefighterGPSNumber(String id) {
        FighterGPSMock.setName(id);
    }

    @When("I want to know where the firefighter is")
    public void iWantToKnowWhereTheFirefighterIs() {

    }

    @Then("The firefighter appears on the map")
    public void theFirefighterAppearsOnTheMap() {
        lat = FighterGPSMock.getGps_tag_lat();
        lon = FighterGPSMock.getGps_tag_long();
    }
}
     */
