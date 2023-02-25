Feature: Create study sessions

As a student, I want to be able to create a session and add all of the session's information so that others can see what I am studying

Scenario: Creating a session (main flow)

Given I am a student
When I press on the "create session" button
Then I can fill in all of the session's information
And the session will be published for others to see and join

Scenario: Creating a private session (alternative flow)

Given I am a student
When I press on the "create session" button
And select to make the session private
Then I can fill in all of the session's information
And the session will only be visible to those I invite

Scenario: Creating a session and not filling in the information (error flow)

Given I am a student
When I press on the "create session" button
And I do not fill in all of the session's information
Then I will be prompted to fill in all of the remaining information