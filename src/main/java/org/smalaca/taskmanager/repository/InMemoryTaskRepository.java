package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.exception.TaskNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTaskRepository implements TaskRepository {
    private final Map<String, Task> tasks = new HashMap<>();

    public InMemoryTaskRepository() {
        tasks.put("1", aUser("1", "Coffee break"));
        tasks.put("2", aUser("2", "Meeting"));
        tasks.put("3", aUser("3", "Catch up"));
        tasks.put("4", aUser("4", "Status  summary"));
        tasks.put("5", aUser("5", "Stand up"));
    }

    private Task aUser(String id, String name) {
        Task task = new Task();

        return task;
    }

    @Override
    public boolean exists(String id) {
        return tasks.containsKey(id);
    }

    @Override
    public Task findById(String id) {
        if (exists(id)) {
            return tasks.get(id).copy();
        }

        throw new TaskNotFoundException("Task with id: " + id + " does not exists.");
    }
}
