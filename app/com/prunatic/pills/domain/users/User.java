package com.prunatic.pills.domain.users;

import com.prunatic.pills.domain.topics.Journey;
import com.prunatic.pills.domain.topics.JourneyId;
import com.prunatic.pills.domain.topics.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 */
public class User {
    private UserId userId;
    private Email email;
    private List<Journey> journeys;

    private User() {}

    public static User fromSubscription(String email) {
        User result = new User();
        result.userId = UserId.generate();
        result.email = Email.fromString(email);
        result.journeys = new ArrayList<>();
        return result;
    }

    public UserId getId() {
        return userId;
    }

    public Journey subscribesTo(Topic topic) {
        Journey journey = topic.prepareJourneyFor(userId);
        journeys.add(journey);

        return journey;
    }

    @Override
    public String toString() {
        return email.toString();
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Pending to implement");
    }

    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("Pending to implement");
    }

    public Optional<Journey> getJourneyByTopic(Topic topic) {
        return journeys.parallelStream()
                .filter(journey -> topic.getId().equals(journey.getTopicId()))
                .findFirst()
                ;
    }
}
