package com.prunatic.pills.domain.topics.command;

import com.prunatic.pills.domain.pills.PillId;
import com.prunatic.pills.domain.topics.Topic;

/**
 */
public class AddPillToJourneyCommand {
    final private Topic topic;
    final private PillId pillId;

    public AddPillToJourneyCommand(Topic topic, PillId pillId) {
        this.topic = topic;
        this.pillId = pillId;
    }

    public Topic getTopic() {
        return topic;
    }

    public PillId getPillId() {
        return pillId;
    }
}
