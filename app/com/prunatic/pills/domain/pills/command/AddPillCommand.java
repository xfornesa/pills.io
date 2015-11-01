package com.prunatic.pills.domain.pills.command;

public class AddPillCommand {
    private final String id;
    private final String title;
    private final String content;
    private final String surveyId;

    public AddPillCommand(String id, String title, String content, String surveyId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.surveyId = surveyId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getSurveyId() {
        return surveyId;
    }
}
