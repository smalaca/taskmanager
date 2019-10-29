package org.smalaca.taskmanager.processor;

import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.event.ToDoItemReleasedEvent;

class ToDoItemReleased implements ToDoItemState {
    private final ToDoItemProcessorFacade toDoItemProcessorFacade;

    ToDoItemReleased(ToDoItemProcessorFacade toDoItemProcessorFacade) {
        this.toDoItemProcessorFacade = toDoItemProcessorFacade;
    }

    public void process(ToDoItem toDoItem) {
        ToDoItemReleasedEvent event = ToDoItemReleasedEvent.aToDoItemReleasedEvent(toDoItem);
        toDoItemProcessorFacade.publish(event);
    }
}
