package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

import java.util.List;

public class CommunicationEventsTriggerManager {
    private final List<CommunicationEventTrigger> processors;
    private CommunicationStep step;

    public CommunicationEventsTriggerManager(List<CommunicationEventTrigger> processors) {
        this.processors = processors;
    }

    public CommunicationEventsTriggerManager(List<CommunicationEventTrigger> triggers, CommunicationStep step) {
        this(triggers);
        this.step = step;
    }

    public void triggerFor(ToDoItem toDoItem) {
        if (step != null) {
            step.process(toDoItem);
        }

        for (CommunicationEventTrigger processor : processors) {
            if (processor.isApplicableFor(toDoItem)) {
                processor.trigger(toDoItem);
            }
        }
    }
}
