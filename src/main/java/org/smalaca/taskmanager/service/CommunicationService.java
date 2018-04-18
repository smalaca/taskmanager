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
import org.smalaca.taskmanager.service.chat.Chat;
import org.smalaca.taskmanager.service.chat.ChatRoom;
import org.smalaca.taskmanager.service.devnull.Directory;
import org.smalaca.taskmanager.service.mail.Mail;
import org.smalaca.taskmanager.service.mail.MailClient;
import org.smalaca.taskmanager.service.sms.SmsCommunicator;
import org.smalaca.taskmanager.session.SessionHolder;

public class CommunicationService {
    private CommunicatorType type;
    private final ProjectBacklogService projectBacklogService;
    private final Directory devNullDirectory;
    private final Chat chat;
    private final SmsCommunicator smsCommunicator;
    private final MailClient mailClient;

    public CommunicationService(
            CommunicatorType type, ProjectBacklogService projectBacklogService, Directory devNullDirectory,
            Chat chat, SmsCommunicator smsCommunicator, MailClient mailClient) {
        this.type = type;
        this.projectBacklogService = projectBacklogService;
        this.devNullDirectory = devNullDirectory;
        this.chat = chat;
        this.smsCommunicator = smsCommunicator;
        this.mailClient = mailClient;
    }

    public void setType(CommunicatorType type) {
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
        devNullDirectory.forget();
    }

    private void notifyAbout(ToDoItem toDoItem, String userName) {
        ChatRoom chatRoom = chat.connectWith(userName);
        chatRoom.send(projectBacklogService.linkFor(toDoItem.getId()));
    }

    private void notifyAbout(ToDoItem toDoItem, PhoneNumber phoneNumber) {
        smsCommunicator.textTo(phoneNumber, projectBacklogService.linkFor(toDoItem.getId()));
    }

    private void notifyAbout(ToDoItem toDoItem, EmailAddress emailAddress) {
        User loggedUser = SessionHolder.intance().logged();
        Mail mail = new Mail.Builder()
                .from(loggedUser.getEmailAddress())
                .to(emailAddress)
                .topic("NOTIFICATION ABOUT" + toDoItem.getId())
                .content(toDoItem.getId())
                .build();

        mailClient.send(mail);
    }
}
