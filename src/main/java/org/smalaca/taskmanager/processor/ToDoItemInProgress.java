package org.smalaca.taskmanager.processor;

import org.smalaca.taskmanager.domain.Story;
import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.domain.ToDoItem;

class ToDoItemInProgress {
    private final ToDoItemProcessorFacade toDoItemProcessorFacade;

    ToDoItemInProgress(ToDoItemProcessorFacade toDoItemProcessorFacade) {
        this.toDoItemProcessorFacade = toDoItemProcessorFacade;
    }

    void process(ToDoItem toDoItem) {
        if (toDoItem instanceof Task) {
            Task task = (Task) toDoItem;
            Story story = toDoItemProcessorFacade.findById(task.getStoryId());
            toDoItemProcessorFacade.updateProgressOf(story, task);
        }
    }
}
