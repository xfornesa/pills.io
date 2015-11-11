Feature: User subscribes to topic

  Scenario: A user subscribes to topic
    Given a user and a topic
     When the user subscribes to that topic
     Then it will exists a journey for that user on that topic

