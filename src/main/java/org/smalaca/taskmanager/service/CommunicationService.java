package org.smalaca.taskmanager.service;

import org.smalaca.taskmanager.domain.EmailAddress;
import org.smalaca.taskmanager.domain.Owner;
import org.smalaca.taskmanager.domain.PhoneNumber;
import org.smalaca.taskmanager.domain.ProductOwner;
import org.smalaca.taskmanager.domain.Project;
import org.smalaca.taskmanager.domain.Stakeholder;
import org.smalaca.taskmanager.domain.Team;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.domain.Watcher;

public class CommunicationService {
    private final CommunicatorType type;

    public CommunicationService(CommunicatorType type) {
        this.type = type;
    }

    public void notify(ToDoItem toDoItem, ProductOwner productOwner) {
        switch (type) {
            case MAIL:
                notifyAbout(toDoItem, productOwner.getEmailAddress());
                break;
            case SMS:
                notifyAbout(toDoItem, productOwner.getPhoneNumber());
                break;
            case DIRECT:
                notifyAbout(toDoItem, productOwner.getUserName());
                break;
            case NULL_TYPE:
                notifyAbout();
                break;
        }
    }

    public void notify(ToDoItem toDoItem, Owner owner) {
        switch (type) {
            case SMS:
                notifyAbout(toDoItem, owner.getPhoneNumber());
                break;
            case MAIL:
                notifyAbout(toDoItem, owner.getEmailAddress());
                break;
            case DIRECT:
                notifyAbout(toDoItem, owner.getUserName());
                break;
            case NULL_TYPE:
                notifyAbout();
                break;
        }
    }

    public void notify(ToDoItem toDoItem, Watcher watcher) {
        switch (type) {
            case SMS:
                notifyAbout(toDoItem, watcher.getPhoneNumber());
                break;
            case DIRECT:
                notifyAbout(toDoItem, watcher.getUserName());
                break;
            case MAIL:
                notifyAbout(toDoItem, watcher.getEmailAddress());
                break;
            case NULL_TYPE:
                notifyAbout();
                break;
        }
    }

    public void notify(ToDoItem toDoItem, User user) {
        switch (type) {
            case SMS:
                notifyAbout(toDoItem, user.getPhoneNumber());
                break;
            case DIRECT:
                notifyAbout(toDoItem, user.getLogin());
                break;
            case MAIL:
                notifyAbout(toDoItem, user.getEmailAddress());
                break;
            case NULL_TYPE:
                notifyAbout();
                break;
        }
    }

    public void notify(ToDoItem toDoItem, Stakeholder stakeholder) {
        switch (type) {
            case DIRECT:
                notifyAbout(toDoItem, stakeholder.getUserName());
                break;
            case SMS:
                notifyAbout(toDoItem, stakeholder.getPhoneNumber());
                break;
            case MAIL:
                notifyAbout(toDoItem, stakeholder.getEmailAddress());
                break;
            case NULL_TYPE:
                notifyAbout();
                break;
        }
    }

    public void notify(ToDoItem toDoItem, Team team) {
        for (User user : team.getMembers()) {
            notify(toDoItem, user);
        }
    }

    public void notifyTeamsAbout(ToDoItem toDoItem, Project project) {
        for (Team team : project.getTeams()) {
            notify(toDoItem, team);
        }
    }

    private void notifyAbout() {

    }

    private void notifyAbout(ToDoItem toDoItem, String userName) {

    }

    private void notifyAbout(ToDoItem toDoItem, PhoneNumber phoneNumber) {

    }

    private void notifyAbout(ToDoItem toDoItem, EmailAddress emailAddress) {

    }
}
