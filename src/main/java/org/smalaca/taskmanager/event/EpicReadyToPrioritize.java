package org.smalaca.taskmanager.event;

import org.smalaca.taskmanager.domain.Epic;

public class EpicReadyToPrioritize {
    private final String epicId;

    private EpicReadyToPrioritize(String epicId) {
        this.epicId = epicId;
    }

    public static EpicReadyToPrioritize anEpicReadyToPrioritize(Epic epic) {
        return new EpicReadyToPrioritize(epic.getId());
    }
}
