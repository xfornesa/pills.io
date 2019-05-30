package com.prunatic.pills.domain.topics;

import com.prunatic.pills.domain.common.EntityId;

import java.util.UUID;

public class JourneyId implements EntityId {

    private String id;

    private JourneyId() {}

    static public JourneyId generate() {
        JourneyId result = new JourneyId();
        result.id = UUID.randomUUID().toString();

        return result;
    }

    public static JourneyId fromString(String id) {
        JourneyId result = new JourneyId();
        result.id = id;

        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof JourneyId)) {
            return false;
        }
        final JourneyId otherJourneyId = (JourneyId) other;

        return this.id.equals(otherJourneyId.id);
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
