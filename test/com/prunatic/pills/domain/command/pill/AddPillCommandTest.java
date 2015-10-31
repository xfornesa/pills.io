package com.prunatic.pills.domain.command.pill;

import com.prunatic.pills.domain.pills.Pill;
import com.prunatic.pills.domain.pills.PillsCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 */
public class AddPillCommandTest {

    private AddPillCommand sut;
    private PillsCollection pillsCollection;

    @Before
    public void setUp() throws Exception {
        pillsCollection = mock(PillsCollection.class);
        sut = new AddPillCommand(pillsCollection);
    }

    @Test
    public void shouldAddPillToCollection() {
        addSomePill();

        verify(pillsCollection).add(any(Pill.class));
    }

    @Test
    public void shouldRaisePillAddedEvent() {
        addSomePill();

        fail("TODO add some event bus");
    }

    private void addSomePill() {
        sut.execute("anId", "aTitle", "someContent", "aSurveyId");
    }
}
