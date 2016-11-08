package org.smalaca.taskmanager.rest.api;

import org.junit.Test;
import org.smalaca.taskmanager.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

public class UserCrudControllerTest {
    private static final String NOT_EXISITNIG_USER = "13";
    private static final String EXISITNIG_USER = "69";

    private UserCrudController controller = new UserCrudController();

    @Test
    public void shouldReturnNoContentWhenNoUsersFound() {
        ResponseEntity<List<User>> response = controller.getAllUsers();

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().isEmpty()).isTrue();
    }

    @Test
    public void shouldReturnNotFoundIfRetrievedUserDoesNotExist() {
        ResponseEntity<User> response = controller.getUser(NOT_EXISITNIG_USER);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldReturnExistingUser() {
        ResponseEntity<User> response = controller.getUser(EXISITNIG_USER);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().getId()).isEqualTo(EXISITNIG_USER);
    }

    @Test
    public void shouldInformAboutConflictWhenCreatedUserAlreadyExists() {
        User user = null;
        UriComponentsBuilder uriComponentsBuilder = null;

        ResponseEntity<Void> response = controller.createUser(user, uriComponentsBuilder);

        assertThat(response.getStatusCode()).isEqualTo(CONFLICT);
    }

    @Test
    public void shouldCreateUser() {
        User user = new User();
        UriComponentsBuilder uriComponentsBuilder = fromUriString("/");

        ResponseEntity<Void> response = controller.createUser(user, uriComponentsBuilder);

        assertThat(response.getStatusCode()).isEqualTo(CREATED);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/user/13");
    }

    @Test
    public void shouldReturnNotFoundIfUpdatedUserDoesNotExist() {
        User user = null;

        ResponseEntity<User> response = controller.updateUser("13", user);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldUpdateAboutSuccessIfUpdatingExistingUser() {
        ResponseEntity<User> response = controller.updateUser(EXISITNIG_USER, new User());

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().getId()).isEqualTo(EXISITNIG_USER);
    }

    @Test
    public void shouldReturnNotFoundIfDeletedUserDoesNotExist() {
        User user = null;

        ResponseEntity<User> response = controller.deleteUser("13");

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldDeleteExistingUser() {
        ResponseEntity<User> response = controller.deleteUser(EXISITNIG_USER);

        assertThat(response.getStatusCode()).isEqualTo(OK);
    }
}
