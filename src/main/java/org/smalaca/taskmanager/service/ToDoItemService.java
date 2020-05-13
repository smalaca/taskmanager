package org.smalaca.taskmanager.service;

import org.smalaca.taskmanager.domain.ToDoItem;
import org.smalaca.taskmanager.processor.ToDoItemProcessor;
import org.smalaca.taskmanager.repository.ToDoItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ToDoItemService {
    private final ToDoItemProcessor processor;
    private final ToDoItemRepository repository;

    ToDoItemService(ToDoItemProcessor processor, ToDoItemRepository repository) {
        this.processor = processor;
        this.repository = repository;
    }

    public int processModified(String todoId) {
        ToDoItem toDoItem = repository.findBy(todoId);

        try {
            processor.processFor(toDoItem);
            return 1;
        } catch (Exception exception) {
            return -1;
        }
    }
}
