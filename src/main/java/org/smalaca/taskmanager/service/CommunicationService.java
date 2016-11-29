package org.smalaca.taskmanager.service;

import org.smalaca.taskmanager.domain.Owner;
import org.smalaca.taskmanager.domain.ProductOwner;
import org.smalaca.taskmanager.domain.Stakeholder;
import org.smalaca.taskmanager.domain.Team;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.domain.Watcher;

public interface CommunicationService {
    void notify(ToDoItem toDoItem, ProductOwner productOwner);

    void notify(ToDoItem toDoItem, Owner owner);

    void notify(ToDoItem toDoItem, Watcher watcher);

    void notify(ToDoItem toDoItem, User user);

    void notify(ToDoItem toDoItem, Stakeholder stakeholder);

    void notify(ToDoItem toDoItem, Team team);
}
