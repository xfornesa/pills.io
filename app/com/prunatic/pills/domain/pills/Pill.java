package com.prunatic.pills.domain.pills;

/**
 */
public class Pill {
    private String title;
    private String content;

    private Pill() {}

    public static Pill fromContent(String title, String content) {
        final Pill result = new Pill();
        result.title = title;
        result.content = content;
        return result;
    }

    public String getTitle() {
        return title;
    }
}
