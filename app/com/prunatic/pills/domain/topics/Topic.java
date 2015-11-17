package com.prunatic.pills.domain.topics;

import com.prunatic.pills.domain.pills.PillId;
import com.prunatic.pills.domain.users.UserId;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Topic {
    private TopicId topicId;
    private String title;
    private String goals;
    private TopicJourney journey;

    private Topic() {
        journey = new TopicJourney();
    }

    public static Topic fromContent(String id, String title, String goals) {
        final Topic result = new Topic();

        result.topicId = TopicId.fromString(id);
        result.title = title;
        result.goals = goals;

        return result;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Topic)) {
            return false;
        }
        final Topic otherTopic = (Topic) other;

        return this.topicId.equals(otherTopic.topicId);
    }

    @Override
    public int hashCode() {
        int result = 11;
        result = 31 * result + title.hashCode();

        return result;
    }

    public TopicId getId() {
        return topicId;
    }

    public TopicJourney getJourney() {
        return journey;
    }

    public void addPillToJourney(PillId pillId) {
        journey.addPill(pillId);
    }

    public void addPillsToJourney(List<PillId> pillIds) {
        journey.addPills(pillIds);
    }

    public List<PillId> getPillsInJourney() {
        return new ArrayList<>(journey.getPills());
    }

    public Journey prepareJourneyFor(UserId userId) {
        return Journey.fromSubscription(topicId, userId);
    }
}
