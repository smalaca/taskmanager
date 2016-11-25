package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;

public class ToDoItemNotApprovedTooLongEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(Task task) {
        return false;
    }
//    * completed, not apporved and longer then one sprint
//    *      - reminderToApprovers()
//    *      - notifyOwner()
//    *

    @Override
    public void trigger(Task task) {

    }
}
