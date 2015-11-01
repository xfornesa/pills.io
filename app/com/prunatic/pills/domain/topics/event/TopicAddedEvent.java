package com.prunatic.pills.domain.topics.event;

import com.prunatic.pills.domain.common.event.Event;
import com.prunatic.pills.domain.topics.TopicId;
import org.joda.time.DateTime;

/**
 */
public class TopicAddedEvent implements Event {
    private final DateTime occurredOn;
    private final TopicId topicId;

    public TopicAddedEvent(TopicId topicId) {
        this.topicId = topicId;
        this.occurredOn = DateTime.now();
    }

    @Override
    public DateTime getOccurredOn() {
        return occurredOn;
    }

    public TopicId getTopicId() {
        return topicId;
    }
}
