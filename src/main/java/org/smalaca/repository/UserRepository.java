package org.smalaca.repository;

import org.smalaca.auth.Token;
import org.smalaca.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findLoggedInBy(Token token);
}
