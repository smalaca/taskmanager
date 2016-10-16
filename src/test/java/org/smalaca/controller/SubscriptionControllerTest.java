package org.smalaca.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.smalaca.auth.Token;
import org.smalaca.domain.TaskIdentifier;
import org.smalaca.domain.User;
import org.smalaca.repository.DbUserRepository;
import org.smalaca.service.SubscriptionService;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionControllerTest {
    private static final Token DUMMY_TOKEN = new Token();
    private static final TaskIdentifier DUMMY_INDENTIFIER = new TaskIdentifier();
    private static final User DUMMY_USER = new User();

    @Mock private DbUserRepository userRepository;
    @Mock private SubscriptionService subscriptionService;
    @InjectMocks private SubscriptionController controller;

    @Test
    public void shouldSubscribeIfUserExists() {
        given(userRepository.findLoggedInBy(DUMMY_TOKEN)).willReturn(of(DUMMY_USER));

        controller.subscribeFor(DUMMY_TOKEN, DUMMY_INDENTIFIER);

        then(subscriptionService).should().subscribeFor(DUMMY_USER, DUMMY_INDENTIFIER);
    }

    @Test
    public void shouldNotSubscribeIfUserDoesNotExist() {
        given(userRepository.findLoggedInBy(DUMMY_TOKEN)).willReturn(empty());

        controller.subscribeFor(DUMMY_TOKEN, DUMMY_INDENTIFIER);

        then(subscriptionService).should(never()).subscribeFor(any(), any());
    }
}