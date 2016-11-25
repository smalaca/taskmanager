package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;

import java.util.List;

public class CommunicationEventsTriggerManager {
    private final List<CommunicationEventTrigger> processors;

    public CommunicationEventsTriggerManager(List<CommunicationEventTrigger> processors) {
        this.processors = processors;
    }

    public void triggerFor(Task task) {
        for (CommunicationEventTrigger processor : processors) {
            if (processor.isApplicableFor(task)) {
                processor.trigger(task);
            }
        }
    }
}
