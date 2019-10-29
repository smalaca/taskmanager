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
                new ToDoItemDefined(toDoItemProcessorFacade).process(toDoItem);
                break;

            case IN_PROGRESS:
                new ToDoItemInProgress(toDoItemProcessorFacade).process(toDoItem);
                break;

            case DONE:
                new ToDoItemDone(toDoItemProcessorFacade).process(toDoItem);
                break;

            case APPROVED:
                new ToDoItemApproved(toDoItemProcessorFacade).process(toDoItem);
                break;

            case RELEASED:
                new ToDoItemReleased(toDoItemProcessorFacade).process(toDoItem);
                break;
        }
    }
}
