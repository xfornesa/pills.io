Feature: Manage pills available within the system


  Scenario: Add pill to system
    Given the following pill:
      | title   | first pill of knowledge |
      | content | Content of the pill     |
    When I ask for all the pills
    Then I will get the following pills:
      | first pill of knowledge |

