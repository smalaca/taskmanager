package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.ToDoItem;

public interface ToDoItemRepository {
    ToDoItem findBy(String itemId);
}
