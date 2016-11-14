package org.smalaca.taskmanager.repository;

import org.junit.Test;
import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.exception.UserNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class InMemoryUserRepositoryTest {
    private static final String SOME_EXISTING_USER_ID = "1";
    private static final String SOME_NON_EXISTING_USER_ID = "101";

    private UserRepository repository = new InMemoryUserRepository();

    @Test
    public void shouldReturnRetrievedUser() throws UserNotFoundException {
        User user = repository.findById(SOME_EXISTING_USER_ID);

        assertThat(user).isEqualToComparingFieldByField(aUser(SOME_EXISTING_USER_ID, "Sebastian", "Malaca", "smalaca"));
    }

    @Test
    public void shouldThrowExceptionWhenRetrievedUserDoesNotExist() {
        try {
            repository.findById(SOME_NON_EXISTING_USER_ID);
            fail("User with id: " + SOME_NON_EXISTING_USER_ID + " should be recognized as not existing");
        } catch (UserNotFoundException exception) {
            assertThat(exception.getMessage()).isEqualTo("User with id: " + SOME_NON_EXISTING_USER_ID + " does not exist.");
        }
    }

    @Test
    public void shouldReturnAllExistingUsers() {
        List<User> users = repository.findAll();

        assertThat(users.size()).isEqualTo(5);
        assertThat(users.get(0)).isEqualToComparingFieldByField(aUser("1", "Sebastian", "Malaca", "smalaca"));
        assertThat(users.get(1)).isEqualToComparingFieldByField(aUser("2", "Peter", "Parker", "Spider Man"));
        assertThat(users.get(2)).isEqualToComparingFieldByField(aUser("3", "Clark", "Kent", "Super Man"));
        assertThat(users.get(3)).isEqualToComparingFieldByField(aUser("4", "Bruce", "Wayne", "Batman"));
        assertThat(users.get(4)).isEqualToComparingFieldByField(aUser("5", "Anthony", "Stark", "Iron Man"));
    }

    private User aUser(String identifier, String firstName, String lastName, String login) {
        User user = new User();
        user.setId(identifier);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword("somethingExtremelyConfidential");

        return user;
    }
}