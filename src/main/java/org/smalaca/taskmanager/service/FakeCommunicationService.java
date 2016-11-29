package org.smalaca.taskmanager.service;

import org.smalaca.taskmanager.domain.Owner;
import org.smalaca.taskmanager.domain.ProductOwner;
import org.smalaca.taskmanager.domain.Stakeholder;
import org.smalaca.taskmanager.domain.Team;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.domain.User;
import org.smalaca.taskmanager.domain.Watcher;

public class FakeCommunicationService implements CommunicationService {
    @Override
    public void notify(ToDoItem toDoItem, ProductOwner productOwner) {

    }

    @Override
    public void notify(ToDoItem toDoItem, Owner owner) {

    }

    @Override
    public void notify(ToDoItem toDoItem, Watcher watcher) {

    }

    @Override
    public void notify(ToDoItem toDoItem, User user) {

    }

    @Override
    public void notify(ToDoItem toDoItem, Stakeholder stakeholder) {

    }

    @Override
    public void notify(ToDoItem toDoItem, Team team) {

    }
}
