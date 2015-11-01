package com.prunatic.pills.domain.topics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
public class InMemoryTopicsCollection implements TopicsCollection {
    private ArrayList<Topic> data;

    public InMemoryTopicsCollection() {
        this.data = new ArrayList<>();
    }

    @Override
    public void add(Topic topic) {
        this.data.add(topic);
    }

    @Override
    public List<Topic> findAll() {
        return this.data
                .parallelStream()
                .collect(Collectors.toList())
                ;
    }
}
