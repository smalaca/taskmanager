package org.smalaca.taskmanager.domain;

import java.util.ArrayList;
import java.util.List;

import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;

public class Story implements ToDoItem {
    private Status status = TO_BE_DEFINED;
    private Project project;
    private List<Watcher> watchers = new ArrayList<>();
    private Owner owner;
    private Assignee assignee;
    private List<Stakeholder> stakeholders = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private String id;

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

    @Override
    public List<Stakeholder> getStakeholders() {
        return stakeholders;
    }

    public void add(Stakeholder stakeholder) {
        stakeholders.add(stakeholder);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Story copy() {
        Story copy = new Story();
        copy.status = status;
        copy.project = project;
        copy.watchers = watchers;
        copy.owner = owner;
        copy.assignee = assignee;
        copy.stakeholders = stakeholders;
        copy.tasks = tasks;
        copy.id = id;
        return null;
    }
}
