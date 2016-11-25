package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.Task;

public class ToDoItemApprovedEventsTrigger implements CommunicationEventTrigger {
    @Override
    public boolean isApplicableFor(Task task) {
        return false;
    }
//    *
//            * completed, apporved
//    *      - sendIformationToTheTeam()
//    *      - sendInformationToTheProductOwner()
//    *      - notifyWatchers()
//    *      - notifyOwner()

    @Override
    public void trigger(Task task) {

    }
}
