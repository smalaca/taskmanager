package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

public class ToDoItemApprovedEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemApprovedEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return false;
    }
//    *
//            * completed, apporved
//    *      - sendIformationToTheTeam()
//    *      - sendInformationToTheProductOwner()
//    *      - notifyWatchers()
//    *      - notifyOwner()

    @Override
    public void trigger(ToDoItem toDoItem) {

    }
}
