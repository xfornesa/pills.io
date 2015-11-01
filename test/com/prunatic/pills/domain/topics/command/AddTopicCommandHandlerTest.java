package com.prunatic.pills.domain.topics.command;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.prunatic.pills.domain.topics.Topic;
import com.prunatic.pills.domain.topics.TopicId;
import com.prunatic.pills.domain.topics.TopicsCollection;
import com.prunatic.pills.domain.topics.event.TopicAddedEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 */
public class AddTopicCommandHandlerTest {

    private AddTopicCommandHandler sut;
    private TopicsCollection topicsCollection;
    private EventBus eventBus;
    private TopicId topicIdAdded;

    @Before
    public void setUp() throws Exception {
        eventBus = spy(new EventBus());
        topicsCollection = mock(TopicsCollection.class);
        sut = new AddTopicCommandHandler(eventBus, topicsCollection);
    }

    @Test
    public void shouldAddTopicToCollection() {
        addSomeTopic();

        verify(topicsCollection).add(any(Topic.class));
    }

    @Test
    public void shouldRaiseTopicAddedEvent() {
        addSomeTopic();

        verify(eventBus, atLeastOnce()).post(any(TopicAddedEvent.class));
    }

    @Test
    public void topicAddedEventRaisedShouldContainTopicIdFromTopicAdded() {
        final TopicAddedEventHandler eventHandler = new TopicAddedEventHandler();
        eventBus.register(eventHandler);

        addSomeTopic();

        Assert.assertTrue(topicIdAdded.equals(eventHandler.topicId));
    }

    private void addSomeTopic() {
        topicIdAdded = TopicId.fromString("anId");
        sut.handle(new AddTopicCommand(topicIdAdded.toString(), "aTitle", "some goals"));
    }

    private class TopicAddedEventHandler {
        public TopicId topicId;

        @Subscribe
        public void handle(TopicAddedEvent event) {
            topicId = event.getTopicId();
        }
    }
}
