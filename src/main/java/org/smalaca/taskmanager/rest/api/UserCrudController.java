package org.smalaca.taskmanager.rest.api;

import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.domain.UserBuilder;
import org.smalaca.taskmanager.dto.UserDto;
import org.smalaca.taskmanager.exception.UserNotFoundException;
import org.smalaca.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserCrudController {
    private final UserRepository userRepository;

    @Autowired
    public UserCrudController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> usersDtos = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            UserDto userDto = user.mapUserToDTO();

            usersDtos.add(userDto);
        }

        return new ResponseEntity<>(usersDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto, UriComponentsBuilder uriComponentsBuilder) {
        // data validation

        if(exists(userDto)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {

            User user = new UserBuilder().withName(userDto.getFirstName(), userDto.getLastName())
                    .withCredentials(userDto.getLogin(), userDto.getPassword())
                    .build();

            userRepository.add(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
    }

    private boolean exists(UserDto userDto) {
        return userRepository.exists(userDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        // data validation
        User user;

        try {
            user = userRepository.findById(id);
        } catch (UserNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (userDto.getLogin() != null) {
            user.setLogin(userDto.getLogin());
        }

        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }

        userRepository.update(user);

        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setId(user.getId());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        try {
            userRepository.findById(id);
        } catch (UserNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userRepository.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
