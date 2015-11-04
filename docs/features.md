# Features

After an small event storming workshop, it has been identified the following topics:
 - authorization
 - users
 - subscriptions
 - scheduler
 - topics/journeys
 - pills/capsules
 - engagement (gamify user progress)
 
## Event storming
 
 Interesting domain events:
 - User has created an account => AccountCreated
 - User has removed his account => AccountRemoved
 - User has subscribed to topic => SubscriptionCreated
 - Some pills have been scheduled => PillsScheduled 
 - Pill has been sent to user => PillSent
 - Pill has been read by user => PillRead
 - Survey has been completed => SurveyCompleted
 - User has passed the survey => SurveyPassed
 - User has failed the survey => SurveyFailed
 - User' progress has been updated with survey results => UserProgressUpdated
 - User has completed the topic journey => UserJourneyCompleted
 - User has cancelled his subscription to a topic => SubscriptionCancelled
 - A topic has been added => ~~TopicAdded~~
 - An existing topic has been removed => TopicRemoved
 - A new pill has been added => ~~PillAdded~~
 - An existing pill has been removed => PillRemoved
 - An existing pill has been added to topic journey => PillAddedToJourney
 - A pill has been detached from a topic journey => PillRemovedFromJourney
 
 commands:
 - CreateAccount
 - DeleteAccount
 - SubscribeToTopic
 - SendPill
 - SchedulePills
 - SendPill
 - ReadPill
 - CompleteSurvey
 - UpdateUserProgress
 - CancelSubscription
 - ~~AddTopic~~
 - RemoveTopic
 - ~~AddPill~~
 - RemovePill
 - ~~AddPillToJourney~~
 - ~~AddPillsToJourney~~
 - RemovePillFromJourney
 
 aggregates:
 - ...
 
 users of the system:
 - User
 - Pills scheduler
 
 external systems:
 - Timer
 
 
## Road map

- ~~[Pills] Add a pill to the system~~
- ~~[Topics] Add a topic to the system~~
- [Topics] Configure journey of a topic
- [Subscriptions] Subscribe to topic
- [Scheduler] Schedule pills to sent
- [Topics] Complete survey

