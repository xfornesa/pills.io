package com.prunatic.pills.domain.pills;

import com.prunatic.pills.domain.entity.EntityId;

import java.util.UUID;

/**
 */
public class PillId implements EntityId {

    private UUID id;

    private PillId() {}

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PillId)) {
            return false;
        }
        final PillId otherPillId = (PillId) other;

        return this.id.equals(otherPillId.id);
    }

    @Override
    public int hashCode() {
        int result = 11;
        result = 31 * result + id.hashCode();

        return result;
    }

    static public PillId generate() {
        PillId result = new PillId();
        result.id = UUID.randomUUID();

        return result;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
