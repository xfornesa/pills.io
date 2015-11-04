package com.prunatic.pills.application.topics.command;

import com.prunatic.pills.application.common.CommandHandler;
import com.prunatic.pills.domain.pills.PillId;
import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.topics.command.AddPillToJourneyCommand;

/**
 */
public class AddPillToJourneyCommandHandler implements CommandHandler {
    public void handle(AddPillToJourneyCommand command) {
        Topic topic = command.getTopic();
        PillId pillId = command.getPillId();
        topic.addPillToJourney(pillId);
    }
}
