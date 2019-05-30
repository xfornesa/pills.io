package com.prunatic.pills.domain.users;

import com.prunatic.pills.domain.common.EntityId;

import java.util.UUID;

/**
 */
public class UserId implements EntityId {
    private final String id;

    private UserId(String id) {
        this.id = id;
    }

    public static UserId generate() {
        String id = UUID.randomUUID().toString();

        return new UserId(id);
    }
}
