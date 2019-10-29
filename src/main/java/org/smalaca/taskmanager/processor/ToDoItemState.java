package org.smalaca.taskmanager.processor;

import org.smalaca.taskmanager.domain.ToDoItem;

interface ToDoItemState {
    void process(ToDoItem item);
}
