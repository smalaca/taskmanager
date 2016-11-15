package org.smalaca.taskmanager.rest.api;

import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.dto.UserDto;
import org.smalaca.taskmanager.exception.UserNotFoundException;
import org.smalaca.taskmanager.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserCrudController {
    private static final String USER_PATH = "/user";
    private static final String SPECIFIC_USER_PATH = USER_PATH + "/{id}";
    private static final String USER_ID_1 = "13";
    private static final String USER_ID_2 = "69";

    private final UserRepository userRepository;

    public UserCrudController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = USER_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> usersDtos = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            UserDto userDto = new UserDto();
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setLogin(user.getLogin());
            userDto.setPassword(user.getPassword());
            userDto.setId(user.getId());

            usersDtos.add(userDto);
        }

        return new ResponseEntity<>(usersDtos, HttpStatus.OK);
    }

    @RequestMapping(value = SPECIFIC_USER_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String id) {
        try {
            User user = userRepository.findById(id);
            UserDto userDto = new UserDto();
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setLogin(user.getLogin());
            userDto.setPassword(user.getPassword());
            userDto.setId(user.getId());

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody UserDto user, UriComponentsBuilder uriComponentsBuilder) {
        /**
         * 1. Data validation
         * 2. Checking whether someone like this exist
         * 3. Convert data to user (Builder :)
         */
        if (user == null) {
            // user exitst
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path(SPECIFIC_USER_PATH).buildAndExpand(USER_ID_1).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = SPECIFIC_USER_PATH, method = RequestMethod.PUT)
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") String id, @RequestBody UserDto user) {
        UserDto currentUser = new UserDto();
        currentUser.setId(USER_ID_2);

        if (USER_ID_1.equals(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // update with setters :)
        // store user

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = SPECIFIC_USER_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        if (USER_ID_1.equals(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
