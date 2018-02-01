package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.Story;

public interface StoryRepository {
    Story findById(String storyId);
}
