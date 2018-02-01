package org.smalaca.taskmanager.service;

import org.smalaca.taskmanager.domain.Story;
import org.smalaca.taskmanager.domain.Task;

public interface StoryService {
    void updateProgressOf(Story story, Task task);

    void attachPartialApprovalFor(String storyId, String id);
}
