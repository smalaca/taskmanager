package org.smalaca.taskmanager.event;

import org.smalaca.taskmanager.domain.Task;

public class TaskApprovedEvent {
    private final String taskId;

    private TaskApprovedEvent(String taskId) {
        this.taskId = taskId;
    }

    public static TaskApprovedEvent aTaskApprovedEvent(Task task) {
        return new TaskApprovedEvent(task.getId());
    }
}
