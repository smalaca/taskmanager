package org.smalaca.taskmanager.domain;

import java.util.ArrayList;
import java.util.List;

import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;

public class Story implements ToDoItem {
    private Status status = TO_BE_DEFINED;
    private Project project;
    private List<Watcher> watchers = new ArrayList<>();
    private Owner owner;

    @Override
    public Status getStatus() {
        return status;
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
}
