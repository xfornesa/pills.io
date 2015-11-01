package com.prunatic.pills.domain.pills.command;

import com.google.common.eventbus.EventBus;
import com.prunatic.pills.domain.pills.Pill;
import com.prunatic.pills.domain.pills.PillsCollection;
import com.prunatic.pills.domain.pills.event.PillAddedEvent;

/**
 */
public class AddPillCommandHandler {

    private final EventBus eventBus;
    private final PillsCollection pillsCollection;

    public AddPillCommandHandler(EventBus eventBus, PillsCollection pillsCollection) {
        this.eventBus = eventBus;
        this.pillsCollection = pillsCollection;
    }

    public void handle(String id, String title, String content, String surveyId) {
        Pill pill = createPill(id, title, content, surveyId);
        pillsCollection.add(pill);
        eventBus.post(new PillAddedEvent(pill.getId()));
    }

    private Pill createPill(String id, String title, String content, String surveyId) {
        return Pill.fromContent(id, title, content, surveyId);
    }
}
