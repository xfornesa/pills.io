package com.prunatic.pills.application.topics.command;

import com.prunatic.pills.application.common.CommandHandler;
import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.topics.command.AddPillToJourneyCommand;
import com.prunatic.pills.domain.topics.command.AddPillsToJourneyCommand;

/**
 */
public class AddPillToJourneyCommandHandler implements CommandHandler {
    public void handle(AddPillToJourneyCommand command) {
        Topic topic = command.getTopic();

        topic.addPillToJourney(command.getPillId());
    }

    public void handle(AddPillsToJourneyCommand command) {
        Topic topic = command.getTopic();

        topic.addPillsToJourney(command.getPillIds());
    }
}
