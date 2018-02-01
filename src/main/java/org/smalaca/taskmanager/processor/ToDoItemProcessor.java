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
    private final StoryRepository storyRepository;
    private final StoryService storyService;
    private final EventPublisher eventPublisher;
    private final ProjectBacklogService projectBacklogService;
    private final CommunicationService communicationService;
    private final SprintBacklogService sprintBacklogService;

    @Autowired
    public ToDoItemProcessor(
            StoryRepository storyRepository, StoryService storyService, EventPublisher eventPublisher,
            ProjectBacklogService projectBacklogService, CommunicationService communicationService, SprintBacklogService sprintBacklogService) {
        this.storyRepository = storyRepository;
        this.storyService = storyService;
        this.eventPublisher = eventPublisher;
        this.projectBacklogService = projectBacklogService;
        this.communicationService = communicationService;
        this.sprintBacklogService = sprintBacklogService;
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
                projectBacklogService.moveToReadyForDevelopment(story, story.getProject());
            } else {
                if (!story.isAssigned()) {
                    communicationService.notifyTeamsAbout(story, story.getProject());
                }
            }
        } else {
            if (toDoItem instanceof Task) {
                Task task = (Task) toDoItem;
                sprintBacklogService.moveToReadyForDevelopment(task, task.getCurrentSprint());
            } else {
                if (toDoItem instanceof Epic) {
                    Epic epic = (Epic) toDoItem;
                    projectBacklogService.putOnTop(epic);
                    EpicReadyToPrioritize event = EpicReadyToPrioritize.anEpicReadyToPrioritize(epic);
                    eventPublisher.publish(event);
                    communicationService.notify(toDoItem, toDoItem.getProject().getProductOwner());
                } else {
                    throw new UnsupportedToDoItemType();
                }
            }
        }
    }

    private void processInProgress(ToDoItem toDoItem) {
        if (toDoItem instanceof Task) {
            Task task = (Task) toDoItem;
            Story story = storyRepository.findById(task.getStoryId());
            storyService.updateProgressOf(story, task);
        }
    }

    private void processDone(ToDoItem toDoItem) {
        if (toDoItem instanceof Task) {
            Task task = (Task) toDoItem;
            Story story = storyRepository.findById(task.getStoryId());
            storyService.updateProgressOf(story, task);
            if (DONE.equals(story.getStatus())) {
                StoryDoneEvent event = StoryDoneEvent.aStoryDoneEventFor(story);
                eventPublisher.publish(event);
            }
        } else if (toDoItem instanceof Story) {
            Story story = (Story) toDoItem;
            StoryDoneEvent event = StoryDoneEvent.aStoryDoneEventFor(story);
            eventPublisher.publish(event);
        }
    }

    private void processApproved(ToDoItem toDoItem) {
        if (toDoItem instanceof Story) {
            Story story = (Story) toDoItem;
            StoryApprovedEvent event = StoryApprovedEvent.aStoryApprovedEventFor(story);
            eventPublisher.publish(event);
        } else if (toDoItem instanceof Task) {
            Task task = (Task) toDoItem;

            if (task.isSubtask()) {
                TaskApprovedEvent event = TaskApprovedEvent.aTaskApprovedEvent(task);
                eventPublisher.publish(event);
            } else {
                storyService.attachPartialApprovalFor(task.getStoryId(), task.getId());
            }
        }
    }

    private void processReleased(ToDoItem toDoItem) {
        ToDoItemReleasedEvent event = ToDoItemReleasedEvent.aToDoItemReleasedEvent(toDoItem);
        eventPublisher.publish(event);
    }
}
