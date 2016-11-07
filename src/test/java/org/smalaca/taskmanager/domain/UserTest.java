package org.smalaca.taskmanager.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private static final String DUMMY_ID = "13";

    @Test
    public void shouldCreateUserWithGivenId() {
        User user = new User(DUMMY_ID);

        assertThat(user.getId()).isEqualTo(DUMMY_ID);
    }
}