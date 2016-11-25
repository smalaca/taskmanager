package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;

public class ToDoItemInProgressEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(Task task) {
        return false;
    }
//    *
//            * is in progress
//    *      - sendIformationToTheTeam()
//    *      - notifyOwner()
//    *

    @Override
    public void trigger(Task task) {

    }
}
