Feature: Exclusively viewing friends' study sessions

As a student, I want to be able to exlusively view my friends' study sessions so that I can see what sessions they are hosting/participating in

Scenario: Viewing friends' study sessions (main flow)

Given I am a students
When I look under the "friends' sessions" tab
Then I can see all of my friends' study sessions

Scenario: Viewing friends' study sessions (alternate flow)

Given I am a student
When I look at my friend's profile
Then I can see all of their study sessions

Scenario: Viewing friends' study sessions (error flow)

Given I am a student
When I look at my friend's profile
And they have no registered sessions
Then I am notified that they have no registered sessions