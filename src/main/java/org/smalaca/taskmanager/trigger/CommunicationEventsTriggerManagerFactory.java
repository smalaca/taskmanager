package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CommunicationEventsTriggerManagerFactory {
    private final CommunicationService communicationService;

    @Autowired
    public CommunicationEventsTriggerManagerFactory(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    CommunicationEventsTriggerManager create() {
        List<CommunicationEventTrigger> triggers = new ArrayList<>();

        triggers.add(new ToDoItemInitializedEventsTrigger(communicationService));
        triggers.add(new ToDoItemAssignedEventsTrigger(communicationService));
        triggers.add(new ToDoItemDefinedEventsTrigger(communicationService));

        return new CommunicationEventsTriggerManager(triggers);
    }
}
