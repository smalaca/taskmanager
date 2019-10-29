package org.smalaca.taskmanager.processor;

import org.smalaca.taskmanager.domain.Epic;
import org.smalaca.taskmanager.domain.Story;
import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.event.EpicReadyToPrioritize;
import org.smalaca.taskmanager.event.EventPublisher;
import org.smalaca.taskmanager.event.StoryApprovedEvent;
import org.smalaca.taskmanager.event.StoryDoneEvent;
import org.smalaca.taskmanager.event.TaskApprovedEvent;
import org.smalaca.taskmanager.event.ToDoItemReleasedEvent;
import org.smalaca.taskmanager.exception.UnsupportedToDoItemType;
import org.smalaca.taskmanager.repository.StoryRepository;
import org.smalaca.taskmanager.service.CommunicationService;
import org.smalaca.taskmanager.service.ProjectBacklogService;
import org.smalaca.taskmanager.service.SprintBacklogService;
import org.smalaca.taskmanager.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.smalaca.taskmanager.domain.Status.DONE;

public class ToDoItemProcessor {

    private final ToDoItemProcessorFacade toDoItemProcessorFacade;

    @Autowired
    public ToDoItemProcessor(
            StoryRepository storyRepository, StoryService storyService, EventPublisher eventPublisher,
            ProjectBacklogService projectBacklogService, CommunicationService communicationService, SprintBacklogService sprintBacklogService) {

        toDoItemProcessorFacade = new ToDoItemProcessorFacade(storyRepository, storyService, eventPublisher, projectBacklogService, communicationService, sprintBacklogService);
    }

    public void processFor(ToDoItem toDoItem) {
        switch(toDoItem.getStatus()) {
            case DEFINED:
                processDefined(toDoItem);
                break;

            case IN_PROGRESS:
                processInProgress(toDoItem);
                break;

            case DONE:
                processDone(toDoItem);
                break;

            case APPROVED:
                processApproved(toDoItem);
                break;

            case RELEASED:
                processReleased(toDoItem);
                break;
        }
    }

    private void processDefined(ToDoItem toDoItem) {
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

    private void processInProgress(ToDoItem toDoItem) {
        if (toDoItem instanceof Task) {
            Task task = (Task) toDoItem;
            Story story = toDoItemProcessorFacade.findById(task.getStoryId());
            toDoItemProcessorFacade.updateProgressOf(story, task);
        }
    }

    private void processDone(ToDoItem toDoItem) {
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

    private void processApproved(ToDoItem toDoItem) {
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

    private void processReleased(ToDoItem toDoItem) {
        ToDoItemReleasedEvent event = ToDoItemReleasedEvent.aToDoItemReleasedEvent(toDoItem);
        toDoItemProcessorFacade.publish(event);
    }
}
