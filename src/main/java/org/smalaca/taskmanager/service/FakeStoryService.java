package org.smalaca.taskmanager.service;

import org.smalaca.taskmanager.domain.Story;
import org.smalaca.taskmanager.domain.Task;

public class FakeStoryService implements StoryService {
    @Override
    public void updateProgressOf(Story story, Task task) {

    }

    @Override
    public void attachPartialApprovalFor(String storyId, String id) {

    }
}
