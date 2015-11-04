package com.prunatic.pills.cucumber.domain.topics;

import com.prunatic.pills.domain.pills.PillId;
import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.topics.TopicJourney;
import com.prunatic.pills.domain.topics.command.AddPillToJourneyCommand;
import com.prunatic.pills.application.topics.command.AddPillToJourneyCommandHandler;
import com.prunatic.pills.domain.topics.command.AddPillsToJourneyCommand;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 */
public class JourneySteps {
    private Topic topic;
    private AddPillToJourneyCommandHandler addPillToJourneyCommandHandler;

    public JourneySteps() {
        addPillToJourneyCommandHandler = new AddPillToJourneyCommandHandler();
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

    @When("^I add a pill to its journey$")
    public void addPillToJourney() throws Throwable {
        PillId pillId = PillId.generate();
        addPillToJourneyCommandHandler.handle(new AddPillToJourneyCommand(topic, pillId));
    }

    @When("^I add the following pills to its journey:$")
    public void addPillsToJourney(List<Map<String,String>> data) throws Throwable {
        List<PillId> pillIds = data.stream()
                .map(row -> PillId.fromString(row.get("pillId")))
                .collect(Collectors.toList());
        addPillToJourneyCommandHandler.handle(new AddPillsToJourneyCommand(topic, pillIds));
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
}
