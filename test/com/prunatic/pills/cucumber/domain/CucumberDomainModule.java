package com.prunatic.pills.cucumber.domain;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.prunatic.pills.application.pills.command.AddPillCommandHandler;
import com.prunatic.pills.application.topics.command.AddPillToJourneyCommandHandler;
import com.prunatic.pills.application.topics.command.AddTopicCommandHandler;
import com.prunatic.pills.application.topics.command.SubscribeToTopicCommandHandler;
import com.prunatic.pills.domain.pills.InMemoryPillsCollection;
import com.prunatic.pills.domain.pills.PillsCollection;
import com.prunatic.pills.domain.topics.InMemoryTopicsCollection;
import com.prunatic.pills.domain.topics.TopicsCollection;
import com.prunatic.pills.domain.users.InMemoryUsersCollection;
import com.prunatic.pills.domain.users.UsersCollection;

public class CucumberDomainModule extends AbstractModule {
    @Override
    protected void configure() {
        // Common
        bind(EventBus.class).asEagerSingleton();

        // CommandHandlers
        bind(AddPillCommandHandler.class).asEagerSingleton();
        bind(AddPillToJourneyCommandHandler.class).asEagerSingleton();
        bind(AddTopicCommandHandler.class).asEagerSingleton();
        bind(SubscribeToTopicCommandHandler.class).asEagerSingleton();

        // Repositories
        bind(PillsCollection.class).to(InMemoryPillsCollection.class).asEagerSingleton();
        bind(TopicsCollection.class).to(InMemoryTopicsCollection.class).asEagerSingleton();
        bind(UsersCollection.class).to(InMemoryUsersCollection.class).asEagerSingleton();
    }
}
