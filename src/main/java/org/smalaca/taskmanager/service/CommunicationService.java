package org.smalaca.taskmanager.service;

import org.smalaca.taskmanager.domain.Owner;
import org.smalaca.taskmanager.domain.ProductOwner;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.domain.Watcher;

public interface CommunicationService {
    void notify(ToDoItem toDoItem, ProductOwner productOwner);

    void notify(ToDoItem toDoItem, Owner owner);

    void notify(ToDoItem toDoItem, Watcher watcher);
}
