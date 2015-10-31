package com.prunatic.pills.domain.command.pill;

import com.prunatic.pills.domain.pills.Pill;
import com.prunatic.pills.domain.pills.PillsCollection;

/**
 */
public class AddPillCommand {

    private final PillsCollection pillsCollection;

    public AddPillCommand(PillsCollection pillsCollection) {
        this.pillsCollection = pillsCollection;
    }

    public void execute(String id, String title, String content, String surveyId) {
        Pill pill = createPill(id, title, content, surveyId);
        pillsCollection.add(pill);
    }

    private Pill createPill(String id, String title, String content, String surveyId) {
        return Pill.fromContent(id, title, content, surveyId);
    }
}
