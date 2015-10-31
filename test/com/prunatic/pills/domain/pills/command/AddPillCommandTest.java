package com.prunatic.pills.domain.pills.command;

import com.google.common.eventbus.EventBus;
import com.prunatic.pills.domain.pills.Pill;
import com.prunatic.pills.domain.pills.PillsCollection;
import com.prunatic.pills.domain.pills.event.PillAddedEvent;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 */
public class AddPillCommandTest {

    private AddPillCommand sut;
    private PillsCollection pillsCollection;
    private EventBus eventBus;

    @Before
    public void setUp() throws Exception {
        eventBus = mock(EventBus.class);
        pillsCollection = mock(PillsCollection.class);
        sut = new AddPillCommand(eventBus, pillsCollection);
    }

    @Test
    public void shouldAddPillToCollection() {
        addSomePill();

        verify(pillsCollection).add(any(Pill.class));
    }

    @Test
    public void shouldRaisePillAddedEvent() {
        addSomePill();

        verify(eventBus).post(any(PillAddedEvent.class));
    }

    private void addSomePill() {
        sut.execute("anId", "aTitle", "someContent", "aSurveyId");
    }
}
