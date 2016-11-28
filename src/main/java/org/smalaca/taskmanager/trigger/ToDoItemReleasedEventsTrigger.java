package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

public class ToDoItemReleasedEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemReleasedEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return false;
    }
//    *
//            * released
//    *      - infoToWatchers()
//    *      - infoToEveryoneInvolvedInProject()
//    *      - notifyOwner()
//    */

    @Override
    public void trigger(ToDoItem toDoItem) {

    }
}
