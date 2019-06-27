package org.smalaca.taskmanager.domain;

import java.util.ArrayList;
import java.util.List;

import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;

public class Story implements ToDoItem {
    private Status status = TO_BE_DEFINED;
    private Project project;
    private Epic epic;
    private List<Watcher> watchers = new ArrayList<>();
    private Owner owner;
    private Assignee assignee;
    private List<Stakeholder> stakeholders = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private String id;
    private String name;

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

    @Override
    public Owner getOwner() {
        return owner;
    }

    @Override
    public boolean isAssigned() {
        return assignee != null;
    }

    @Override
    public Assignee getAssignee() {
        return assignee;
    }

    @Override
    public List<Stakeholder> getStakeholders() {
        return stakeholders;
    }

    public List<Task> getTasks() {
        return tasks;
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

    public void setName(String name) {
        this.name = name;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
}
