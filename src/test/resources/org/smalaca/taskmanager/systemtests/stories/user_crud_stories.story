Narrative:
We need to have a posibility to manipulate existing users, adding new users and get list of all existing users.

Scenario: User crud operations verification
When retrieves all users
Then the list of the users contains 5 user

Given a new user data
When adds a new user
And retrieves all users
Then the list of the users contains 6 user
When retrieves information about the user
Then the newly created user exists

Given a new data for newly created user
When updates a user
And retrieves information about the user
Then user's data are changed

When removes a user
And retrieves all users
Then the list of the users contains 5 user
And the removed user is not found