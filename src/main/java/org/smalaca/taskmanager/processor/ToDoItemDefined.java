package org.smalaca.taskmanager.processor;

import org.smalaca.taskmanager.domain.Epic;
import org.smalaca.taskmanager.domain.Story;
import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.event.EpicReadyToPrioritize;
import org.smalaca.taskmanager.exception.UnsupportedToDoItemType;

class ToDoItemDefined {
    private final ToDoItemProcessorFacade toDoItemProcessorFacade;

    ToDoItemDefined(ToDoItemProcessorFacade toDoItemProcessorFacade) {

        this.toDoItemProcessorFacade = toDoItemProcessorFacade;
    }

    void process(ToDoItem toDoItem) {
        if (toDoItem instanceof Story) {
            Story story = (Story) toDoItem;
            if (story.getTasks().isEmpty()) {
                toDoItemProcessorFacade.moveToReadyForDevelopment(story, story.getProject());
            } else {
                if (!story.isAssigned()) {
                    toDoItemProcessorFacade.notifyTeamsAbout(story, story.getProject());
                }
            }
        } else {
            if (toDoItem instanceof Task) {
                Task task = (Task) toDoItem;
                toDoItemProcessorFacade.moveToReadyForDevelopment(task, task.getCurrentSprint());
            } else {
                if (toDoItem instanceof Epic) {
                    Epic epic = (Epic) toDoItem;
                    toDoItemProcessorFacade.putOnTop(epic);
                    EpicReadyToPrioritize event = EpicReadyToPrioritize.anEpicReadyToPrioritize(epic);
                    toDoItemProcessorFacade.publish(event);
                    toDoItemProcessorFacade.notify(toDoItem, toDoItem.getProject().getProductOwner());
                } else {
                    throw new UnsupportedToDoItemType();
                }
            }
        }
    }
}
