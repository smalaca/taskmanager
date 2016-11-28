package org.smalaca.taskmanager.domain;

import java.util.ArrayList;
import java.util.List;

import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;

public class Task implements ToDoItem {
    private Status status = TO_BE_DEFINED;
    private String id;
    private String name;
    private Definition definition = new Definition("");
    private Project project;
    private List<Watcher> watchers = new ArrayList<>();
    private Owner owner;
    private Assignee assignee;

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

    @Override
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public List<Watcher> getWatchers() {
        return watchers;
    }

    public void add(Watcher watcher) {
        watchers.add(watcher);
    }

    @Override
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean isAssigned() {
        return assignee != null;
    }

    public void assignTo(Assignee assignee) {
        this.assignee = assignee;
    }

    @Override
    public Assignee getAssignee() {
        return assignee;
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
