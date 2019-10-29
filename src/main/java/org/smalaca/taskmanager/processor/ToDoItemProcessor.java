package org.smalaca.taskmanager.processor;

import com.google.common.collect.ImmutableMap;
import org.smalaca.taskmanager.domain.*;
import org.smalaca.taskmanager.event.EventPublisher;
import org.smalaca.taskmanager.repository.StoryRepository;
import org.smalaca.taskmanager.service.CommunicationService;
import org.smalaca.taskmanager.service.ProjectBacklogService;
import org.smalaca.taskmanager.service.SprintBacklogService;
import org.smalaca.taskmanager.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.smalaca.taskmanager.domain.Status.*;

public class ToDoItemProcessor {
    private final ToDoItemProcessorFacade toDoItemProcessorFacade;
    private final Map<Status, ToDoItemState> states;

    @Autowired
    public ToDoItemProcessor(
            StoryRepository storyRepository, StoryService storyService, EventPublisher eventPublisher,
            ProjectBacklogService projectBacklogService, CommunicationService communicationService, SprintBacklogService sprintBacklogService) {
        toDoItemProcessorFacade = new ToDoItemProcessorFacade(storyRepository, storyService, eventPublisher, projectBacklogService, communicationService, sprintBacklogService);
        states = ImmutableMap.of(
                DEFINED, new ToDoItemDefined(toDoItemProcessorFacade),
                IN_PROGRESS, new ToDoItemInProgress(toDoItemProcessorFacade),
                DONE, new ToDoItemDone(toDoItemProcessorFacade),
                APPROVED, new ToDoItemApproved(toDoItemProcessorFacade),
                RELEASED, new ToDoItemReleased(toDoItemProcessorFacade)
        );
    }

    public void processFor(ToDoItem toDoItem) {
        states.get(toDoItem.getStatus()).process(toDoItem);
    }
}
