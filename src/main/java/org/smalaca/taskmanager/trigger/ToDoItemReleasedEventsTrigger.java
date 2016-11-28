package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

public class ToDoItemReleasedEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return false;
    }
//    *
//            * released
//    *      - infoToWatchers()
//    *      - infoToEveryoneInvolvedInProject()
//    *      - notifyOwner()
//    */

    @Override
    public void trigger(ToDoItem toDoItem) {

    }
}
