package com.prunatic.pills.cucumber.domain.subscriptions;

import com.google.inject.Inject;
import com.prunatic.pills.domain.topics.Journey;
import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.users.User;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.junit.Assert;

import java.util.Optional;

/**
 */
@ScenarioScoped
public class SubscriptionSteps {

    private Topic topic;
    private User user;

    @Inject
    public SubscriptionSteps() {
    }

    @Given("^a user and a topic$")
    public void createSomeUserAndSomeTopic() throws Throwable {
        topic = Topic.fromContent("aTopicId", "some title", " some goals");
        user = User.fromSubscription("anEmail@example.org");
    }

    @When("^the user subscribes to that topic$")
    public void subscribeToTopic() throws Throwable {
        user.subscribeTo(topic);
    }

    @Then("^it will exists a journey for that user on that topic$")
    public void checkJourneyForUserAndTopic() throws Throwable {
        Optional<Journey> journeyFound = user.getJourneyByTopic(topic);
        Assert.assertTrue(journeyFound.isPresent());
        journeyFound.ifPresent(journey -> {
            Assert.assertTrue(user.getId().equals(journey.getUserId()));
            Assert.assertTrue(topic.getId().equals(journey.getTopicId()));
        });
    }
}
