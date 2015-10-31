package com.prunatic.pills.domain.pills.event;

import com.prunatic.pills.domain.common.event.Event;
import org.joda.time.DateTime;

/**
 */
public class PillAddedEvent implements Event {

    private final DateTime occurredOn;

    public PillAddedEvent() {
        occurredOn = DateTime.now();
    }

    @Override
    public DateTime occurredOn() {
        return occurredOn;
    }
}
