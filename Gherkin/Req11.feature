Feature: Marking sessions as full capacity 

As a student, I want to be able to see when a session is full capacity so that I can choose another session

Scenario: Marking a session as full capacity (main flow)

Given I am a student,
When I click on a session
And the session is at full capacity
Then I should see that the number of attendants is equal to the maximum number of attendants
And a message should be displayed saying that the session is full capacity

Scenario: Marking a session as full capacity (alternative flow)

Given I am a student
When I am scrolling through sessionson the home page
Then I should see the full sessions marked as full capacity

Scenario: Marking a session with free space as full capacity (error flow)

Given I am the study app software application
When add a new participant to a session
And there is more space for participants in the sessions
Then the session should not be marked as full capacity