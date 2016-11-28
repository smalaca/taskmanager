package org.smalaca.taskmanager.domain;

import java.util.ArrayList;
import java.util.List;

import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;

public class Epic implements ToDoItem {
    private Status status = TO_BE_DEFINED;
    private Project project;
    private List<Watcher> watchers = new ArrayList<>();
    private Owner owner;
    private Assignee assignee;

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

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
