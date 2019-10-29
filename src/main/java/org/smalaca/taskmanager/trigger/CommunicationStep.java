package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

public interface CommunicationStep {
    void process(ToDoItem toDoItem);
}
