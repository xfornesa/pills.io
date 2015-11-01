package com.prunatic.pills.domain.topics;

import java.util.List;

/**
 */
public interface TopicsCollection {
    void add(Topic topic);

    List<Topic> findAll();
}
