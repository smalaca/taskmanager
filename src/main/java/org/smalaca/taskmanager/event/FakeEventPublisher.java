package org.smalaca.taskmanager.event;

public class FakeEventPublisher implements EventPublisher {
    @Override
    public void publish(StoryDoneEvent event) {

    }

    @Override
    public void publish(StoryApprovedEvent event) {

    }

    @Override
    public void publish(TaskApprovedEvent event) {

    }

    @Override
    public void publish(EpicReadyToPrioritize event) {

    }

    @Override
    public void publish(ToDoItemReleasedEvent event) {

    }
}
