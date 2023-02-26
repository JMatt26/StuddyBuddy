Feature: Displaying active and upcoming study sessions

As a student, I want to see the active and upcoming study sessions so that I can join them if I choose to

Scenario: Opening the main page of the application (main flow)

Given I am a student 
When I open the main page of the application
Then I see the active and upcoming study sessions

Scenario: Searching for study sessions (alternative flow)

Given I am a student 
When I search for study sessions
Then I see the active and upcoming study sessions that match my search

Scenario: Looking for a session that has already ended (error flow)

Given I am a student
When I look for an active session
And it has already ended
Then I will not be able to find it on the main page