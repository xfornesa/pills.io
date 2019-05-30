Feature: User subscribes to topic

  Scenario: A user subscribes to topic
    Given a user and a topic
     When the user subscribes to that topic
     Then it will exists a journey for that user on that topic


  Scenario: Raise UserSubscribedToTopicEvent event when adding a pill to a topic journey
    Given a user and a topic
      And I am listening for a UserSubscribedToTopicEvent
     When the user subscribes to that topic
     Then I should receive a UserSubscribedToTopicEvent with journeyId, userId and topicId
