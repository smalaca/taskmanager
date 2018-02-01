package org.smalaca.taskmanager.service;

import org.smalaca.taskmanager.domain.Sprint;
import org.smalaca.taskmanager.domain.Task;

public interface SprintBacklogService {
    void moveToReadyForDevelopment(Task task, Sprint sprint);
}
