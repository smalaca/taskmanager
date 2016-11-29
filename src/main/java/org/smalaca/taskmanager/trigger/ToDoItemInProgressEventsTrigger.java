package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.smalaca.taskmanager.domain.Status.IN_PROGRESS;

public class ToDoItemInProgressEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemInProgressEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return IN_PROGRESS.equals(toDoItem.getStatus());
    }

    @Override
    public void trigger(ToDoItem toDoItem) {
        communicationService.notify(toDoItem, toDoItem.getAssignee().getTeam());
        communicationService.notify(toDoItem, toDoItem.getOwner());
    }
}
