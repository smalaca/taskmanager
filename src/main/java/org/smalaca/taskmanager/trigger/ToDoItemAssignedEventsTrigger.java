package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

public class ToDoItemAssignedEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
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
    public void trigger(ToDoItem toDoItem) {

    }
}
