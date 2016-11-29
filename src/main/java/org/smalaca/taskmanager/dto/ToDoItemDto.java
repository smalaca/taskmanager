package org.smalaca.taskmanager.dto;

import org.smalaca.taskmanager.domain.Status;

public class ToDoItemDto {
    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
