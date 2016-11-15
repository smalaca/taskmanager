package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.exception.UserAlreadyExistsExcetion;
import org.smalaca.taskmanager.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InMemoryUserRepository implements UserRepository {
    private static final String DUMMY_PASSWORD = "somethingExtremelyConfidential";

    private final Map<String, User> users = new HashMap<>();

    public InMemoryUserRepository() {
        users.put("1", aUser("1", "Sebastian", "Malaca", "smalaca"));
        users.put("2", aUser("2", "Peter", "Parker", "Spider Man"));
        users.put("3", aUser("3", "Clark", "Kent", "Super Man"));
        users.put("4", aUser("4", "Bruce", "Wayne", "Batman"));
        users.put("5", aUser("5", "Anthony", "Stark", "Iron Man"));
    }

    private User aUser(String id, String firstName, String lastName, String login) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(DUMMY_PASSWORD);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }


    @Override
    public User findById(String id) {
        if (users.containsKey(id)) {
            return users.get(id);
        } else {
            throw new UserNotFoundException("User with id: " + id + " does not exist.");
        }
    }

    @Override
    public User findByName(String firstName, String lastName) {
        for (User user : findAll()) {
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
                return user;
            }
        }

        throw new UserNotFoundException("User " + firstName + " " + lastName  + " does not exists.");
    }

    @Override
    public void remove(User user) {
        if (users.containsValue(user)) {
            users.remove(user.getId());
        } else {
            throw new UserNotFoundException("User with id: " + user.getId() + " does not exist and cannot be removed.");
        }
    }

    @Override
    public void add(User user) {
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        } else {
            throw new UserAlreadyExistsExcetion("User with given id already exists.");
        }
    }
}
