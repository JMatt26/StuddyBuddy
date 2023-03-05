Feature: Create an account

As a student, I want to be able to create an account so that I can use the StuddyBuddy application

Scenario: First time using the StuddyBuddy application (main flow)

Given I am a student
When I open the StuddyBuddy application for the first time
Then I should be able to create an account

Scenario: A student has switched schools (alternate flow)

Given I am a student
And I have switched schools
When I open the StuddyBuddy application
Then I should be able to create an account registered to my new school

Scenario: A student creates an account with a previously used email (error flow)

Given I am a student
When I try to create an account with an email that has already been used
Then I should be notified that the email is already in use