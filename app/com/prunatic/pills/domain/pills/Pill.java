package com.prunatic.pills.domain.pills;

/**
 */
public class Pill {
    private PillId pillId;
    private String title;
    private String content;

    private Pill() {}

    public static Pill fromContent(String id, String title, String content) {
        final Pill result = new Pill();

        result.pillId = PillId.fromString(id);
        result.title = title;
        result.content = content;

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
}
