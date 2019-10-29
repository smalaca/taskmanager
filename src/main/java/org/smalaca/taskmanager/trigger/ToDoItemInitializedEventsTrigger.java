package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.domain.Watcher;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;

public class ToDoItemInitializedEventsTrigger implements CommunicationEventTrigger, CommunicationStep {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemInitializedEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return TO_BE_DEFINED.equals(toDoItem.getStatus());
    }

    @Override
    public void trigger(ToDoItem toDoItem) {
        communicationService.notify(toDoItem, toDoItem.getProductOwner());

        for (Watcher watcher : toDoItem.getWatchers()) {
            communicationService.notify(toDoItem, watcher);
        }

        communicationService.notify(toDoItem, toDoItem.getOwner());
    }

    @Override
    public void process(ToDoItem toDoItem) {
        if (isApplicableFor(toDoItem)) {
            trigger(toDoItem);
        }
    }
}
