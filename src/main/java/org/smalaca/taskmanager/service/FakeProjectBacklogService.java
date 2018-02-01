package org.smalaca.taskmanager.service;

import org.smalaca.taskmanager.domain.Epic;
import org.smalaca.taskmanager.domain.Project;
import org.smalaca.taskmanager.domain.Story;

public class FakeProjectBacklogService implements ProjectBacklogService {
    @Override
    public void moveToReadyForDevelopment(Story story, Project project) {

    }

    @Override
    public void putOnTop(Epic epic) {

    }
}
