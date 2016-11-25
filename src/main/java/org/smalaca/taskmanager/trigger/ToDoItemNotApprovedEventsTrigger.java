package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;

public class ToDoItemNotApprovedEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(Task task) {
        return false;
    }
//    * completed, not apporved
//    *      - infoToApprovers()
//    *      - notifyOwner()
//    *

    @Override
    public void trigger(Task task) {

    }
}
