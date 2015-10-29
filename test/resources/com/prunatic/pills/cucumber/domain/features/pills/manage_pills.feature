Feature: Manage pills available within the system

  Scenario: Add pill to system
    Given the following pills collection:
      | id   | title                   | content             |
      | anId | first pill of knowledge | Content of the pill |
    When I get all the pills
    Then I found the following pills:
      | anId |
