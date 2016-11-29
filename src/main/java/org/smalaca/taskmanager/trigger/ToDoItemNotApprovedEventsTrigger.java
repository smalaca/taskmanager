package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Stakeholder;
import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.smalaca.taskmanager.domain.Status.USER_ACCEPTANCE_TESTING;

public class ToDoItemNotApprovedEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemNotApprovedEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return USER_ACCEPTANCE_TESTING.equals(toDoItem.getStatus()) && toDoItem instanceof Task;
    }

    @Override
    public void trigger(ToDoItem toDoItem) {
        for (Stakeholder stakeholder : toDoItem.getStakeholders()) {
            communicationService.notify(toDoItem, stakeholder);
        }

        communicationService.notify(toDoItem, toDoItem.getOwner());
    }
}
