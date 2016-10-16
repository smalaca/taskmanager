package org.smalaca.repository;

import org.smalaca.auth.Token;
import org.smalaca.domain.User;

import java.util.Optional;

public class DbUserRepository implements UserRepository {

    @Override
    public Optional<User> findLoggedInBy(Token token) {
        return null;
    }
}
