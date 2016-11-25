package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;

public class ToDoItemDefinedEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(Task task) {
        return false;
    }

//    * is defined
//    *
//            *      EVENTS (EventsManager)
//    *      - setBAasAWatcher()
//    *      - snnedInforrmationToTheTeam()
//    *      - notifyWatchers()
//    *      - notifyOwner()

    @Override
    public void trigger(Task task) {

    }
}
