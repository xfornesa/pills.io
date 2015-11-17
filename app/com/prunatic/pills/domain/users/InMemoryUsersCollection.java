package com.prunatic.pills.domain.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 */
public class InMemoryUsersCollection implements UsersCollection {
    private ArrayList<User> data;

    public InMemoryUsersCollection() {
        this.data = new ArrayList<>();
    }

    @Override
    public void add(User user) {
        this.data.add(user);
    }

    @Override
    public List<User> findAll() {
        return this.data
                .parallelStream()
                .collect(Collectors.toList())
                ;
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return this.data
                .parallelStream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }
}
