Feature: Manage topics within the system

  Scenario: Add topic to system
    Given the following topics collection:
      | id   | title      | goals                                                                |
      | anId | some title | Description of goals to achieve completing the journey of this topic |
    When I get all the topics
    Then I found the following topics:
      | anId |

  Scenario: Raise TopicAdded event when adding a topic to system
    Given I am listening for a TopicAddedEvent
    When I add a topic
    Then I should receive a TopicAddedEvent with its topicId
