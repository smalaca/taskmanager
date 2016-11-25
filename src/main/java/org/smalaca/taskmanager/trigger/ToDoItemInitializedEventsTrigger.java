package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;

public class ToDoItemInitializedEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(Task task) {
        return false;
    }

//    * is initialized
//    *
//            *      EVENTS (EventsManager)
//    *      - sendInformationToTheProductOwner()
//    *      - notifyWatchers()
//    *      - notifyOwner()
//    *
    @Override
    public void trigger(Task task) {

    }
}
