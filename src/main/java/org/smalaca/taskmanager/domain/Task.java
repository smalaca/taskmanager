package org.smalaca.taskmanager.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;

public class Task implements ToDoItem {
    private Status status = TO_BE_DEFINED;
    private String id;
    private String name;
    private Definition definition;
    private Project project;
    private List<Watcher> watchers = new ArrayList<>();
    private Owner owner;
    private Assignee assignee;
    private Sprint assignementSprint;
    private Sprint currentSprint;
    private Date resolutionDate;
    private List<Stakeholder> stakeholders = new ArrayList<>();
    private String storyId;

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
    public String getId() {
        return id;
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

    public void assignTo(Assignee assignee, Sprint sprint) {
        this.assignee = assignee;
        this.assignementSprint = sprint;
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

    public Task copy() {
        Task copy = new Task();
        copy.status = status;
        copy.id = id;
        copy.name = name;
        copy.definition = definition;

        return copy;
    }

    public Sprint getCurrentSprint() {
        return currentSprint;
    }

    public void setCurrentSprint(Sprint currentSprint) {
        this.currentSprint = currentSprint;
    }

    public Sprint getAssignementSprint() {
        return assignementSprint;
    }

    public void resolved(Date date) {
        resolutionDate = date;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public boolean isSubtask() {
        return storyId != null;
    }

    public void start() {
        switch (getStatus()) {
            case TO_BE_DEFINED:
                definition = new Definition("");
                currentSprint = assignementSprint;
                break;

            case DEFINED:
                assignee = Assignee.from(owner);
                break;

            case DONE:
                resolutionDate = new Date();
                assignee = Assignee.from(owner);
                break;

            case APPROVED:
                assignee = Assignee.from(project.getProductOwner());
                break;
        }
    }
}
