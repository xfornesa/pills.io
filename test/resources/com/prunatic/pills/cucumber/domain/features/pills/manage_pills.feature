Feature: Manage pills available within the system


  Scenario: Add pill to system
    Given the following pills:
      | title                   | content             |
      | first pill of knowledge | Content of the pill |
    When I get all the pills
    Then I found the following pills:
      | first pill of knowledge |
