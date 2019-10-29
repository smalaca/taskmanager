package org.smalaca.taskmanager.processor;

import org.smalaca.taskmanager.domain.*;
import org.smalaca.taskmanager.event.*;
import org.smalaca.taskmanager.repository.StoryRepository;
import org.smalaca.taskmanager.service.CommunicationService;
import org.smalaca.taskmanager.service.ProjectBacklogService;
import org.smalaca.taskmanager.service.SprintBacklogService;
import org.smalaca.taskmanager.service.StoryService;

class ToDoItemProcessorFacade {
    private final StoryRepository storyRepository;
    private final StoryService storyService;
    private final EventPublisher eventPublisher;
    private final ProjectBacklogService projectBacklogService;
    private final CommunicationService communicationService;
    private final SprintBacklogService sprintBacklogService;

    ToDoItemProcessorFacade(StoryRepository storyRepository, StoryService storyService, EventPublisher eventPublisher, ProjectBacklogService projectBacklogService, CommunicationService communicationService, SprintBacklogService sprintBacklogService) {
        this.storyRepository = storyRepository;
        this.storyService = storyService;
        this.eventPublisher = eventPublisher;
        this.projectBacklogService = projectBacklogService;
        this.communicationService = communicationService;
        this.sprintBacklogService = sprintBacklogService;
    }

    Story findById(String storyId) {
        return storyRepository.findById(storyId);
    }

    void updateProgressOf(Story story, Task task) {
        storyService.updateProgressOf(story, task);
    }

    void attachPartialApprovalFor(String storyId, String id) {
        storyService.attachPartialApprovalFor(storyId, id);
    }

    void publish(EpicReadyToPrioritize event) {
        eventPublisher.publish(event);
    }

    void publish(StoryDoneEvent event) {
        eventPublisher.publish(event);
    }

    void publish(StoryApprovedEvent event) {
        eventPublisher.publish(event);
    }

    void publish(TaskApprovedEvent event) {
        eventPublisher.publish(event);
    }

    void publish(ToDoItemReleasedEvent event) {
        eventPublisher.publish(event);
    }

    void moveToReadyForDevelopment(Story story, Project project) {
        projectBacklogService.moveToReadyForDevelopment(story, project);
    }

    void putOnTop(Epic epic) {
        projectBacklogService.putOnTop(epic);
    }

    void notifyTeamsAbout(Story story, Project project) {
        communicationService.notifyTeamsAbout(story, project);
    }

    void notify(ToDoItem toDoItem, ProductOwner productOwner) {
        communicationService.notify(toDoItem, productOwner);
    }

    void moveToReadyForDevelopment(Task task, Sprint currentSprint) {
        sprintBacklogService.moveToReadyForDevelopment(task, currentSprint);
    }
}
