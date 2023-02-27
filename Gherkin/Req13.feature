Feature: Displaying sessions by their category 

As a student, I want to be able to see sessions by their category so that I can easily find the sessions I am interested in.

Scenario: Displaying sessions by their category (main flow)

Given I am a student
When I am on the home page 
And I press on one fo the displayed categories in the "category" section
Then I should see all the sessions that are in that category

Scenario: Displaying sessions by their category (alternative flow)

Given I am a student
When I look up a category in the search bar
Then I should see all the sessions that are in that category

Scenario: Displaying sessions by a non-existing category (error flow)

Given I am a student
When I look up a category in the search bar
And the category does not exist
Then I should receive an error message that the category does not exist