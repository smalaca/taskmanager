package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.dto.UserDto;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findById(String id);

    User findByName(String firstName, String lastName);

    void removeById(String id);

    void add(User user);

    void update(User user);

    boolean exists(UserDto userDto);
}
