package org.smalaca.taskmanager.repository;

import org.junit.Test;
import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.exception.UserNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class InMemoryUserRepositoryTest {
    private static final String SOME_EXISTING_USER_ID = "1";
    private static final String SOME_NON_EXISTING_USER_ID = "101";

    private UserRepository repository = new InMemoryUserRepository();

    @Test
    public void shouldReturnRetrievedUser() throws UserNotFoundException {
        User user = repository.findById(SOME_EXISTING_USER_ID);

        assertThat(user.getId()).isEqualTo(SOME_EXISTING_USER_ID);
        assertThat(user.getFirstName()).isEqualTo("Sebastian");
        assertThat(user.getLastName()).isEqualTo("Malaca");
        assertThat(user.getLogin()).isEqualTo("smalaca");
        assertThat(user.getPassword()).isEqualTo("somethingExtremelyConfidential");
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
}