Feature: Setting up the journey for a topic

  Scenario: A topic's journey is empty by default
   Given a topic
    Then I will see that its journey is empty

   Scenario: A journey can contain some pills
     Given a topic
      When I add a pill to its journey
      Then I will see that its journey is not empty

  Scenario: The pills within a journey are ordered
    Given a topic
     When I add the following pills to its journey:
      | pillId       |
      | firstPillId  |
      | secondPillId |
     Then I will see the following ordered pills in its journey:
      | firstPillId  |
      | secondPillId |


  Scenario: Raise PillAddedToJourney event when adding a pill to a topic journey
    Given I am listening for a PillAddedToJourney
    When I add a pill to its journey
    Then I should receive a PillAddedToJourney with its topicId and pillId
