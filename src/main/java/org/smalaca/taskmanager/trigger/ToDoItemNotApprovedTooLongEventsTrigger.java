package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Stakeholder;
import org.smalaca.taskmanager.domain.Story;
import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.smalaca.taskmanager.domain.Status.USER_ACCEPTANCE_TESTING;

public class ToDoItemNotApprovedTooLongEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemNotApprovedTooLongEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return USER_ACCEPTANCE_TESTING.equals(toDoItem.getStatus())
                && (toDoItem instanceof Task || toDoItem instanceof Story)
                && isWaitsForApprovalTooLong((Task) toDoItem);
    }

    private boolean isWaitsForApprovalTooLong(Task task) {
        return ChronoUnit.DAYS.between(task.getResolutionDate().toInstant(), new Date().toInstant()) > 2;
    }

    @Override
    public void trigger(ToDoItem toDoItem) {
        for (Stakeholder stakeholder : toDoItem.getStakeholders()) {
            communicationService.notify(toDoItem, stakeholder);
        }

        communicationService.notify(toDoItem, toDoItem.getOwner());
    }
}
