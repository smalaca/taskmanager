package org.smalaca.taskmanager.processor;

import org.smalaca.taskmanager.domain.Story;
import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.event.StoryApprovedEvent;
import org.smalaca.taskmanager.event.TaskApprovedEvent;

class ToDoItemApproved {
    private final ToDoItemProcessorFacade toDoItemProcessorFacade;

    ToDoItemApproved(ToDoItemProcessorFacade toDoItemProcessorFacade) {
        this.toDoItemProcessorFacade = toDoItemProcessorFacade;
    }

    void process(ToDoItem toDoItem) {
        if (toDoItem instanceof Story) {
            Story story = (Story) toDoItem;
            StoryApprovedEvent event = StoryApprovedEvent.aStoryApprovedEventFor(story);
            toDoItemProcessorFacade.publish(event);
        } else if (toDoItem instanceof Task) {
            Task task = (Task) toDoItem;

            if (task.isSubtask()) {
                TaskApprovedEvent event = TaskApprovedEvent.aTaskApprovedEvent(task);
                toDoItemProcessorFacade.publish(event);
            } else {
                toDoItemProcessorFacade.attachPartialApprovalFor(task.getStoryId(), task.getId());
            }
        }
    }
}
