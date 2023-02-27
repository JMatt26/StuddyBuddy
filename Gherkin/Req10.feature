Feature: Displaying study session by course

As a student, I want to be able to display study sessions by course so that I can see all of the study sessions for a particular course

Scenario: Displaying study sessions by course (main flow)

Given I am a student
When I search for a course code in the search bar
Then I should see all of the study sessions for that course

Scenario: Displaying study sessions by course (alternative flow)

Given I am a student
When I am on the home page
And I press in the "categories" section
And I press on a course code
Then I should see all of the study sessions for that course

Scenario: Displaying study sessions for an invalid course (error flow)

Given I am a student
When I search for a course code in the search bar
And the course code is invalid
Then I should see an error message stating that the course code is invalid