package com.prunatic.pills.domain.users;

import java.util.List;
import java.util.Optional;

public interface UsersCollection {
    void add(User user);

    List<User> findAll();

    Optional<User> findById(UserId userId);
}
