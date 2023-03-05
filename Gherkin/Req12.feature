Feature: Logging in with username and password

As a student, I want to be able to log in with my username and password so that I can access my account

Scenario: Logging in with username and password (main flow)

Given I am a student
And I am on the login page
When I enter my username and password
Then I should be logged into my account

Scenario: Logging in with username and password (alternate flow)

Given I am a student
And I am creating my account
When I enter my username and password
Then I should be logged into my account

Scenario: Logging in with an invalid username or password (error flow)

Given I am a student
When I enter my username and password
And one of the two is invalid
Then I should be notified that my username or password is invalid 
And I am denied access to my account
