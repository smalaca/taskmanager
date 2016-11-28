package org.smalaca.taskmanager.domain;

import java.util.List;

public interface ToDoItem {
    Status getStatus();

    Project getProject();

    List<Watcher> getWatchers();

    Owner getOwner();
}
