package com.prunatic.pills.domain.topics;

import com.prunatic.pills.domain.pills.PillId;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class TopicJourney {
    List<PillId> pills;

    public TopicJourney() {
        pills = new ArrayList<>();
    }

    public boolean isEmpty() {
        return pills.isEmpty();
    }

    public void addPill(PillId pillId) {
        pills.add(pillId);
    }

    public void addPills(List<PillId> pillIds) {
        pills.addAll(pillIds);
    }

    public List<PillId> getPills() {
        return new ArrayList<>(pills);
    }
}
