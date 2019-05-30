package com.prunatic.pills.application.topics.command;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.prunatic.pills.application.common.EntityNotFoundException;
import com.prunatic.pills.domain.topics.Journey;
import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.topics.TopicId;
import com.prunatic.pills.domain.topics.TopicsCollection;
import com.prunatic.pills.domain.topics.command.SubscribeToTopicCommand;
import com.prunatic.pills.domain.topics.event.UserSubscribedToTopicEvent;
import com.prunatic.pills.domain.users.User;
import com.prunatic.pills.domain.users.UserId;
import com.prunatic.pills.domain.users.UsersCollection;

import java.util.Optional;

@Singleton
public class SubscribeToTopicCommandHandler {

    private final EventBus eventBus;
    private final TopicsCollection topicsCollection;
    private final UsersCollection usersCollection;

    @Inject
    public SubscribeToTopicCommandHandler(
            EventBus eventBus,
            TopicsCollection topicsCollection,
            UsersCollection usersCollection
    ) {
        this.eventBus = eventBus;
        this.topicsCollection = topicsCollection;
        this.usersCollection = usersCollection;
    }

    public void handle(SubscribeToTopicCommand command) throws EntityNotFoundException {
        Journey journey = doCommand(command);
        raiseEvent(journey);
    }

    private Journey doCommand(SubscribeToTopicCommand command) throws EntityNotFoundException {
        Topic topic = retrieveTopic(command);
        User user = retrieveUser(command);

        return user.subscribesTo(topic);
    }

    private Topic retrieveTopic(SubscribeToTopicCommand command) throws EntityNotFoundException {
        final TopicId topicId = command.getTopicId();
        Optional<Topic> topicFound = topicsCollection.findById(topicId);
        if (!topicFound.isPresent()) {
            throw new EntityNotFoundException(String.format("Entity topic for topicId: %s, not found", topicId));
        }
        return topicFound.get();
    }

    private User retrieveUser(SubscribeToTopicCommand command) throws EntityNotFoundException {
        final UserId userId = command.getUserId();
        Optional<User> userFound = usersCollection.findById(userId);
        if (!userFound.isPresent()) {
            throw new EntityNotFoundException(String.format("Entity user for userId: %s, not found", userId));
        }
        return userFound.get();
    }

    private void raiseEvent(Journey journey) {
        eventBus.post(new UserSubscribedToTopicEvent(journey));
    }
}
