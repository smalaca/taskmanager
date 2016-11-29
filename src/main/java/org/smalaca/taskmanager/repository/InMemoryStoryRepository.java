package org.smalaca.taskmanager.repository;

import org.smalaca.taskmanager.domain.Story;
import org.smalaca.taskmanager.exception.StoryNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStoryRepository implements StoryRepository {
    private final Map<String, Story> stories = new HashMap<>();

    public InMemoryStoryRepository() {
        stories.put("1", aStory("1", "Prepare a prototype of new web page"));
        stories.put("2", aStory("2", "Prepare code examples for workshop"));
        stories.put("3", aStory("3", "Update project dependencies"));
    }

    private Story aStory(String id, String name) {
        Story story = new Story();
        story.setId(id);
        story.setName(name);

        return story;
    }

    @Override
    public Story findById(String storyId) {
        if (stories.containsKey(storyId)) {
            return stories.get(storyId).copy();
        }

        throw new StoryNotFoundException("Story with id: " + storyId + " does not exists.");
    }
}
