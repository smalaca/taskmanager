package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Stakeholder;
import org.smalaca.taskmanager.domain.Story;
import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.smalaca.taskmanager.domain.Status.DONE;

public class ToDoItemNotApprovedEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemNotApprovedEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return DONE.equals(toDoItem.getStatus()) && (toDoItem instanceof Task || toDoItem instanceof Story);
    }

    @Override
    public void trigger(ToDoItem toDoItem) {
        for (Stakeholder stakeholder : toDoItem.getStakeholders()) {
            communicationService.notify(toDoItem, stakeholder);
        }

        communicationService.notify(toDoItem, toDoItem.getOwner());
    }
}
