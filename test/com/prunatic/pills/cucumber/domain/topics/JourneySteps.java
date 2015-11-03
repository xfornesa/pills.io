package com.prunatic.pills.cucumber.domain.topics;

import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.topics.TopicJourney;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

/**
 */
public class JourneySteps {
    private Topic topic;

    public JourneySteps() {
    }

    @Given("^a topic$")
    public void createTopic() throws Throwable {
        this.topic = Topic.fromContent("aTopicId", "some title", "some goals");
    }

    @Then("^I will see that its journey is empty$")
    public void assertTopicJourneyIsEmpty() throws Throwable {
        TopicJourney journey = topic.getJourney();
        Assert.assertTrue(journey.isEmpty());
    }
}