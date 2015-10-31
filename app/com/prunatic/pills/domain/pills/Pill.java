package com.prunatic.pills.domain.pills;

import com.prunatic.pills.domain.surveys.SurveyId;

/**
 */
public class Pill {
    private PillId pillId;
    private String title;
    private String content;
    private SurveyId survey;

    private Pill() {}

    public static Pill fromContent(String id, String title, String content, String surveyId) {
        final Pill result = new Pill();

        result.pillId = PillId.fromString(id);
        result.title = title;
        result.content = content;
        result.survey = SurveyId.fromString(surveyId);

        return result;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pill)) {
            return false;
        }
        final Pill otherPill = (Pill) other;

        return this.pillId.equals(otherPill.pillId);
    }

    @Override
    public int hashCode() {
        int result = 11;
        result = 31 * result + title.hashCode();

        return result;
    }

    public PillId getId() {
        return pillId;
    }
}
