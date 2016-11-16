package org.smalaca.taskmanager.rest.api;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.StrictAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.dto.UserDto;
import org.smalaca.taskmanager.exception.UserNotFoundException;
import org.smalaca.taskmanager.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class UserCrudControllerTest {
    private static final UserNotFoundException SOME_USER_NOT_FOUND_EXCEPTION = new UserNotFoundException("the truth is out there");
    private static final User DUMMY_USER_1 = mock(User.class);
    private static final User DUMMY_USER_2 = mock(User.class);
    private static final List<User> DUMMY_USERS = ImmutableList.of(DUMMY_USER_1, DUMMY_USER_2);
    private static final String EXISTING_USER_ID = "13";
    private static final String NOT_EXISTING_USER_ID = "69";
    private static final String FIRST_NAME = "Steve";
    private static final String LAST_NAME = "Rogers";
    private static final String LOGIN = "captain america";
    private static final String PASSWORD = "avengers";
    private static final User MOCKED_USER = aMockedUser();
    private static final UserDto MOCKED_USER_DTO = aMockedUserDto();
    private static final URI DUMMY_URI = URI.create("dummy/uri");

    @Mock private UriComponentsBuilder uriComponentsBuilder;
    @Mock private UriComponents uriComponents;

    @Mock private UserRepository repository;
    @InjectMocks private UserCrudController controller;

    @Test
    public void shouldReturnAllUsers() {
        given(repository.findAll()).willReturn(DUMMY_USERS);

        ResponseEntity<List<UserDto>> response = controller.getAllUsers();

        assertThat(response.getStatusCode()).isEqualTo(OK);
        StrictAssertions.assertThat(response.getBody().size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnNotFoundIfRetrievedUserDoesNotExist() {
        given(repository.findById(NOT_EXISTING_USER_ID)).willThrow(SOME_USER_NOT_FOUND_EXCEPTION);

        ResponseEntity<UserDto> response = controller.getUser(NOT_EXISTING_USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldReturnExistingUser() {
        given(repository.findById(EXISTING_USER_ID)).willReturn(MOCKED_USER);

        ResponseEntity<UserDto> response = controller.getUser(EXISTING_USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertUserDto(response.getBody());
    }

    private void assertUserDto(UserDto user) {
        assertThat(user.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(user.getLastName()).isEqualTo(LAST_NAME);
        assertThat(user.getId()).isEqualTo(EXISTING_USER_ID);
        assertThat(user.getLogin()).isEqualTo(LOGIN);
        assertThat(user.getPassword()).isEqualTo(PASSWORD);
    }

    @Test
    public void shouldInformAboutConflictWhenCreatedUserAlreadyExists() {
        given(repository.findByName(FIRST_NAME, LAST_NAME)).willReturn(MOCKED_USER);

        ResponseEntity<Void> response = controller.createUser(MOCKED_USER_DTO, uriComponentsBuilder);

        assertThat(response.getStatusCode()).isEqualTo(CONFLICT);
    }

    @Test
    public void shouldCreateUser() {
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        given(repository.findByName(FIRST_NAME, LAST_NAME)).willThrow(SOME_USER_NOT_FOUND_EXCEPTION);
        given(uriComponentsBuilder.path(anyString())).willReturn(uriComponentsBuilder);
        given(uriComponentsBuilder.buildAndExpand(anyString())).willReturn(uriComponents);
        given(uriComponents.toUri()).willReturn(DUMMY_URI);

        ResponseEntity<Void> response = controller.createUser(MOCKED_USER_DTO, uriComponentsBuilder);

        assertThat(response.getStatusCode()).isEqualTo(CREATED);
        verify(repository).add(argumentCaptor.capture());
        User user = argumentCaptor.getValue();
        assertThat(user.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(user.getLastName()).isEqualTo(LAST_NAME);
        assertThat(user.getLogin()).isEqualTo(LOGIN);
        assertThat(user.getPassword()).isEqualTo(PASSWORD);
    }

    @Test
    public void shouldReturnNotFoundIfUpdatedUserDoesNotExist() {
        given(repository.findById(NOT_EXISTING_USER_ID)).willThrow(SOME_USER_NOT_FOUND_EXCEPTION);

        ResponseEntity<UserDto> response = controller.updateUser(NOT_EXISTING_USER_ID, MOCKED_USER_DTO);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldNotUpdateAnythingWhenNoChangesSend() {
        UserDto userDto = mock(UserDto.class);
        given(userDto.getLogin()).willReturn(null);
        given(userDto.getPassword()).willReturn(null);
        User user = aMockedUser();
        given(repository.findById(EXISTING_USER_ID)).willReturn(user);

        ResponseEntity<UserDto> response = controller.updateUser(EXISTING_USER_ID, userDto);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        then(user).should(never()).setLogin(any());
        then(user).should(never()).setPassword(any());
    }

    @Test
    public void shouldUpdateAboutSuccessIfUpdatingExistingUser() {
        User user = aMockedUser();
        given(repository.findById(EXISTING_USER_ID)).willReturn(user);

        ResponseEntity<UserDto> response = controller.updateUser(EXISTING_USER_ID, MOCKED_USER_DTO);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        then(user).should().setLogin(LOGIN);
        then(user).should().setPassword(PASSWORD);
        then(repository).should().update(user);
    }

    @Test
    public void shouldReturnNotFoundIfDeletedUserDoesNotExist() {
        given(repository.findById(NOT_EXISTING_USER_ID)).willThrow(SOME_USER_NOT_FOUND_EXCEPTION);

        ResponseEntity<Void> response = controller.deleteUser(NOT_EXISTING_USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldDeleteExistingUser() {
        given(repository.findById(EXISTING_USER_ID)).willReturn(MOCKED_USER);

        ResponseEntity<Void> response = controller.deleteUser(EXISTING_USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        then(repository).should().removeById(EXISTING_USER_ID);
    }

    private static User aMockedUser() {
        User user = mock(User.class);
        given(user.getId()).willReturn(EXISTING_USER_ID);
        given(user.getFirstName()).willReturn(FIRST_NAME);
        given(user.getLastName()).willReturn(LAST_NAME);
        given(user.getLogin()).willReturn(LOGIN);
        given(user.getPassword()).willReturn(PASSWORD);
        return user;
    }

    private static UserDto aMockedUserDto() {
        UserDto user = mock(UserDto.class);
        given(user.getFirstName()).willReturn(FIRST_NAME);
        given(user.getLastName()).willReturn(LAST_NAME);
        given(user.getLogin()).willReturn(LOGIN);
        given(user.getPassword()).willReturn(PASSWORD);
        return user;
    }
}
