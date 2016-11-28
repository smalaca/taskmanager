package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

public class ToDoItemApprovedEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(ToDoItem toDoItem) {
        return false;
    }
//    *
//            * completed, apporved
//    *      - sendIformationToTheTeam()
//    *      - sendInformationToTheProductOwner()
//    *      - notifyWatchers()
//    *      - notifyOwner()

    @Override
    public void trigger(ToDoItem toDoItem) {

    }
}
