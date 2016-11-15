package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findById(String someExistingUserId);

    User findByName(String firstName, String lastName);

    void remove(User user);

    void add(User user);
}
