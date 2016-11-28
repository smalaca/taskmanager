package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

public class ToDoItemNotApprovedEventsTrigger implements CommunicationEventTrigger {
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
