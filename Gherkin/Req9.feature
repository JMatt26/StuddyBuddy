Feature: Leaving a study session

As a student, I want to be able to leave a study session so that I will no longer be registered as an attendant

Scenario: Leaving a study session (main flow)

Given I am a student 
When I join a study session
Then I have the ability to leave it at any point in time

Scenario: Leaving a study session (alternative flow)

Given I am a student
When I am browing through the list of my registered sessions
And I press on one for which I am just a member
Then I have the ability to leave it 

Scenario: Leaving a study session which I have not joined (error flow)

Given I am a student
When I press on a session that I have not joined
Then I will not have the ability to leave it