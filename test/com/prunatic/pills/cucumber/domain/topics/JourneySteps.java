package com.prunatic.pills.cucumber.domain.topics;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.prunatic.pills.application.topics.command.AddPillToJourneyCommandHandler;
import com.prunatic.pills.domain.pills.PillId;
import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.topics.TopicId;
import com.prunatic.pills.domain.topics.TopicJourney;
import com.prunatic.pills.domain.topics.command.AddPillToJourneyCommand;
import com.prunatic.pills.domain.topics.command.AddPillsToJourneyCommand;
import com.prunatic.pills.domain.topics.event.PillAddedToJourneyEvent;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 */
@ScenarioScoped
public class JourneySteps {
    private Topic topic;
    private AddPillToJourneyCommandHandler addPillToJourneyCommandHandler;
    private PillAddedToJourneyListener pillAddedToJourneyListener;
    private EventBus eventBus;
    private PillId pillId;

    @Inject
    public JourneySteps(
            EventBus eventBus,
            AddPillToJourneyCommandHandler addPillToJourneyCommandHandler
    ) {
        this.eventBus = eventBus;
        this.addPillToJourneyCommandHandler = addPillToJourneyCommandHandler;
    }

    @Given("^a topic$")
    public void createTopic() throws Throwable {
        this.topic = Topic.fromContent("aTopicId", "some title", "some goals");
    }

    @Given("^I am listening for a PillAddedToJourneyEvent$")
    public void registerPillAddedToJourneyEventListener() throws Throwable {
        pillAddedToJourneyListener = new PillAddedToJourneyListener();
        eventBus.register(pillAddedToJourneyListener);
    }

    @Then("^I will see that its journey is empty$")
    public void assertTopicJourneyIsEmpty() throws Throwable {
        TopicJourney journey = topic.getJourney();
        Assert.assertTrue(journey.isEmpty());
    }

    @When("^I add a pill to its journey$")
    public void addPillToJourney() throws Throwable {
        pillId = PillId.generate();
        addPillToJourneyCommandHandler.handle(new AddPillToJourneyCommand(topic, pillId));
    }

    @When("^I add the following pills to its journey:$")
    public void addPillsToJourney(List<Map<String,String>> data) throws Throwable {
        List<PillId> pillIds = data.stream()
                .map(row -> PillId.fromString(row.get("pillId")))
                .collect(Collectors.toList());
        addPillToJourneyCommandHandler.handle(new AddPillsToJourneyCommand(topic, pillIds));
    }

    @When("^I add a pill to a topic's journey$")
    public void createTopicAndAddPillToJourney() throws Throwable {
        createTopic();
        pillId = PillId.generate();
        addPillToJourneyCommandHandler.handle(new AddPillToJourneyCommand(topic, pillId));
    }

    @Then("^I will see that its journey is not empty$")
    public void assertTopicJourneyIsNotEmpty() throws Throwable {
        TopicJourney journey = topic.getJourney();
        Assert.assertFalse(journey.isEmpty());
    }

    @Then("^I will see the following ordered pills in its journey:$")
    public void assertPillsOnJourney(List<String> data) throws Throwable {
        List<PillId> expected = data.stream()
                .map(PillId::fromString)
                .collect(Collectors.toList());
        List<PillId> actual = topic.getPillsInJourney();
        Assert.assertEquals(expected, actual);
    }

    @Then("^I should receive a PillAddedToJourneyEvent with its topicId and pillId$")
    public void checkPillAddedToJourneyEventReceived() throws Throwable {
        Assert.assertTrue(topic.getId().equals(pillAddedToJourneyListener.topicId));
        Assert.assertTrue(pillId.equals(pillAddedToJourneyListener.pillId));
    }

    private class PillAddedToJourneyListener {
        public TopicId topicId;
        public PillId pillId;

        @Subscribe
        public void handle(PillAddedToJourneyEvent event) {
            topicId = event.getTopicId();
            pillId = event.getPillId();
        }
    }
}
