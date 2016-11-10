package org.smalaca.taskmanager.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    private static final String DUMMY_ID = "13";
    private static final String DUMMY_FIRST_NAME = "Sebastian";
    private static final String DUMMY_LAST_NAME = "Malaca";
    private static final String DUMMY_LOGIN = "smalaca";
    private static final String DUMMY_PASSWORD = "dummyPassword";

    @Test
    public void shouldCreateUserWithGivenData() {
        User user = new User();
        user.setId(DUMMY_ID);
        user.setFirstName(DUMMY_FIRST_NAME);
        user.setLastName(DUMMY_LAST_NAME);
        user.setLogin(DUMMY_LOGIN);
        user.setPassword(DUMMY_PASSWORD);

        assertThat(user.getId()).isEqualTo(DUMMY_ID);
        assertThat(user.getFirstName()).isEqualTo(DUMMY_FIRST_NAME);
        assertThat(user.getLogin()).isEqualTo(DUMMY_LOGIN);
        assertThat(user.getPassword()).isEqualTo(DUMMY_PASSWORD);
    }
}