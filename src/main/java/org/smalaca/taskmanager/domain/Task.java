package org.smalaca.taskmanager.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.smalaca.taskmanager.domain.Status.APPROVED;
import static org.smalaca.taskmanager.domain.Status.DEFINED;
import static org.smalaca.taskmanager.domain.Status.DONE;
import static org.smalaca.taskmanager.domain.Status.IN_PROGRESS;
import static org.smalaca.taskmanager.domain.Status.RELEASED;
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

    public Sprint getAssignementSprint() {
        return assignementSprint;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public String getStoryId() {
        return storyId;
    }

    public boolean isSubtask() {
        return storyId != null;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public void changeStatusTo(Status status) {
        Status currentStatus = getStatus();

        if (!RELEASED.equals(currentStatus) && !TO_BE_DEFINED.equals(status)) {
            if ((TO_BE_DEFINED.equals(currentStatus) && DEFINED.equals(status))
                || (DEFINED.equals(currentStatus) && IN_PROGRESS.equals(status))
                || (IN_PROGRESS.equals(currentStatus) && DONE.equals(status))
                || (DONE.equals(currentStatus) && APPROVED.equals(status))
                || (APPROVED.equals(currentStatus) && RELEASED.equals(status))) {
                this.status = status;
            }
        }
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
