Feature: Cancelling a study session

As a student, I want to be able to cancel a study session I created so that I can let all participants know that the session will not take place

Scenario: Cancel a study session (main flow)

Given I am a student
When I create a study session
Then I can cancel the study session at any point in time

Scenario: Cancel a study session (alternate flow)

Given I am a student
When I press on the list of my attending sessions
And I press on the session of which I am the admin
Then I have the option to cancel the session

Scenario: Cancelling a session of which I am a member (error flow)

Given I am a student
When I press on a session of which I am just a joined member
Then I do not have the option to cancel the session