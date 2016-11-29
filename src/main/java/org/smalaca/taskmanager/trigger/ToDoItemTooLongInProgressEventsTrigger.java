package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.domain.Watcher;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.smalaca.taskmanager.domain.Status.IN_PROGRESS;

public class ToDoItemTooLongInProgressEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemTooLongInProgressEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return IN_PROGRESS.equals(toDoItem.getStatus())
                && toDoItem instanceof Task
                && isInProgressLongerThanOneSprint((Task) toDoItem);
    }

    private boolean isInProgressLongerThanOneSprint(Task task) {
        return task.getCurrentSprint().differentThan(task.getAssignementSprint());
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
