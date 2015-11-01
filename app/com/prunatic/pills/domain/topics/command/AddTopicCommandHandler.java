package com.prunatic.pills.domain.topics.command;

import com.google.common.eventbus.EventBus;
import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.topics.TopicsCollection;
import com.prunatic.pills.domain.topics.event.TopicAddedEvent;

/**
 */
public class AddTopicCommandHandler {
    private final EventBus eventBus;
    private final TopicsCollection topicsCollection;

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
