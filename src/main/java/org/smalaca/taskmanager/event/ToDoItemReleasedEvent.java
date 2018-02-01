package org.smalaca.taskmanager.event;

import org.smalaca.taskmanager.domain.ToDoItem;

public class ToDoItemReleasedEvent {
    private final String toDoItemId;

    private ToDoItemReleasedEvent(String toDoItemId) {
        this.toDoItemId = toDoItemId;
    }

    public static ToDoItemReleasedEvent aToDoItemReleasedEvent(ToDoItem toDoItem) {
        return new ToDoItemReleasedEvent(toDoItem.getId());
    }
}
