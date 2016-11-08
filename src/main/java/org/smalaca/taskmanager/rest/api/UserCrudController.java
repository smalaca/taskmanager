package org.smalaca.taskmanager.rest.api;

import org.smalaca.taskmanager.domain.User;
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
    private static final String NOT_FOUND_USER = "13";
    private static final String FOUND_USER = "69";

    @RequestMapping(value = USER_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<>();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = SPECIFIC_USER_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        User user = new User();
        user.setId(FOUND_USER);

        //get single user
        if (NOT_FOUND_USER.equals(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
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
        headers.setLocation(uriComponentsBuilder.path(SPECIFIC_USER_PATH).buildAndExpand(NOT_FOUND_USER).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = SPECIFIC_USER_PATH, method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        User currentUser = new User();
        currentUser.setId(FOUND_USER);

        if (NOT_FOUND_USER.equals(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // update with setters :)
        // store user

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = SPECIFIC_USER_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        if (NOT_FOUND_USER.equals(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
