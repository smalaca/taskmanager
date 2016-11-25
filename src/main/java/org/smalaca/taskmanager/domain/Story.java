package org.smalaca.taskmanager.domain;

import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;

public class Story implements ToDoItem {
    private Status status = TO_BE_DEFINED;

    @Override
    public Status getStatus() {
        return status;
    }
}
