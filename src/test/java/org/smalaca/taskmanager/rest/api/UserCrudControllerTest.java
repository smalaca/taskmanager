package org.smalaca.taskmanager.rest.api;

import org.junit.Test;
import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.dto.UserDto;
import org.smalaca.taskmanager.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.smalaca.taskmanager.repository.UserRepositories.aInMemoryUserRepository;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

public class UserCrudControllerTest {
    private static final String USER_ID_2 = "13";
    private static final String USER_ID_1 = "69";
    private static final String EXISTING_USER_ID = "1";
    private static final String NOT_EXISTING_USER_ID = "101";
    private static final User DUMMY_USER = new User();
    private static final UserDto NO_USER_DATA = null;

    private UserCrudController controller = new UserCrudController(aInMemoryUserRepository());

    @Test
    public void shouldReturnAllUsers() {
        ResponseEntity<List<UserDto>> response = controller.getAllUsers();

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().size()).isEqualTo(5);
    }

    @Test
    public void shouldReturnNotFoundIfRetrievedUserDoesNotExist() {
        ResponseEntity<UserDto> response = controller.getUser(NOT_EXISTING_USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldReturnExistingUser() {
        ResponseEntity<UserDto> response = controller.getUser(EXISTING_USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertUser(response.getBody());
    }

    private void assertUser(UserDto user) {
        assertThat(user.getId()).isEqualTo(EXISTING_USER_ID);
        assertThat(user.getFirstName()).isEqualTo("Sebastian");
        assertThat(user.getLastName()).isEqualTo("Malaca");
        assertThat(user.getLogin()).isEqualTo("smalaca");
    }

    @Test
    public void shouldInformAboutConflictWhenCreatedUserAlreadyExists() {
        UserDto user = null;
        UriComponentsBuilder uriComponentsBuilder = null;

        ResponseEntity<Void> response = controller.createUser(user, uriComponentsBuilder);

        assertThat(response.getStatusCode()).isEqualTo(CONFLICT);
    }

    @Test
    public void shouldCreateUser() {
        UserDto user = new UserDto();
        UriComponentsBuilder uriComponentsBuilder = fromUriString("/");

        ResponseEntity<Void> response = controller.createUser(user, uriComponentsBuilder);

        assertThat(response.getStatusCode()).isEqualTo(CREATED);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/user/13");
    }

    @Test
    public void shouldReturnNotFoundIfUpdatedUserDoesNotExist() {
        ResponseEntity<UserDto> response = controller.updateUser(USER_ID_2, NO_USER_DATA);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldUpdateAboutSuccessIfUpdatingExistingUser() {
        ResponseEntity<UserDto> response = controller.updateUser(USER_ID_1, new UserDto());

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().getId()).isEqualTo(USER_ID_1);
    }

    @Test
    public void shouldReturnNotFoundIfDeletedUserDoesNotExist() {
        ResponseEntity<Boolean> response = controller.deleteUser(NOT_EXISTING_USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldDeleteExistingUser() {
        ResponseEntity<Boolean> response = controller.deleteUser(EXISTING_USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(TRUE);
    }

    @Test
    public void shouldInformAboutProblemDuringUserDeletetion() {
        UserCrudController controller = new UserCrudController(aGivenUserRepositoryCannotRemoveExistingUser());

        ResponseEntity<Boolean> response = controller.deleteUser(EXISTING_USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(FALSE);
    }

    private UserRepository aGivenUserRepositoryCannotRemoveExistingUser() {
        UserRepository userRepository = mock(UserRepository.class);
        given(userRepository.findById(EXISTING_USER_ID)).willReturn(DUMMY_USER);
        doThrow(new RuntimeException("Bad things happen.")).when(userRepository).remove(DUMMY_USER);
        return userRepository;
    }
}
