package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

public class ToDoItemTooLongInProgressEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return false;
    }
//    *
//            * is in progress longer then one sprint
//    *
//            *      EVENTS (EventsManager)
//    *      - warnProductOwner()
//    *      - warnTheTeam()
//    *      - warnWatchers()
//    *      - notifyOwner()

    @Override
    public void trigger(ToDoItem toDoItem) {

    }
}
