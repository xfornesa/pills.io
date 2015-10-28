package com.prunatic.pills.domain.pills;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 */
public class InMemoryPillsCollectionTest {

    private InMemoryPillsCollection sut;

    @Before
    public void setUp() throws Exception {
        sut = createAnEmptyCollection();
    }

    private InMemoryPillsCollection createAnEmptyCollection() {
        return new InMemoryPillsCollection();
    }

    @Test
    public void shouldBeEmptyByDefault() {
        boolean isEmpty = sut.findAll().isEmpty();

        Assert.assertTrue("Should be empty by default", isEmpty);
    }

    @Test
    public void shouldHaveOneElementWhenPillIsAdded() {
        Pill pill = Pill.fromContent("aTitle", "someContent");

        sut.add(pill);

        long count = sut.findAll().parallelStream().count();
        Assert.assertEquals(1, count);
    }
}
