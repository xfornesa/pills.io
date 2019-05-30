package com.prunatic.pills.domain.topics;

import java.util.List;
import java.util.Optional;

/**
 */
public interface TopicsCollection {
    void add(Topic topic);

    List<Topic> findAll();

    Optional<Topic> findById(TopicId topicId);
}
