package org.smalaca.taskmanager.rest.api;

import org.smalaca.taskmanager.domain.Definition;
import org.smalaca.taskmanager.domain.Task;
import org.smalaca.taskmanager.dto.DefinitionDto;
import org.smalaca.taskmanager.dto.ToDoItemDto;
import org.smalaca.taskmanager.trigger.CommunicationEventsTriggerManager;
import org.smalaca.taskmanager.repository.TaskRepository;
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

    @Autowired
    public TaskController(TaskRepository taskRepository, CommunicationEventsTriggerManager communicationEventsTriggerManager) {
        this.taskRepository = taskRepository;
        this.communicationEventsTriggerManager = communicationEventsTriggerManager;
    }

    @RequestMapping(value = "/{taskId}/defined", method = RequestMethod.PUT)
    public ResponseEntity<Void> define(@PathVariable("taskId") String taskId, DefinitionDto definitionDto) {
        if (taskRepository.exists(taskId)) {
            Task task = taskRepository.findById(taskId);
            task.setDefinition(new Definition(definitionDto.getBody()));
            task.setStatus(DEFINED);
            /**
             * processor here as well. Based on state.
             */
            communicationEventsTriggerManager.triggerFor(task);

/**
 * CHAIN OF RESP + DECORATOR - each step
 * is initialized
 *
 *      EVENTS (EventsManager)
 *      - sendInformationToTheProductOwner()
 *      - notifyWatchers()
 *
 *
 * is initialized and assigned to the team
 *
 *      EVENTS (EventsManager)
 *      - sendIformationToTheTeam()
 *      - assignToTheBA()
 *      - notifyWatchers()
 *
 * is defined
 *
 *      EVENTS (EventsManager)
 *      - setBAasAWatcher()
 *      - snnedInforrmationToTheTeam()
 *      - notifyWatchers()
 *
 * is in progress longer then one sprint
 *
 *      EVENTS (EventsManager)
 *      - warnProductOwner()
 *      - warnTheTeam()
 *      - warnWatchers()
 *
 * is in progress
 *      - sendIformationToTheTeam()
 *
 * completed, not apporved and longer then one sprint
 *      - reminderToApprovers()
 *
 * completed, not apporved
 *      - infoToApprovers()
 *
 *
 * completed, apporved
 *      - sendIformationToTheTeam()
 *      - sendInformationToTheProductOwner()
 *      - notifyWatchers()
 *
 * released
 *      - infoToWatchers()
 *      - infoToEveryoneInvolvedInProject()
 */
/**
 * VISITOR
 * 1. info to team - instanceof because Epic will send to multiple
 * 2. send to BA - same as above in case of Epic, no info in case of Task
 * 3. send to Watchers - only for story and epic
 * 4. notifu ProductOwner - in case of Epic and Story
 */

/**
 * Strategy? - calculating estimates - automatic, lucky guess or manual?
 * State? - after changing a state - processor
 */


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
