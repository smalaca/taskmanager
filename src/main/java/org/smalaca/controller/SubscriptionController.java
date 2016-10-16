package org.smalaca.controller;

import org.smalaca.auth.Token;
import org.smalaca.domain.TaskIdentifier;
import org.smalaca.domain.User;
import org.smalaca.repository.DbUserRepository;
import org.smalaca.service.SubscriptionService;

import java.util.Optional;

public class SubscriptionController {
    private final DbUserRepository userRepository;
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService, DbUserRepository userRepository) {
        this.subscriptionService = subscriptionService;
        this.userRepository = userRepository;
    }


    public void subscribeFor(Token token, TaskIdentifier identifier) {
        Optional<User> user = userRepository.findLoggedInBy(token);

        if (user.isPresent()) {
             subscriptionService.subscribeFor(user.get(), identifier);
        }
    }
}
