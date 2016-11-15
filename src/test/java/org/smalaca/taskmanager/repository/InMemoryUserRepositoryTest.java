package org.smalaca.taskmanager.repository;

import org.junit.Test;
import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.exception.UserAlreadyExistsExcetion;
import org.smalaca.taskmanager.exception.UserNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class InMemoryUserRepositoryTest {
    private static final String SOME_EXISTING_USER_ID = "1";
    private static final String SOME_NON_EXISTING_USER_ID = "101";
    private static final User NOT_EXISITING_USER = aUser(SOME_NON_EXISTING_USER_ID, "Mary Jane", "Watson", "mjwatson");
    private static final String FIRST_NAME = "Sebastian";
    private static final String LAST_NAME = "Malaca";
    private static final User SEBASTIAN_MALACA = aUser(SOME_EXISTING_USER_ID, FIRST_NAME, LAST_NAME, "smalaca");

    private UserRepository repository = new InMemoryUserRepository();

    @Test
    public void shouldReturnRetrievedUser() throws UserNotFoundException {
        User user = repository.findById(SOME_EXISTING_USER_ID);

        assertThat(user).isEqualToComparingFieldByField(SEBASTIAN_MALACA);
    }

    @Test
    public void shouldThrowExceptionWhenRetrievedUserDoesNotExist() {
        try {
            repository.findById(SOME_NON_EXISTING_USER_ID);
            fail("User with id: " + SOME_NON_EXISTING_USER_ID + " should be recognized as not existing.");
        } catch (UserNotFoundException exception) {
            assertThat(exception.getMessage()).isEqualTo("User with id: " + SOME_NON_EXISTING_USER_ID + " does not exist.");
        }
    }

    @Test
    public void shouldReturnUserSearchedByNameWhenExists() throws UserNotFoundException {
        User user = repository.findByName(FIRST_NAME, LAST_NAME);

        assertThat(user).isEqualToComparingFieldByField(SEBASTIAN_MALACA);
    }

    @Test
    public void shouldThrowExceptionWhenSearchedByNameUserDoesNotExist() {
        try {
            repository.findByName("Wilson", "Fisk");
            fail("User Wilson Fisk should be recognized as not existing.");
        } catch (UserNotFoundException exception) {
            assertThat(exception.getMessage()).isEqualTo("User Wilson Fisk does not exists.");
        }
    }

    @Test
    public void shouldReturnAllExistingUsers() {
        List<User> users = repository.findAll();

        assertThat(users.size()).isEqualTo(5);
        assertThat(users.get(0)).isEqualToComparingFieldByField(SEBASTIAN_MALACA);
        assertThat(users.get(1)).isEqualToComparingFieldByField(aUser("2", "Peter", "Parker", "Spider Man"));
        assertThat(users.get(2)).isEqualToComparingFieldByField(aUser("3", "Clark", "Kent", "Super Man"));
        assertThat(users.get(3)).isEqualToComparingFieldByField(aUser("4", "Bruce", "Wayne", "Batman"));
        assertThat(users.get(4)).isEqualToComparingFieldByField(aUser("5", "Anthony", "Stark", "Iron Man"));
    }

    @Test
    public void shouldDeleteExistingUser() throws UserNotFoundException {
        User user = repository.findById(SOME_EXISTING_USER_ID);

        repository.remove(user);

        assertThatUserWasRemoved(SOME_EXISTING_USER_ID);
    }

    private void assertThatUserWasRemoved(String id) {
        try {
            repository.findById(id);
            fail("User with id: " + id + " should be removed.");
        } catch (UserNotFoundException exception) {
            assertThat(exception.getMessage()).isEqualTo("User with id: " + id + " does not exist.");
        }
    }

    @Test
    public void shouldThrowExceptionWhenDeletedUserDoesNotExist() {
        try {
            repository.remove(NOT_EXISITING_USER);
            fail("User with id: " + SOME_NON_EXISTING_USER_ID + " does not exist and cannot be removed.");
        } catch (UserNotFoundException exception) {
            assertThat(exception.getMessage()).isEqualTo("User with id: " + SOME_NON_EXISTING_USER_ID + " does not exist and cannot be removed.");
        }
    }

    @Test
    public void shouldAddNewUser() throws UserNotFoundException {
        repository.add(NOT_EXISITING_USER);

        User user = repository.findById(SOME_NON_EXISTING_USER_ID);

        assertThat(user).isEqualToComparingFieldByField(NOT_EXISITING_USER);
    }

    @Test
    public void shouldThrowExceptionWhenUserWithTheSameIdAlreadyExists() {
        try {
            repository.add(SEBASTIAN_MALACA);
            fail("User with id: " + SOME_EXISTING_USER_ID + " already exists and cannot be added once again.");
        } catch (UserAlreadyExistsExcetion exception) {
            assertThat(exception.getMessage()).isEqualTo("User with given id already exists.");
        }
    }

    private static User aUser(String identifier, String firstName, String lastName, String login) {
        User user = new User();
        user.setId(identifier);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword("somethingExtremelyConfidential");

        return user;
    }
}