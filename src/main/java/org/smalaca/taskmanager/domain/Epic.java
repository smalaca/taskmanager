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
    private List<Stakeholder> stakeholders = new ArrayList<>();
    private List<Story> stories = new ArrayList<>();
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

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
