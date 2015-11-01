package com.prunatic.pills.application.pills.command;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.prunatic.pills.domain.pills.Pill;
import com.prunatic.pills.domain.pills.PillId;
import com.prunatic.pills.domain.pills.PillsCollection;
import com.prunatic.pills.domain.pills.command.AddPillCommand;
import com.prunatic.pills.domain.pills.event.PillAddedEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 */
public class AddPillCommandHandlerTest {

    private AddPillCommandHandler sut;
    private PillsCollection pillsCollection;
    private EventBus eventBus;
    private PillId pillIdAdded;

    @Before
    public void setUp() throws Exception {
        eventBus = spy(new EventBus());
        pillsCollection = mock(PillsCollection.class);
        sut = new AddPillCommandHandler(eventBus, pillsCollection);
    }

    @Test
    public void shouldAddPillToCollection() {
        addSomePill();

        verify(pillsCollection).add(any(Pill.class));
    }

    @Test
    public void shouldRaisePillAddedEvent() {
        addSomePill();

        verify(eventBus, atLeastOnce()).post(any(PillAddedEvent.class));
    }

    @Test
    public void pillAddedEventRaisedShouldContainPillIdFromPillAdded() {
        final PillAddedEventHandler eventHandler = new PillAddedEventHandler();
        eventBus.register(eventHandler);

        addSomePill();

        Assert.assertTrue(pillIdAdded.equals(eventHandler.pillId));
    }

    private void addSomePill() {
        pillIdAdded = PillId.fromString("anId");
        sut.handle(new AddPillCommand(pillIdAdded.toString(), "aTitle", "someContent", "aSurveyId"));
    }

    private class PillAddedEventHandler {
        public PillId pillId;

        @Subscribe
        public void handle(PillAddedEvent event) {
            pillId = event.getPillId();
        }
    }
}
