package com.esp11.app;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.esp11.app.kafka.Consumer;
import com.esp11.app.kafka.Producer;

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
        //producer.emit(TOPIC_SUBSCRIPTIONS, "subscribe qwe-asd-zxc-101");
        //snapshot = getConsumerRecords();

    }

    @When("I want to know where the firefighter is")
    public void iWantToKnowWhereTheFirefighterIs() {

    }

    @Then("The firefighter appears on the map")
    public void theFirefighterAppearsOnTheMap() {

    }

}
/**
    @Given("^that we know a number of words processed previously$")
    public void getSnapshotForCounts()
    {
        producer.emit(TOPIC_SUBSCRIPTIONS, "subscribe qwe-asd-zxc-101");
        snapshot = getConsumerRecords();
    }

    @When("^I send a new word \"([^\"]*)\"$")
    public void sendWord( String word )
    {
        producer.emit(TOPIC_PRODUCER, word);
    }

    @Then("^I should receive count for \"([^\"]*)\" word increased by (\\d+)$")
    public void iShouldReceiveCountForWordIncreasedBy( String word, int delta )
    {
        Map<String, Integer> actual = getConsumerRecords();

        Map<String, Integer> expected = new HashMap<>();
        expected.put(word, snapshot.getOrDefault(word, 0) + delta);

        Assert.assertEquals("Rows in kafka doesn't match expected", expected, actual);
    }

    private Map<String, Integer> getConsumerRecords()
    {
        return consumer.consume();
    }
}*/
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
