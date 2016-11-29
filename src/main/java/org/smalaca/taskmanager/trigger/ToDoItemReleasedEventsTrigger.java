package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Stakeholder;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.domain.Watcher;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.smalaca.taskmanager.domain.Status.RELEASED;

public class ToDoItemReleasedEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemReleasedEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return RELEASED.equals(toDoItem.getStatus());
    }

    @Override
    public void trigger(ToDoItem toDoItem) {
        for (Stakeholder stakeholder : toDoItem.getStakeholders()) {
            communicationService.notify(toDoItem, stakeholder);
        }

        communicationService.notify(toDoItem, toDoItem.getProject().getProductOwner());
        communicationService.notify(toDoItem, toDoItem.getAssignee().getTeam());

        for (Watcher watcher : toDoItem.getWatchers()) {
            communicationService.notify(toDoItem, watcher);
        }

        communicationService.notify(toDoItem, toDoItem.getOwner());
    }
}
