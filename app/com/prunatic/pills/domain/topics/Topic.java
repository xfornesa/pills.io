package com.prunatic.pills.domain.topics;

/**
 */
public class Topic {
    private TopicId pillId;
    private String title;
    private String goals;

    private Topic() {}

    public static Topic fromContent(String id, String title, String goals) {
        final Topic result = new Topic();

        result.pillId = TopicId.fromString(id);
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

        return this.pillId.equals(otherTopic.pillId);
    }

    @Override
    public int hashCode() {
        int result = 11;
        result = 31 * result + title.hashCode();

        return result;
    }

    public TopicId getId() {
        return pillId;
    }
}
