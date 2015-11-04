package com.prunatic.pills.domain.topics;

import com.prunatic.pills.domain.pills.PillId;
import org.junit.Assert;
import org.junit.Test;

/**
 */
public class TopicTest {

    @Test
    public void shouldHaveAnEmptyJourneyByDefault() {
        Topic sut = givenSomeTopic();

        TopicJourney actual = sut.getJourney();

        Assert.assertTrue(actual.isEmpty());
    }

    @Test
    public void shouldNotHaveAnEmptyJourneyWhenAddingAPill() {
        Topic sut = givenSomeTopic();
        PillId pillId = PillId.generate();

        sut.addPillToJourney(pillId);

        TopicJourney actual = sut.getJourney();
        Assert.assertFalse(actual.isEmpty());
    }

    private Topic givenSomeTopic() {
        return Topic.fromContent("anId", "someTitle", "some goals");
    }
}
