Feature: Joining study sessions

As a student, I want to be able to join study sessions so that I can study with other students.

Scenario: Joining a study session from the home page (main flow)

Given that I am a student
And I am on the home page
When I select a study session
And I click on the "Join" button'
Then I am registered for that study session

Scenario: Joining a study session from search results (alternate flow)

Given that I am a student
And I search up a specific study session
When I click on the "Join" button
Then I am registered for that study session

Scenario: Joining a study session I am already registered for (error flow)

Given that I am a student
When I select a study session
And I click on the "Join" button
And I am already registered for that study session
Then I am notified that I am already registered for that study session
