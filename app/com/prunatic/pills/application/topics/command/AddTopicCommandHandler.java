package com.prunatic.pills.application.topics.command;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.prunatic.pills.application.common.CommandHandler;
import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.topics.TopicsCollection;
import com.prunatic.pills.domain.topics.command.AddTopicCommand;
import com.prunatic.pills.domain.topics.event.TopicAddedEvent;

/**
 */
@Singleton
public class AddTopicCommandHandler implements CommandHandler {
    private final EventBus eventBus;
    private final TopicsCollection topicsCollection;

    @Inject
    public AddTopicCommandHandler(EventBus eventBus, TopicsCollection topicsCollection) {
        this.eventBus = eventBus;
        this.topicsCollection = topicsCollection;
    }


    public void handle(AddTopicCommand command) {
        Topic topic = createTopic(command);
        topicsCollection.add(topic);
        eventBus.post(new TopicAddedEvent(topic.getId()));
    }

    private Topic createTopic(AddTopicCommand addTopicCommand) {
        return Topic.fromContent(addTopicCommand.getId(), addTopicCommand.getTitle(), addTopicCommand.getGoals());
    }
}
