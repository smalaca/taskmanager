package org.smalaca.taskmanager.domain;

import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;

public class Task implements ToDoItem {
    private Status status = TO_BE_DEFINED;
    private String id;
    private String name;
    private Definition definition = new Definition("");

    public Task() {
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDefinition(Definition definition) {
        this.definition = definition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Task copy() {
        Task copy = new Task();
        copy.status = status;
        copy.id = id;
        copy.name = name;
        copy.definition = definition;

        return copy;
    }
}
