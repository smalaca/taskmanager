package org.smalaca.taskmanager.trigger;

import org.smalaca.taskmanager.domain.ToDoItem;

public class IsWaitingForApprovalTooLongPredicate {
    public boolean apply(ToDoItem toDoItem) {
        return false;
    }
}
