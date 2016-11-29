package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.domain.Watcher;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.smalaca.taskmanager.domain.Status.APPROVED;

public class ToDoItemApprovedEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemApprovedEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return APPROVED.equals(toDoItem.getStatus());
    }

    @Override
    public void trigger(ToDoItem toDoItem) {
        communicationService.notify(toDoItem, toDoItem.getProject().getProductOwner());
        communicationService.notify(toDoItem, toDoItem.getAssignee().getTeam());

        for (Watcher watcher : toDoItem.getWatchers()) {
            communicationService.notify(toDoItem, watcher);
        }

        communicationService.notify(toDoItem, toDoItem.getOwner());
    }
}
