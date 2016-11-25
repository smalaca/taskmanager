package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;

public interface CommunicationEventTrigger {
    boolean isApplicableFor(Task task);

    void trigger(Task task);
}
