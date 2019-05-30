package com.prunatic.pills.domain.topics;

import com.prunatic.pills.domain.users.UserId;

import java.sql.Timestamp;

public class Journey {
    private final TopicId topicId;
    private final UserId userId;
    private JourneyId journeyId;

    private Journey(TopicId topicId, UserId userId) {
        this.journeyId = JourneyId.generate();
        this.topicId = topicId;
        this.userId = userId;
    }

    public static Journey fromSubscription(TopicId topicId, UserId userId) {
        return new Journey(topicId, userId);
    }

    public JourneyId getJourneyId() {
        return journeyId;
    }

    public UserId getUserId() {
        return userId;
    }

    public TopicId getTopicId() {
        return topicId;
    }
}
