package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.*;
import org.smalaca.taskmanager.repository.ToDoItemRepository;
import org.springframework.stereotype.Service;

@Service
public class CommunicationEventsService {
    private final CommunicationEventsTriggerManager communicationEventsTriggerManager;
    private final ToDoItemRepository toDoItemRepository;

    public CommunicationEventsService(CommunicationEventsTriggerManager communicationEventsTriggerManager, ToDoItemRepository toDoItemRepository) {
        this.communicationEventsTriggerManager = communicationEventsTriggerManager;
        this.toDoItemRepository = toDoItemRepository;
    }

    public void informAbout(String itemId) {
        ToDoItem toDoItem = toDoItemRepository.findBy(itemId);
        communicationEventsTriggerManager.triggerFor(toDoItem);
    }
}
