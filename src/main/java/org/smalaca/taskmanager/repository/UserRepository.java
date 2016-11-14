package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.User;

import java.util.List;

public interface UserRepository {
    User findById(String someExistingUserId);

    List<User> findAll();
}
