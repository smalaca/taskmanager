package org.smalaca.taskmanager.domain;

public interface ToDoItemRepository {
    ToDoItem findBy(String itemId);
}
