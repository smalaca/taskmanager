package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

import java.util.List;

public class CommunicationEventsTriggerManager {
    private final List<CommunicationEventTrigger> processors;

    public CommunicationEventsTriggerManager(List<CommunicationEventTrigger> processors) {
        this.processors = processors;
    }

    public void triggerFor(ToDoItem toDoItem) {
        for (CommunicationEventTrigger processor : processors) {
            if (processor.isApplicableFor(toDoItem)) {
                processor.trigger(toDoItem);
            }
        }
    }
}
