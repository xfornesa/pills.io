Feature: User progress for a topic journey

  Background:
    Given it exists a user

  Scenario: A user journey is completed when all the pills have been completed
    Given a topic with one pill on its journey
     When the user completes the survey for that pill
     Then his journey progress should be completed

  Scenario: Journey half complete
    Given a topic with two pills on its journey
     When the user completes the survey for the first pill
     Then his journey progress should not be completed

