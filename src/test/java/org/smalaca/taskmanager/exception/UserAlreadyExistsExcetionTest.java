package org.smalaca.taskmanager.exception;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAlreadyExistsExcetionTest {
    private static final String SOME_MESSAGE = "May the force be with you.";

    @Test
    public void shouldCreateExceptionWithAppropriateMessage() {
        UserAlreadyExistsExcetion excetion = new UserAlreadyExistsExcetion(SOME_MESSAGE);

        assertThat(excetion.getMessage()).isEqualTo(SOME_MESSAGE);
    }
}
