package org.smalaca.taskmanager.exception;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class UserNotFoundExceptionTest {
    private static final String SOME_MESSAGE = "May the force be with you.";

    @Test
    public void shouldCreateExceptionWithAppropriateMessage() {
        UserNotFoundException excetion = new UserNotFoundException(SOME_MESSAGE);

        assertThat(excetion.getMessage()).isEqualTo(SOME_MESSAGE);
    }

}