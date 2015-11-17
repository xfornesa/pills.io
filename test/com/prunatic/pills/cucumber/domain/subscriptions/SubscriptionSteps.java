package com.prunatic.pills.cucumber.domain.subscriptions;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.prunatic.pills.application.topics.command.SubscribeToTopicCommandHandler;
import com.prunatic.pills.domain.topics.*;
import com.prunatic.pills.domain.topics.command.SubscribeToTopicCommand;
import com.prunatic.pills.domain.topics.event.UserSubscribedToTopicEvent;
import com.prunatic.pills.domain.users.User;
import com.prunatic.pills.domain.users.UserId;
import com.prunatic.pills.domain.users.UsersCollection;
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

    private final EventBus eventBus;
    private final UsersCollection usersCollection;
    private final TopicsCollection topicsCollection;
    private final SubscribeToTopicCommandHandler subscribeToTopicCommandHandler;
    private Topic topic;
    private User user;
    private UserSubscribedToTopicEventListener userSubscribedToTopicEventListener;

    @Inject
    public SubscriptionSteps(
            EventBus eventBus,
            UsersCollection usersCollection,
            TopicsCollection topicsCollection,
            SubscribeToTopicCommandHandler subscribeToTopicCommandHandler
    ) {
        this.eventBus = eventBus;
        this.usersCollection = usersCollection;
        this.topicsCollection = topicsCollection;
        this.subscribeToTopicCommandHandler = subscribeToTopicCommandHandler;
    }

    @Given("^a user and a topic$")
    public void createSomeUserAndSomeTopic() throws Throwable {
        topic = Topic.fromContent("aTopicId", "some title", " some goals");
        topicsCollection.add(topic);
        user = User.fromSubscription("anEmail@example.org");
        usersCollection.add(user);
    }

    @Given("^I am listening for a UserSubscribedToTopicEvent$")
    public void iAmListeningForAUserSubscribedToTopicEvent() throws Throwable {
        userSubscribedToTopicEventListener = new UserSubscribedToTopicEventListener();
        eventBus.register(userSubscribedToTopicEventListener);
    }

    @When("^the user subscribes to that topic$")
    public void subscribeToTopic() throws Throwable {
        subscribeToTopicCommandHandler.handle(new SubscribeToTopicCommand(topic.getId(), user.getId()));
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

    @Then("^I should receive a UserSubscribedToTopicEvent with journeyId, userId and topicId$")
    public void iShouldReceiveAUserSubscribedToTopicEventWithJourneyIdUserIdAndTopicId() throws Throwable {
        Optional<Journey> journeyFound = user.getJourneyByTopic(topic);
        Assert.assertTrue(journeyFound.isPresent());
        journeyFound.ifPresent(journey -> {
            Assert.assertTrue(journey.getJourneyId().equals(userSubscribedToTopicEventListener.journeyId));
            Assert.assertTrue(journey.getTopicId().equals(userSubscribedToTopicEventListener.topicId));
            Assert.assertTrue(journey.getUserId().equals(userSubscribedToTopicEventListener.userId));
        });
    }

    private class UserSubscribedToTopicEventListener {
        public JourneyId journeyId;
        public TopicId topicId;
        public UserId userId;

        @Subscribe
        public void handle(UserSubscribedToTopicEvent event) {
            journeyId = event.getJourneyId();
            topicId = event.getTopicId();
            userId = event.getUserId();
        }
    }
}
