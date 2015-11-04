package com.prunatic.pills.cucumber.domain.topics;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.prunatic.pills.domain.topics.InMemoryTopicsCollection;
import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.topics.TopicId;
import com.prunatic.pills.domain.topics.TopicsCollection;
import com.prunatic.pills.domain.topics.command.AddTopicCommand;
import com.prunatic.pills.application.topics.command.AddTopicCommandHandler;
import com.prunatic.pills.domain.topics.event.TopicAddedEvent;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

/**
 */
public class TopicsSteps {
    private final TopicsCollection topicsCollection;
    private final AddTopicCommandHandler addTopicCommandHandler;
    private final EventBus eventBus;

    private List<Topic> topics;
    private TopicAddedEventListener topicAddedEventListener;
    private TopicId addedTopicId;

    public TopicsSteps() {
        eventBus = new EventBus();
        topicsCollection = new InMemoryTopicsCollection();
        addTopicCommandHandler = new AddTopicCommandHandler(eventBus, topicsCollection);
    }

    @Given("^the following topics collection:$")
    public void addTopicsToCollection(List<Map<String, String>> rows) throws Throwable {
        rows.parallelStream()
            .forEach(row -> addTopicFromContent(row.get("id"), row.get("title"), row.get("goals")));
    }

    private void addTopicFromContent(String id, String title, String goals) {
        addTopicCommandHandler.handle(new AddTopicCommand(id, title, goals));
    }

    @When("^I get all the topics$")
    public void getTopics() throws Throwable {
        topics = topicsCollection.findAll();
    }

    @Then("^I found the following topics:$")
    public void checkTopicsList(List<String> rows) throws Throwable {
        rows.parallelStream()
            .map(TopicId::fromString)
            .forEach(topicId -> {
                final boolean found = checkTopicExists(topicId);
                Assert.assertTrue(String.format("Have not found any topic with id '%s'", topicId), found);
            });
    }

    private boolean checkTopicExists(TopicId topicId) {
        return topics.parallelStream().anyMatch(topic -> topicId.equals(topic.getId()));
    }

    @Given("^I am listening for a TopicAddedEvent$")
    public void registerTopicAddedEventListener() throws Throwable {
        topicAddedEventListener = new TopicAddedEventListener();
        eventBus.register(topicAddedEventListener);
    }

    @When("^I add a topic$")
    public void addSomeTopic() throws Throwable {
        addedTopicId = TopicId.fromString("anId");
        addTopicFromContent(addedTopicId.toString(), "someTitle", "some goals");
    }

    @Then("^I should receive a TopicAddedEvent with its topicId$")
    public void checkTopicAddedEventReceived() throws Throwable {
        Assert.assertTrue(addedTopicId.equals(topicAddedEventListener.topicId));
    }

    private class TopicAddedEventListener {
        public TopicId topicId;

        @Subscribe
        public void handle(TopicAddedEvent event) {
            topicId = event.getTopicId();
        }
    }
}
