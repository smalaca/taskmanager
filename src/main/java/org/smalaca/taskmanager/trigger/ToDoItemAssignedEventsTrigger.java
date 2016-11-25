package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;

public class ToDoItemAssignedEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(Task task) {
        return false;
    }

//    * is initialized and assigned to the team
//    *
//            *      EVENTS (EventsManager)
//    *      - sendIformationToTheTeam()
//    *      - assignToTheBA()
//    *      - notifyWatchers()
//    *      - notifyOwner()
//    *
    @Override
    public void trigger(Task task) {

    }
}
