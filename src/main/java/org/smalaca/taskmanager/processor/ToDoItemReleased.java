package org.smalaca.taskmanager.processor;

import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.event.ToDoItemReleasedEvent;

class ToDoItemReleased {
    private final ToDoItemProcessorFacade toDoItemProcessorFacade;

    ToDoItemReleased(ToDoItemProcessorFacade toDoItemProcessorFacade) {
        this.toDoItemProcessorFacade = toDoItemProcessorFacade;
    }

    void process(ToDoItem toDoItem) {
        ToDoItemReleasedEvent event = ToDoItemReleasedEvent.aToDoItemReleasedEvent(toDoItem);
        toDoItemProcessorFacade.publish(event);
    }
}
