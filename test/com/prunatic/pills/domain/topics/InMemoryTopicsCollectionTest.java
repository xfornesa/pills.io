package com.prunatic.pills.domain.topics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 */
public class InMemoryTopicsCollectionTest {

    private InMemoryTopicsCollection sut;

    @Before
    public void setUp() throws Exception {
        sut = createAnEmptyCollection();
    }

    @Test
    public void shouldBeEmptyByDefault() {
        boolean isEmpty = sut.findAll().isEmpty();

        Assert.assertTrue("Should be empty by default", isEmpty);
    }

    @Test
    public void shouldHaveOneElementWhenTopicIsAdded() {
        Topic topic = givenSomeTopic();

        sut.add(topic);

        long count = sut.findAll().parallelStream().count();
        Assert.assertEquals("Should have one element when adding one topic", 1, count);
    }

    @Test
    public void shouldContainTopicAdded() {
        Topic topic = givenSomeTopic();

        sut.add(topic);

        final boolean found = sut.findAll().parallelStream()
                .anyMatch(actual -> actual.equals(topic));

        Assert.assertTrue("Should found the topic recently added", found);
    }

    private InMemoryTopicsCollection createAnEmptyCollection() {
        return new InMemoryTopicsCollection();
    }

    private Topic givenSomeTopic() {
        return Topic.fromContent("anId", "aTitle", "some goals");
    }
}
