package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;

public class ToDoItemReleasedEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(Task task) {
        return false;
    }
//    *
//            * released
//    *      - infoToWatchers()
//    *      - infoToEveryoneInvolvedInProject()
//    *      - notifyOwner()
//    */

    @Override
    public void trigger(Task task) {

    }
}
