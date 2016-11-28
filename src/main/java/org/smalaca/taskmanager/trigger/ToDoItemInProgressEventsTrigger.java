package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

public class ToDoItemInProgressEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return false;
    }
//    *
//            * is in progress
//    *      - sendIformationToTheTeam()
//    *      - notifyOwner()
//    *

    @Override
    public void trigger(ToDoItem toDoItem) {

    }
}
