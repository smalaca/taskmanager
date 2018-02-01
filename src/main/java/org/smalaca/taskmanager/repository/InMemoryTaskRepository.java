package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.exception.TaskNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTaskRepository implements TaskRepository {
    private final Map<String, Task> tasks = new HashMap<>();

    public InMemoryTaskRepository() {
        tasks.put("1", aTask("1", "Coffee break"));
        tasks.put("2", aTask("2", "Meeting"));
        tasks.put("3", aTask("3", "Catch up"));
        tasks.put("4", aTask("4", "Status  summary"));
        tasks.put("5", aTask("5", "Stand up"));
    }

    private Task aTask(String id, String name) {
        Task task = new Task();
        task.setId(id);
        task.setName(name);

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

    @Override
    public void update(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.replace(task.getId(), task);
        } else {
            throw new TaskNotFoundException("Task with id: " + task.getId() + " does not exist.");
        }
    }
}
