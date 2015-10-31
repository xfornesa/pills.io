package com.prunatic.pills.domain.pills.event;

import com.prunatic.pills.domain.common.event.Event;
import com.prunatic.pills.domain.pills.PillId;
import org.joda.time.DateTime;

/**
 */
public class PillAddedEvent implements Event {

    private final DateTime occurredOn;
    private final PillId pillId;

    public PillAddedEvent(PillId pillId) {
        this.pillId = pillId;
        occurredOn = DateTime.now();
    }

    @Override
    public DateTime getOccurredOn() {
        return occurredOn;
    }

    public PillId getPillId() {
        return pillId;
    }
}
