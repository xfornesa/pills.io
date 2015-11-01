package com.prunatic.pills.domain.topics.command;

public class AddTopicCommand {
    private final String id;
    private final String title;
    private final String goals;

    public AddTopicCommand(String id, String title, String goals) {
        this.id = id;
        this.title = title;
        this.goals = goals;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGoals() {
        return goals;
    }
}
