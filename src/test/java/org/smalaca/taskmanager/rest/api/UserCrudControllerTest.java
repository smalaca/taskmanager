package org.smalaca.taskmanager.rest.api;

import org.junit.Test;
import org.smalaca.taskmanager.dto.UserDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.smalaca.taskmanager.repository.UserRepositories.aInMemoryUserRepository;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

public class UserCrudControllerTest {
    private static final String EXISTING_USER_ID = "1";
    private static final String NOT_EXISTING_USER_ID = "101";
    private static final UserDto NO_USER_DATA = null;
    public static final String SEBASTIAN = "Sebastian";
    public static final String MALACA = "Malaca";

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
        assertThat(user.getFirstName()).isEqualTo(SEBASTIAN);
        assertThat(user.getLastName()).isEqualTo(MALACA);
        assertThat(user.getLogin()).isEqualTo("smalaca");
    }

    @Test
    public void shouldInformAboutConflictWhenCreatedUserAlreadyExists() {
        UriComponentsBuilder uriComponentsBuilder = null;
        UserDto sebastianMalaca = givenUserWithFirstAndLastName(SEBASTIAN, MALACA);

        ResponseEntity<Void> response = controller.createUser(sebastianMalaca, uriComponentsBuilder);

        assertThat(response.getStatusCode()).isEqualTo(CONFLICT);
    }

    @Test
    public void shouldCreateUser() {
        UserDto user = givenUserWithFirstAndLastName("Natasha", "Romanow");
        UriComponentsBuilder uriComponentsBuilder = fromUriString("/");

        ResponseEntity<Void> response = controller.createUser(user, uriComponentsBuilder);

        assertThat(response.getStatusCode()).isEqualTo(CREATED);
        assertThat(response.getHeaders().getLocation().getPath()).matches("/user/[0-9a-z\\-]+");
        assertThatUserWasCreated("Natasha", "Romanow", response.getHeaders());
    }

    private UserDto givenUserWithFirstAndLastName(String firstName, String lastName) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        return userDto;
    }

    private void assertThatUserWasCreated(String firstName, String lastName, HttpHeaders headers) {
        String userId = headers.getLocation().getPath().replace("/user/", "");
        UserDto user = controller.getUser(userId).getBody();

        assertThat(user.getFirstName()).isEqualTo(firstName);
        assertThat(user.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void shouldReturnNotFoundIfUpdatedUserDoesNotExist() {
        ResponseEntity<UserDto> response = controller.updateUser(NOT_EXISTING_USER_ID, NO_USER_DATA);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldUpdateAboutSuccessIfUpdatingExistingUser() {
        String newLogin = "sebastian86";
        String newPassword = "qwerty";
        UserDto user = givenUserWithLoginAndPassword(newLogin, newPassword);

        ResponseEntity<UserDto> response = controller.updateUser(EXISTING_USER_ID, user);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThatUserWasUpdated(newLogin, newPassword, response.getBody());
    }

    private void assertThatUserWasUpdated(String newLogin, String newPassword, UserDto userDto) {
        assertThat(userDto.getId()).isEqualTo(EXISTING_USER_ID);
        assertThat(userDto.getFirstName()).isEqualTo(SEBASTIAN);
        assertThat(userDto.getLastName()).isEqualTo(MALACA);
        assertThat(userDto.getLogin()).isEqualTo(newLogin);
        assertThat(userDto.getPassword()).isEqualTo(newPassword);

        UserDto user = controller.getUser(EXISTING_USER_ID).getBody();

        assertThat(user.getLogin()).isEqualTo(newLogin);
        assertThat(user.getPassword()).isEqualTo(newPassword);
    }

    private UserDto givenUserWithLoginAndPassword(String login, String password) {
        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setPassword(password);
        return userDto;
    }

    @Test
    public void shouldReturnNotFoundIfDeletedUserDoesNotExist() {
        ResponseEntity<Void> response = controller.deleteUser(NOT_EXISTING_USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldDeleteExistingUser() {
        ResponseEntity<Void> response = controller.deleteUser(EXISTING_USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(OK);
    }
}
