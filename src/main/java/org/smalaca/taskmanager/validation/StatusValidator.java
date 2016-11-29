package org.smalaca.taskmanager.validation;

import org.smalaca.taskmanager.domain.Status;
import org.smalaca.taskmanager.domain.Task;

import static org.smalaca.taskmanager.domain.Status.APPROVED;
import static org.smalaca.taskmanager.domain.Status.DEFINED;
import static org.smalaca.taskmanager.domain.Status.DONE;
import static org.smalaca.taskmanager.domain.Status.IN_PROGRESS;
import static org.smalaca.taskmanager.domain.Status.RELEASED;
import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;

public class StatusValidator {
    public boolean isPossibleToMove(Task task, Status status) {
        Status currentStatus = task.getStatus();

        if (RELEASED.equals(currentStatus) || TO_BE_DEFINED.equals(status)) {
            return false;
        }

        return (TO_BE_DEFINED.equals(currentStatus) && DEFINED.equals(status))
                || (DEFINED.equals(currentStatus) && IN_PROGRESS.equals(status))
                || (IN_PROGRESS.equals(currentStatus) && DONE.equals(status))
                || (DONE.equals(currentStatus) && APPROVED.equals(status))
                || (APPROVED.equals(currentStatus) && RELEASED.equals(status));
    }
}
