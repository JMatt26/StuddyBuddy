Feature: Displaying all session attendants

As a student, I want to see all of the students who are attending a session, so that I can see who is attending a session.

Scenario: Displaying all session attendants (main flow)

Given I am a student
When I press on a session
Then I should see all of the students who are attending the session

Scenario: Displaying all session attendants (alternate flow)

Given I am a student
When I search for sessions by tags
And I press on one of the sessions
Then I should see all of the students who are attending the session

Scenario: Displaying all attendants for cancelled session (error flow)

Given I am a student
When a session is cancelled
Then I should not be able to see the attendants for that session
