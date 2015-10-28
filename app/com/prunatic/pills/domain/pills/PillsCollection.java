package com.prunatic.pills.domain.pills;

import java.util.List;

/**
 */
public interface PillsCollection {
    void add(Pill pill);

    List<Pill> findAll();
}
