package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

public class ToDoItemDefinedEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
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
    public void trigger(ToDoItem toDoItem) {

    }
}
