package com.prunatic.pills.domain.pills;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
public class InMemoryPillsCollection implements PillsCollection {
    private ArrayList<Pill> data;

    public InMemoryPillsCollection() {
        this.data = new ArrayList<>();
    }

    @Override
    public void add(Pill pill) {
        this.data.add(pill);
    }

    @Override
    public List<Pill> findAll() {
        return this.data
                .parallelStream()
                .collect(Collectors.toList())
                ;
    }
}
