package org.smalaca.taskmanager.processor;

import org.smalaca.taskmanager.domain.Story;
import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.event.StoryDoneEvent;

import static org.smalaca.taskmanager.domain.Status.DONE;

class ToDoItemDone implements ToDoItemState {
    private final ToDoItemProcessorFacade toDoItemProcessorFacade;

    ToDoItemDone(ToDoItemProcessorFacade toDoItemProcessorFacade) {
        this.toDoItemProcessorFacade = toDoItemProcessorFacade;
    }

    public void process(ToDoItem toDoItem) {
        if (toDoItem instanceof Task) {
            Task task = (Task) toDoItem;
            Story story = toDoItemProcessorFacade.findById(task.getStoryId());
            toDoItemProcessorFacade.updateProgressOf(story, task);
            if (DONE.equals(story.getStatus())) {
                StoryDoneEvent event = StoryDoneEvent.aStoryDoneEventFor(story);
                toDoItemProcessorFacade.publish(event);
            }
        } else if (toDoItem instanceof Story) {
            Story story = (Story) toDoItem;
            StoryDoneEvent event = StoryDoneEvent.aStoryDoneEventFor(story);
            toDoItemProcessorFacade.publish(event);
        }
    }
}
