package org.smalaca.taskmanager.session;

import org.smalaca.taskmanager.domain.User;

public class SessionHolder {
    private static final SessionHolder INSTANCE = new SessionHolder();

    private SessionHolder() {}

    public static SessionHolder intance() {
        return INSTANCE;
    }

    public User logged() {
        return null;
    }
}
