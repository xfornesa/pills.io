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
    public void shouldBeEmptyByDefault() throws Exception {
        boolean isEmpty = sut.findAll().isEmpty();

        Assert.assertTrue("Should be empty by default", isEmpty);
    }
}
