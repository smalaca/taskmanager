package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

public class ToDoItemNotApprovedEventsTrigger implements CommunicationEventTrigger {
    private final CommunicationService communicationService;

    @Autowired
    public ToDoItemNotApprovedEventsTrigger(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return false;
    }
//    * completed, not apporved
//    *      - infoToApprovers()
//    *      - notifyOwner()
//    *

    @Override
    public void trigger(ToDoItem toDoItem) {

    }
}
