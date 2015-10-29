package com.prunatic.pills.domain.surveys;

import com.prunatic.pills.domain.entity.EntityId;

import java.util.UUID;

/**
 */
public class SurveyId implements EntityId {

    private String id;

    private SurveyId() {}

    static public SurveyId generate() {
        SurveyId result = new SurveyId();
        result.id = UUID.randomUUID().toString();

        return result;
    }

    public static SurveyId fromString(String id) {
        SurveyId result = new SurveyId();
        result.id = id;

        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SurveyId)) {
            return false;
        }
        final SurveyId otherSurveyId = (SurveyId) other;

        return this.id.equals(otherSurveyId.id);
    }

    @Override
    public int hashCode() {
        int result = 11;
        result = 31 * result + id.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return id;
    }
}
