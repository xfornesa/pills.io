package com.prunatic.pills.domain.topics.command;

import com.prunatic.pills.domain.pills.PillId;
import com.prunatic.pills.domain.topics.Topic;

import java.util.List;

/**
 */
public class AddPillsToJourneyCommand {
    private final Topic topic;
    private final List<PillId> pillIds;

    public AddPillsToJourneyCommand(Topic topic, List<PillId> pillIds) {
        this.topic = topic;
        this.pillIds = pillIds;
    }

    public Topic getTopic() {
        return topic;
    }

    public List<PillId> getPillIds() {
        return pillIds;
    }
}
