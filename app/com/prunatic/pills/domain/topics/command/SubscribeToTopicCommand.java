package com.prunatic.pills.domain.topics.command;

import com.prunatic.pills.domain.topics.TopicId;
import com.prunatic.pills.domain.users.UserId;

public class SubscribeToTopicCommand {
    private final TopicId topicId;
    private final UserId userId;

    public SubscribeToTopicCommand(TopicId topicId, UserId userId) {
        this.topicId = topicId;
        this.userId = userId;
    }

    public TopicId getTopicId() {
        return topicId;
    }

    public UserId getUserId() {
        return userId;
    }
}
