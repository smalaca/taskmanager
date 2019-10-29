package org.smalaca.taskmanager.trigger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.smalaca.taskmanager.domain.*;
import org.smalaca.taskmanager.service.CommunicationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.smalaca.taskmanager.domain.Status.TO_BE_DEFINED;

@RunWith(MockitoJUnitRunner.class)
public class CommunicationEventsTriggerManagerTest {
    @Mock private CommunicationService communicationService;

    @Test
    public void shouldDoSomething() {
        // GIVEN
        List<CommunicationEventTrigger> triggers = new ArrayList<>();
//        triggers.add(new ToDoItemInitializedEventsTrigger(communicationService));
        triggers.add(new ToDoItemAssignedEventsTrigger(communicationService));
        triggers.add(new ToDoItemDefinedEventsTrigger(communicationService));

        CommunicationStep step = new ToDoItemInitializedEventsTrigger(communicationService);
        CommunicationEventsTriggerManager manager = new CommunicationEventsTriggerManager(triggers, step);

        ToDoItem toDoItem = mock(ToDoItem.class);
        given(toDoItem.getStatus()).willReturn(TO_BE_DEFINED);
        ProductOwner productOwner = mock(ProductOwner.class);
        Project project = mock(Project.class);
        given(project.getProductOwner()).willReturn(productOwner);
        given(toDoItem.getProject()).willReturn(project);
        Owner owner = mock(Owner.class);
        given(toDoItem.getOwner()).willReturn(owner);
        Watcher watcher = mock(Watcher.class);
        given(toDoItem.getWatchers()).willReturn(Arrays.asList(watcher));

        // WHEN
        manager.triggerFor(toDoItem);


        // THEN
        then(communicationService).should().notify(toDoItem, productOwner);
        then(communicationService).should().notify(toDoItem, watcher);
        then(communicationService).should().notify(toDoItem, owner);
    }
}
