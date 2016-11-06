package org.smalaca.taskmanager.rest.api;

import org.junit.Test;
import org.smalaca.taskmanager.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

public class UserCrudControllerTest {
    private UserCrudController controller = new UserCrudController();

    @Test
    public void shouldReturnNoContentWhenNoUsersFound() {
        ResponseEntity<List<User>> response = controller.getAllUsers();

        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
    }

    @Test
    public void shouldReturnNotFoundIfRetrievedUserDoesNotExist() {
        ResponseEntity<User> response = controller.getUser("13");

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldInformAboutConflictWhenCreatedUserAlreadyExists() {
        User user = null;
        UriComponentsBuilder uriComponentsBuilder = null;

        ResponseEntity<Void> response = controller.createUser(user, uriComponentsBuilder);

        assertThat(response.getStatusCode()).isEqualTo(CONFLICT);
    }

    @Test
    public void shouldReturnNotFoundIfUpdatedUserDoesNotExist() {
        User user = null;

        ResponseEntity<User> response = controller.updateUser("13", user);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldReturnNotFoundIfDeletedUserDoesNotExist() {
        User user = null;

        ResponseEntity<User> response = controller.deleteUser("13");

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }
}
