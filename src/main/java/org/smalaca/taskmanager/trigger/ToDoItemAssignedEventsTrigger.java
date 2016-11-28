package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Status;
import org.smalaca.taskmanager.domain.Team;
import org.smalaca.taskmanager.domain.TeamRole;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.domain.Watcher;
import org.smalaca.taskmanager.service.CommunicationService;

public class ToDoItemAssignedEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    public ToDoItemAssignedEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return Status.TO_BE_DEFINED.equals(toDoItem.getStatus()) && toDoItem.isAssigned();
    }

    @Override
    public void trigger(ToDoItem toDoItem) {
        Team team = toDoItem.getAssignee().getTeam();
        communicationService.notify(toDoItem, team);
        communicationService.notify(toDoItem, team.getMemberByRole(TeamRole.BUSINESS_ANALYSIS));

        for (Watcher watcher : toDoItem.getWatchers()) {
            communicationService.notify(toDoItem, watcher);
        }

        communicationService.notify(toDoItem, toDoItem.getOwner());
    }
}
