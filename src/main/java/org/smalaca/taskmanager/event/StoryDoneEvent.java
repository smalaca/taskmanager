package org.smalaca.taskmanager.event;

import org.smalaca.taskmanager.domain.Story;

public class StoryDoneEvent {
    private final String storyId;

    private StoryDoneEvent(String storyId) {
        this.storyId = storyId;
    }

    public static StoryDoneEvent aStoryDoneEventFor(Story story) {
        return new StoryDoneEvent(story.getId());
    }
}
