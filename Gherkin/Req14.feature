Feature: Displaying sessions by their location

As a student, I want to be able to see all the sessions that are happening in a particular location, so that I can decide which sessions to attend.

Scenario: Displaying sessions by their location (main flow)

Given I am a student
When I look up a location in the search bar
Then I will see all the sessions that are happening in that location

Scenario: Displaying sessions by their location (alternate flow)

Given I am a student
When I press on a session
And I then press on the location that this session is being held
Then I will see all of the other sessions that are set to happen in the same location

Scenario: Displaying sessions for a location that has no sessions (error flow)

Given I am a student
When I look up a location in the search bar
And there are no sessions that are happening in that location
Then I will see a message that says “There are no sessions in this location”