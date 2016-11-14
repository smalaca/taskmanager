package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.exception.UserNotFoundException;

import java.util.List;

public interface UserRepository {
    User findById(String someExistingUserId) throws UserNotFoundException;

    List<User> findAll();
}
