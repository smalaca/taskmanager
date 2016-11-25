package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;

public class ToDoItemTooLongInProgressEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(Task task) {
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
    public void trigger(Task task) {

    }
}
