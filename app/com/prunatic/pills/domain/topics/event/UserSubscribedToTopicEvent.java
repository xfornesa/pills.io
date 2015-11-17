package com.prunatic.pills.domain.topics.event;

import com.prunatic.pills.domain.common.event.Event;
import com.prunatic.pills.domain.topics.Journey;
import com.prunatic.pills.domain.topics.JourneyId;
import com.prunatic.pills.domain.topics.TopicId;
import com.prunatic.pills.domain.users.UserId;
import org.joda.time.DateTime;

/**
 */
public class UserSubscribedToTopicEvent implements Event {
    private final DateTime occurredOn;
    private final JourneyId journeyId;
    private TopicId topicId;
    private UserId userId;

    public UserSubscribedToTopicEvent(Journey journey) {
        this.journeyId = journey.getJourneyId();
        this.topicId = journey.getTopicId();
        this.userId = journey.getUserId();
        this.occurredOn = DateTime.now();
    }

    @Override
    public DateTime getOccurredOn() {
        return occurredOn;
    }

    public JourneyId getJourneyId() {
        return journeyId;
    }

    public TopicId getTopicId() {
        return topicId;
    }

    public UserId getUserId() {
        return userId;
    }
}
