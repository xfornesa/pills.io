package com.prunatic.pills.domain.pills;

import com.prunatic.pills.domain.entity.EntityId;

/**
 */
public class PillId implements EntityId {

    private String id;

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
}
