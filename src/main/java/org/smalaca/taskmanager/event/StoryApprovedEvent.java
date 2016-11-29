package org.smalaca.taskmanager.event;

import org.smalaca.taskmanager.domain.Story;

public class StoryApprovedEvent {
    private final String storyId;

    private StoryApprovedEvent(String storyId) {
        this.storyId = storyId;
    }

    public static StoryApprovedEvent aStoryApprovedEventFor(Story story) {
        return new StoryApprovedEvent(story.getId());
    }
}
