package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

public interface CommunicationEventTrigger {
    boolean isApplicableFor(ToDoItem toDoItem);

    void trigger(ToDoItem toDoItem);
}
