package org.smalaca.taskmanager.service;

import org.smalaca.taskmanager.domain.Epic;
import org.smalaca.taskmanager.domain.Project;
import org.smalaca.taskmanager.domain.Story;

public interface ProjectBacklogService {
    void moveToReadyForDevelopment(Story story, Project project);

    void putOnTop(Epic epic);

    String linkFor(String toDoItemId);
}
