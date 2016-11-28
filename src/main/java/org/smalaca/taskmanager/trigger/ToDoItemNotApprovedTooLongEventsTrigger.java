package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

public class ToDoItemNotApprovedTooLongEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return false;
    }
//    * completed, not apporved and longer then one sprint
//    *      - reminderToApprovers()
//    *      - notifyOwner()
//    *

    @Override
    public void trigger(ToDoItem toDoItem) {

    }
}
