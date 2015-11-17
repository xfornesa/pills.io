package com.prunatic.pills.domain.topics;

import com.prunatic.pills.domain.users.UserId;

public class Journey {
    private final TopicId topicId;
    private final UserId userId;

    private Journey(TopicId topicId, UserId userId) {
        this.topicId = topicId;
        this.userId = userId;
    }

    public static Journey fromSubscription(TopicId topicId, UserId userId) {
        return new Journey(topicId, userId);
    }

    public UserId getUserId() {
        return userId;
    }

    public TopicId getTopicId() {
        return topicId;
    }
}
