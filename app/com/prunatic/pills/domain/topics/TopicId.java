package com.prunatic.pills.domain.topics;

import com.prunatic.pills.domain.common.EntityId;

import java.util.UUID;

/**
 */
public class TopicId implements EntityId {

    private String id;

    private TopicId() {}

    static public TopicId generate() {
        TopicId result = new TopicId();
        result.id = UUID.randomUUID().toString();

        return result;
    }

    public static TopicId fromString(String id) {
        TopicId result = new TopicId();
        result.id = id;

        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TopicId)) {
            return false;
        }
        final TopicId otherTopicId = (TopicId) other;

        return this.id.equals(otherTopicId.id);
    }

    @Override
    public int hashCode() {
        int result = 12;
        result = 31 * result + id.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return id;
    }
}
