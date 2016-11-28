package org.smalaca.taskmanager.service;

import org.smalaca.taskmanager.domain.Owner;
import org.smalaca.taskmanager.domain.ProductOwner;
import org.smalaca.taskmanager.domain.ToDoItem;
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
}
