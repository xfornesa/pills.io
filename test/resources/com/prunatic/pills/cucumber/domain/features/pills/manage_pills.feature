Feature: Manage pills available within the system

  Scenario: Add pill to system
    Given the following pills collection:
      | id   | title                   | content             | surveyId  |
      | anId | first pill of knowledge | Content of the pill | aSurveyId |
    When I get all the pills
    Then I found the following pills:
      | anId |

  Scenario: Raise PillAdded event when adding a pill to system
    Given I am listening for a PillAddedEvent
    When I add a pill
    Then I should receive a PillAddedEvent with its pillId
