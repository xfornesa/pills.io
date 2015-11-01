package com.prunatic.pills.application.pills.command;

import com.google.common.eventbus.EventBus;
import com.prunatic.pills.domain.pills.Pill;
import com.prunatic.pills.domain.pills.PillsCollection;
import com.prunatic.pills.domain.pills.command.AddPillCommand;
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

    public void handle(AddPillCommand command) {
        Pill pill = createPill(command);
        pillsCollection.add(pill);
        eventBus.post(new PillAddedEvent(pill.getId()));
    }

    private Pill createPill(AddPillCommand command) {
        return Pill.fromContent(command.getId(), command.getTitle(), command.getContent(), command.getSurveyId());
    }
}
