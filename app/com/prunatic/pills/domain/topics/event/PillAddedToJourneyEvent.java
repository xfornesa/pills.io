package com.prunatic.pills.domain.topics.event;

import com.prunatic.pills.domain.common.event.Event;
import com.prunatic.pills.domain.pills.PillId;
import com.prunatic.pills.domain.topics.TopicId;
import org.joda.time.DateTime;

/**
 */
public class PillAddedToJourneyEvent implements Event {
    private final DateTime occurredOn;
    private final TopicId topicId;
    private final PillId pillId;

    public PillAddedToJourneyEvent(TopicId topicId, PillId pillId) {
        this.topicId = topicId;
        this.pillId = pillId;
        this.occurredOn = DateTime.now();
    }

    @Override
    public DateTime getOccurredOn() {
        return occurredOn;
    }

    public TopicId getTopicId() {
        return topicId;
    }

    public PillId getPillId() {
        return pillId;
    }
}
