package org.smalaca.taskmanager.rest.api;

import org.smalaca.taskmanager.domain.Definition;
import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.dto.DefinitionDto;
import org.smalaca.taskmanager.dto.ToDoItemDto;
import org.smalaca.taskmanager.processor.ToDoItemProcessor;
import org.smalaca.taskmanager.trigger.CommunicationEventsTriggerManager;
import org.smalaca.taskmanager.repository.TaskRepository;
import org.smalaca.taskmanager.validation.TaskStatusValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.smalaca.taskmanager.domain.Status.DEFINED;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskRepository taskRepository;
    private final CommunicationEventsTriggerManager communicationEventsTriggerManager;
    private final ToDoItemProcessor toDoItemProcessor;
    private final TaskStatusValidator taskStatusValidator;

    @Autowired
    public TaskController(
            TaskRepository taskRepository, CommunicationEventsTriggerManager communicationEventsTriggerManager,
            ToDoItemProcessor toDoItemProcessor, TaskStatusValidator taskStatusValidator) {
        this.taskRepository = taskRepository;
        this.communicationEventsTriggerManager = communicationEventsTriggerManager;
        this.toDoItemProcessor = toDoItemProcessor;
        this.taskStatusValidator = taskStatusValidator;
    }

    @RequestMapping(value = "/{taskId}/defined", method = RequestMethod.PUT)
    public ResponseEntity<Void> define(@PathVariable("taskId") String taskId, DefinitionDto definitionDto) {
        if (taskRepository.exists(taskId)) {
            Task task = taskRepository.findById(taskId);

            if (taskStatusValidator.isPossibleToMove(task, DEFINED)) {
                task.setDefinition(new Definition(definitionDto.getBody()));
                task.setStatus(DEFINED);
                task.start();
                taskRepository.update(task);
                toDoItemProcessor.processFor(task);
                communicationEventsTriggerManager.triggerFor(task);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{taskId}/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoItemDto> getStatus(@PathVariable("taskId") String taskId) {
        if (taskRepository.exists(taskId)) {
            Task task = taskRepository.findById(taskId);
            ToDoItemDto toDoItemDto = new ToDoItemDto();
            toDoItemDto.setStatus(task.getStatus());

            return new ResponseEntity<>(toDoItemDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
