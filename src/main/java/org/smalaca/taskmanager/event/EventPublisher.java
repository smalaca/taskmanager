package org.smalaca.taskmanager.event;

public interface EventPublisher {
    void publish(StoryDoneEvent event);

    void publish(StoryApprovedEvent event);

    void publish(TaskApprovedEvent event);

    void publish(EpicReadyToPrioritize event);

    void publish(ToDoItemReleasedEvent event);
}
