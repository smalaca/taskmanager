package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.exception.UserNotFoundException;

public interface UserRepository {
    User findById(String someExistingUserId) throws UserNotFoundException;
}
