package com.esp11.app;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Properties;
import com.esp11.app.kafka.Consumer;
import com.esp11.app.kafka.Producer;
import java.util.ArrayList;
import java.util.List;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import org.mockito.Mock;

import static org.mockito.Mockito.mock;

public class LocationStepdefs {

    private static final String TOPIC_PRODUCER = "gps";

    private static final String TOPIC_SUBSCRIPTIONS = "control";
    
    

    
    private static final String TOPIC_CONSUMER = "gps";

    private final Producer producer = new Producer();

    private final Consumer consumer = new Consumer(TOPIC_CONSUMER);

    private Map<String, Integer> snapshot = new HashMap<>();

    @Given("The firefighterGPS number {string}")
    public void theFirefighterGPSNumber(String id) {
        //snapshot = getConsumerRecords();
        snapshot = getConsumerRecords();
        
    }

    @When("I want to know where the firefighter is")
    public void iWantToKnowWhereTheFirefighterIs() {
        
        producer.emit(TOPIC_PRODUCER, "ola");
    }

    @Then("The firefighter appears on the map")
    public void theFirefighterAppearsOnTheMap() {
        Map<String, Integer> actual = getConsumerRecords();
        Integer i = actual.get("ola");
        System.out.print(i);
    }



       
    private Map<String, Integer> getConsumerRecords()
    {
        return consumer.consume();
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
