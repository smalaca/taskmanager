package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.Task;

public interface TaskRepository {
    boolean exists(String id);

    Task findById(String id);

    void update(Task task);
}
