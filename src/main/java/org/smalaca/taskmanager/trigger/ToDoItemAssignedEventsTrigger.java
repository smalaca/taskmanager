package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Story;
import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.domain.Team;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.domain.Watcher;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;
import static org.smalaca.taskmanager.domain.TeamRole.BUSINESS_ANALYSIS;

public class ToDoItemAssignedEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemAssignedEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return TO_BE_DEFINED.equals(toDoItem.getStatus())
                && (toDoItem instanceof Task || toDoItem instanceof Story)
                && toDoItem.isAssigned();
    }

    @Override
    public void trigger(ToDoItem toDoItem) {
        Team team = toDoItem.getAssignee().getTeam();
        communicationService.notify(toDoItem, team);
        communicationService.notify(toDoItem, team.getMemberByRole(BUSINESS_ANALYSIS));

        for (Watcher watcher : toDoItem.getWatchers()) {
            communicationService.notify(toDoItem, watcher);
        }

        communicationService.notify(toDoItem, toDoItem.getOwner());
    }
}
