package com.prunatic.pills.application.topics.command;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.prunatic.pills.application.common.CommandHandler;
import com.prunatic.pills.domain.pills.PillId;
import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.topics.command.AddPillToJourneyCommand;
import com.prunatic.pills.domain.topics.command.AddPillsToJourneyCommand;
import com.prunatic.pills.domain.topics.event.PillAddedToJourneyEvent;

/**
 */
@Singleton
public class AddPillToJourneyCommandHandler implements CommandHandler {
    private final EventBus eventBus;

    @Inject
    public AddPillToJourneyCommandHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void handle(AddPillToJourneyCommand command) {
        final Topic topic = command.getTopic();
        final PillId pillId = command.getPillId();

        topic.addPillToJourney(pillId);

        eventBus.post(new PillAddedToJourneyEvent(topic.getId(), pillId));
    }

    public void handle(AddPillsToJourneyCommand command) {
        Topic topic = command.getTopic();

        topic.addPillsToJourney(command.getPillIds());
    }
}
